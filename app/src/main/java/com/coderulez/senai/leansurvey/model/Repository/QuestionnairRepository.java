package com.coderulez.senai.leansurvey.model.Repository;

import com.coderulez.senai.leansurvey.model.Questionnaire;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.google.gson.Gson;

/**
 * Created by SENAI on 26/11/2016.
 */

/// FORNECEDOR
public class QuestionnairRepository
{
    static Gson gson = new Gson();

    public static void List(final ICallback<Questionnaire[]> cb)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();



                Request request = new Request.Builder()
                        .url("http://xabuco.com.br/Senai-LeanSurvey/questionnaire/")
                        .get()
                        .build();

                try
                {
                    Response resp = client.newCall(request).execute();
                    int code = resp.code();
                    String body = resp.body().string();

                    if (code != 200)
                    {
                        cb.Callback(null, body);
                    }
                    else
                    {
                        Questionnaire[] questionnaire = gson.fromJson(body, Questionnaire[].class);
                        cb.Callback(questionnaire, null);
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
}
