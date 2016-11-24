package com.coderulez.senai.leansurvey.util;

import com.coderulez.senai.leansurvey.model.Questionnaire;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SENAI on 12/11/2016.
 */

public class QuestionnaireRest {

   static String url = GlobalUtil.XABUCO;


    public static List<Questionnaire> listar(Context context) throws Exception{

        String api = url + "/questionnaire";

        List<Questionnaire> qst = new ArrayList<>();

        String retorno = RestUtil.get(api, context);

        JSONArray jsonArray = new JSONArray(retorno);

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject objeto = jsonArray.getJSONObject(i);
            Questionnaire questionaire = new Questionnaire();

            questionaire.setId(objeto.getLong("id"));
            questionaire.setTitle(objeto.getString("title"));


            qst.add(questionaire);

           // System.out.println(qst);

        }

        return qst;

    }




}
