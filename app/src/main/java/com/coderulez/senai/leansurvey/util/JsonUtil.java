package com.coderulez.senai.leansurvey.util;


import android.content.Context;
import android.util.Log;

import com.coderulez.senai.leansurvey.model.Gender;
import com.coderulez.senai.leansurvey.model.Interviewer;
import com.coderulez.senai.leansurvey.model.Questionnaire;
import com.coderulez.senai.leansurvey.model.Question;
import com.coderulez.senai.leansurvey.model.Answerquestion;

import com.google.gson.Gson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by SENAI on 05/11/2016.
 */

@Deprecated
public class JsonUtil {

    static String XABUCO = "http://xabuco.com.br/Senai-LeanSurvey/";

    public static Interviewer consumeJsonInterviewer(int codigo) throws Exception {
        //String viaCep = "http://viacep.com.br/ws/01001000/json/";
        //String suaApi = "http://192.168.2.139:8080/Senai-LeanSurvey/interviewer/" + codigo;
        String api = XABUCO+"interviewer/" + codigo;

        URL endereco = new URL(api);

        HttpURLConnection urlConnection = (HttpURLConnection) endereco.openConnection();
        urlConnection.setConnectTimeout(3000);
        urlConnection.setRequestMethod("GET");

        Scanner leitor = new Scanner(urlConnection.getInputStream());

        //Não deixa dar memoryleak
        StringBuilder json = new StringBuilder();

        while (leitor.hasNextLine()) {
            json.append(leitor.nextLine());
        }

        leitor.close();

        // Converter o JSON em Interviewer
        JSONArray vetor = new JSONArray(json.toString());
        Gson parserGson = new Gson();

        List<Interviewer> entrevistadores = new ArrayList<>();

        for(int i=0; i < vetor.length(); i++) {



            Interviewer inter = parserGson.fromJson(vetor.get(i).toString(), Interviewer.class);


            entrevistadores.add(inter);

        }

        Log.d("GLAUBER", entrevistadores.toString());


        return entrevistadores.get(0);



    }


    public static Questionnaire consumeJsonQuestionnaire(int codigo) throws Exception{

        String api = XABUCO + "questionnaire/" + codigo;

        URL endereco = new URL(api);

        HttpURLConnection urlConnection = (HttpURLConnection) endereco.openConnection();
        urlConnection.setConnectTimeout(3000);
        urlConnection.setRequestMethod("GET");

        Scanner leitor = new Scanner(urlConnection.getInputStream());


        StringBuilder json = new StringBuilder();

       while (leitor.hasNextLine()){
           json.append(leitor.nextLine());
       }

        leitor.close();


        JSONArray vetor = new JSONArray(json.toString());
        Gson parserGson = new Gson();

        List<Questionnaire> questionnaire = new ArrayList<>();
        for (int i = 0; i < vetor.length(); i++){

            Questionnaire quest = parserGson.fromJson(vetor.get(i).toString(), Questionnaire.class);
            questionnaire.add(quest);
        }

        Log.d("GLAUBER", questionnaire.toString());
        return questionnaire.get(0);
    }

    public static Question consumeJsonQuestion(int codigo) throws Exception{
        String api = XABUCO + "question/" + codigo;

        URL endereco = new URL(api);

        HttpURLConnection urlConnection = (HttpURLConnection) endereco.openConnection();
        urlConnection.setConnectTimeout(3000);
        urlConnection.setRequestMethod("GET");

        Scanner leitor = new Scanner(urlConnection.getInputStream());


        StringBuilder json = new StringBuilder();

        while (leitor.hasNextLine()){
            json.append(leitor.nextLine());
        }

        leitor.close();


        JSONArray vetor = new JSONArray(json.toString());
        Gson parserGson = new Gson();

        List<Question> question = new ArrayList<>();
        for (int i = 0; i < vetor.length(); i++){

            Question quest2 = parserGson.fromJson(vetor.get(i).toString(), Question.class);
            question.add(quest2);
        }

        Log.d("GLAUBER", question.toString());
        return question.get(0);
    }

