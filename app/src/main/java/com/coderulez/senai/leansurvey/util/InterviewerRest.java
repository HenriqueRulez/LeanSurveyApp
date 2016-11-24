package com.coderulez.senai.leansurvey.util;

import android.content.Context;
import android.util.Log;

import com.coderulez.senai.leansurvey.model.Gender;
import com.coderulez.senai.leansurvey.model.Interviewer;
import com.coderulez.senai.leansurvey.model.Questionnaire;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henrique_Rulez on 13/11/2016.
 */

public class InterviewerRest {


    static String url = GlobalUtil.XABUCO;


    public static List<Interviewer> listar(Context context) throws Exception{

        List<Interviewer> interviewers = new ArrayList<>();


        String api = "http://www.xabuco.com.br/Senai-LeanSurvey/interviewer";

        String retorno = RestUtil.get(api, context);
        
        // Evandro disse para trocar isso JSONArray por isso
           
        JSONArray jsonArray = new JSONArray(retorno);  //Objeto ob = new ObjectMapper().readValue(jsonString, Objeto.class)

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);

            Interviewer interviewer = new Interviewer();
            Gender gender = new Gender();

            interviewer.setId(objeto.getLong("id"));
            interviewer.setName(objeto.getString("name"));

            //JSONArray array = objeto.getJSONArray("gender");
//
//            interviewer.setGender(new Gender());
//
//            for (int j = 0; j < array.length(); j++) {
//
//                Gender gender = new Gender();
//
//                JSONObject genderObject = array.getJSONObject(j);
//
//                gender.setId(genderObject.getLong("id"));
//                gender.setDescription(genderObject.getString("description"));
//
//                interviewer.setGender(gender);
//            }

                gender.setId(objeto.getLong("id"));
                gender.setDescription(objeto.getString("description"));


            interviewer.setGender(gender);



            interviewers.add(interviewer);

            Log.e("ESSE", interviewers.toString());
            Log.w("ESSE", interviewers.toString());
        }


        return interviewers;

    }



}
