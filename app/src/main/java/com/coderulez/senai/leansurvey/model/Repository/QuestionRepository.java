package com.coderulez.senai.leansurvey.model.Repository;

import com.google.gson.Gson;
import com.coderulez.senai.leansurvey.model.Question;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SENAI on 30/11/2016.
 */

public class QuestionRepository {

    static Gson gson = new Gson();

    public static void List(final ICallback<Question> cb){

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://xabuco.com.br/Senai-LeanSurvey/question/")
                        .get()
                        .build();

                try {
                    Response resp = client.newCall(request).execute();
                    int code = resp.code();
                    String body = resp.body().string();

                    if (code != 200){
                        cb.Callback(null, body);
                    }else{

                        Question question = gson.fromJson(body, Question.class);
                        cb.Callback(question,null);

                    }

                }catch (IOException e)
                {
                    cb.Callback(null, e.toString());
                    e.printStackTrace();
                }

            }
        }).start();

    }

}