    public static Answerquestion consumeJsonAnswerquestion(int codigo) throws Exception{
        String api = XABUCO + "answerquestion/" + codigo;

        URL endereco = new URL(api);

        HttpURLConnection urlConnection = (HttpURLConnection) endereco.openConnection();
        urlConnection.setConnectTimeout(3000);
        urlConnection.setRequestMethod("GET");

        Scanner leitor = new Scanner(urlConnection.getInputStream());


        StringBuilder json = new StringBuilder();

        while (leitor.hasNextLine()){
            json.append(leitor.nextLine());
        }

        leitor.close();


        JSONArray vetor = new JSONArray(json.toString());
        Gson parserGson = new Gson();

        List<Answerquestion> answerquestion = new ArrayList<>();
        for (int i = 0; i < vetor.length(); i++){

            Answerquestion aq = parserGson.fromJson(vetor.get(i).toString(), Answerquestion.class);
            answerquestion.add(aq);
        }

        Log.d("GLAUBER", answerquestion.toString());
        return answerquestion.get(0);
    }

    public static List<Interviewer> xd() throws Exception{

        String api = XABUCO+"interviewer/";

        URL endereco = new URL(api);

        HttpURLConnection urlConnection = (HttpURLConnection) endereco.openConnection();
        urlConnection.setConnectTimeout(3000);
        urlConnection.setRequestMethod("GET");

        Scanner leitor = new Scanner(urlConnection.getInputStream());

        //Não deixa dar memoryleak
        StringBuilder json = new StringBuilder();

        while (leitor.hasNextLine()) {
            json.append(leitor.nextLine());
        }

        leitor.close();

        // Converter o JSON em Interviewer
        JSONArray vetor = new JSONArray(json.toString());
        Gson parserGson = new Gson();

        List<Interviewer> entrevistadores = new ArrayList<>();
        for(int i=0; i < vetor.length(); i++) {
            Interviewer inter = parserGson.fromJson(vetor.get(i).toString(), Interviewer.class);
            entrevistadores.add(inter);

        }

        Log.d("GLAUBER", entrevistadores.toString());


        return entrevistadores;

    }

    public static List<Questionnaire> consumeJsonQuestionnaires() throws Exception{

        String api = XABUCO + "questionnaire/";

        URL enredeco = new URL(api);

        HttpURLConnection urlConnection = (HttpURLConnection) enredeco.openConnection();
        urlConnection.setConnectTimeout(3000);
        urlConnection.setRequestMethod("GET");

        Scanner leitor = new Scanner(urlConnection.getInputStream());

        StringBuilder json = new StringBuilder();

        while(leitor.hasNext()){
            json.append(leitor.nextLine());
        }

        leitor.close();

        JSONArray vetor = new JSONArray(json.toString());
        Gson parserGson = new Gson();

        List<Questionnaire> questionnaires = new ArrayList<>();

        for (int i = 0; i < vetor.length() ; i++) {

            Questionnaire q = parserGson.fromJson(vetor.get(i).toString(), Questionnaire.class);
            questionnaires.add(q);

        }

        Log.d("GLAUBER", questionnaires.toString());

        return questionnaires;

    }






    /*
    /*
    public final String[] get(String url) {

        String[] result = new String[2];
        HttpGet httpget = new HttpGet(url);
        HttpResponse response;

        try {
            response = HttpClientSingleton.getHttpClientInstace().execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result[0] = String.valueOf(response.getStatusLine().getStatusCode());
                InputStream instream = entity.getContent();
                result[1] = toString(instream);
                instream.close();
                Log.i("get", "Result from post JsonPost : " + result[0] + " : " + result[1]);
            }
        } catch (Exception e) {
            Log.e("NGVL", "Falha ao acessar Web service", e);
            result[0] = "0";
            result[1] = "Falha de rede!";
        }
        return result;
    }
    */

}
