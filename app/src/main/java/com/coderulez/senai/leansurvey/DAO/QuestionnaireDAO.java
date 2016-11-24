package com.coderulez.senai.leansurvey.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coderulez.senai.leansurvey.model.Questionnaire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henrique_Rulez on 15/11/2016.
 */

public class QuestionnaireDAO extends SQLiteOpenHelper{

    public QuestionnaireDAO(Context context){
        //                Nome do BD        Versão
        super(context, "LeanSurveyApp", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql =
                "CREATE TABLE questionnaire (" +
                "id LONG PRIMARY KEY," +
                "employee LONG NOT NULL," +
                "enterprise LONG NOT NULL, " +
                "title VARCHAR, " +
                "interviewerPayment NUMERIC, " +
                "startDate DATE, " +
                "endDate DATE, " +
                "limite INTEGER);";

        db.execSQL(sql);

    }

    //Só roda se modificar a versão do BD no construtor
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS questionnaire;";
        db.execSQL(sql);

        onCreate(db);

    }

    //PreparedStatement 2.0
    public void insert(Questionnaire q){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();

        //Verificar como passar como parâmetro o JSON

        /*
        dados.put("employee", q.getEmployee());
        dados.put("enterprise", q.getEnterprise());
        dados.put("title", q.getTitle());
        dados.put("interviewerPayment", q.getInterviewerPayment());
        dados.put("startDate", q.getStartDate());
        dados.put("endDate", q.getEndDate());
        dados.put("limite", q.getLimite());
        */

        db.insert("questionnaire", null, dados);

    }

    //Acredito que não vai precisar disso

    @Deprecated
    public List<Questionnaire> listarNomeId(){

        String sql = "SELECT id, title FROM questionnaire;";

        SQLiteDatabase db = getReadableDatabase();

        //Trás resultados em um cursor
        Cursor cursor = db.rawQuery(sql, null);

        List<Questionnaire> questionnaires = new ArrayList<>();

        while(cursor.moveToNext()){
            Questionnaire q = new Questionnaire();

            q.setId(cursor.getLong(cursor.getColumnIndex("id")));
            q.setTitle(cursor.getString(cursor.getColumnIndex("title")));

            questionnaires.add(q);


        }

        cursor.close();

        return questionnaires;

    }
}
