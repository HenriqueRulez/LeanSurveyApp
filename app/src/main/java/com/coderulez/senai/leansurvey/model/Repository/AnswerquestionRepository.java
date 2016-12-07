//package com.coderulez.senai.leansurvey.model.Repository;
//
//import com.coderulez.senai.leansurvey.model.Option;
//import com.coderulez.senai.leansurvey.model.Question;
//import com.google.gson.Gson;
//import com.coderulez.senai.leansurvey.model.Answerquestion;
//
//import java.io.IOException;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//
///**
// * Created by SENAI on 07/12/2016.
// */
//
//public class AnswerquestionRepository
//{
//
//    static Gson gson = new Gson();
//
//    public static void Enviar(final long answerquestionId, final ICallback<Answerquestion[]> cb){
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                OkHttpClient client = new OkHttpClient();
//
//
//                //Continuar enviar as respostas para o BACk
////                Request request = new Request.Builder()
////                        .url("")
////                        .post("")
////                        .build();
//
//
////                try {
////                    Response resp = client.newCall(request).execute();
////                    int code = resp.code();
////                    String body = resp.body().string();
////
////                    if (code != 200){
////                        cb.Callback(null, body);
////                    }else{
////
////                        Answerquestion[] questions = gson.fromJson(body, Answerquestion[].class);
////                        cb.Callback(questions, null);
////
////                    }
////                }
////                catch (IOException e)
////                {
////                    cb.Callback(null, e.toString());
////                    e.printStackTrace();
////                }
////            }
//
//
// //       }).start();
//
////    }
//
//}
