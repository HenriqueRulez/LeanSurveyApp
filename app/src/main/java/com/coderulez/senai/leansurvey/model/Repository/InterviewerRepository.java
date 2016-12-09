package com.coderulez.senai.leansurvey.model.Repository;
import com.coderulez.senai.leansurvey.model.Interviewer;
import android.support.v7.app.AppCompatActivity;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;

/**
 * Created by SENAI on 03/12/2016.
 */

public class InterviewerRepository extends AppCompatActivity {

    static Gson gson = new Gson();

    public static void Get(final long id, final ICallback<Interviewer> cb){

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://192.168.3.52:80/Senai-LeanSurvey/interviewer/" + id)
                        .get()
                        .build();
                try {

                    Response resp = client.newCall(request).execute();
                    int code = resp.code();
                    String body = resp.body().string();

                    if(code != 200){
                        cb.Callback(null, body);
                    }else{
                        Interviewer interviewer = gson.fromJson(body, Interviewer.class);
                        cb.Callback(interviewer,null);
                    }

                }catch  (IOException e)
                {
                    cb.Callback(null, e.toString());
                    e.printStackTrace();
                }
            }
        }).start();

    }

}


