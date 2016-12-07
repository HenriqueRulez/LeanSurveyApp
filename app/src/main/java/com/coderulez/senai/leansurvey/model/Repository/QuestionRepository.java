package com.coderulez.senai.leansurvey.model.Repository;

import com.coderulez.senai.leansurvey.model.Option;
import com.google.gson.Gson;
import com.coderulez.senai.leansurvey.model.Question;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SENAI on 30/11/2016.
 */

public class QuestionRepository
{

    static Gson gson = new Gson();

    public static void List(final long questionnaireId, final ICallback<Question[]> cb){

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://xabuco.com.br/Senai-LeanSurvey/questionnaire/" + questionnaireId + "/question/")
                        .get()
                        .build();

                try {
                    Response resp = client.newCall(request).execute();
                    int code = resp.code();
                    String body = resp.body().string();

                    if (code != 200){
                        cb.Callback(null, body);
                    }else{

                        Question[] questions = gson.fromJson(body, Question[].class);
                        cb.Callback(questions, null);

                    }
                }
                catch (IOException e)
                {
                    cb.Callback(null, e.toString());
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static void GetOptions(final long questionId, final ICallback<Option[]> cb)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Option[] options = new Option[30];
                for (int i = 0; i < options.length; i++)
                {
                    Option op = new Option();
                    op.setDescription("Opcao " + i);
                    op.setOrdem(i);
                    options[i] = op;
                }
                cb.Callback(options, null);
                if (false) {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://192.168.3.28:8080/Senai-LeanSurvey/question/" + questionId + "/options")
                            //.url("http://xabuco.com.br/Senai-LeanSurvey/question/" + questionId + "/options")
                            .get()
                            .build();

                    try {
                        Response resp = client.newCall(request).execute();
                        int code = resp.code();
                        String body = resp.body().string();

                        if (code != 200) {
                            cb.Callback(null, body);
                        } else {
//                        Option[] options = gson.fromJson(body, Option[].class);

                            cb.Callback(options, null);

                        }
                    } catch (IOException e) {
                        cb.Callback(null, e.toString());
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
