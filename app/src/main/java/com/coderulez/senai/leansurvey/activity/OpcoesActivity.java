package com.coderulez.senai.leansurvey.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.coderulez.senai.leansurvey.adapter.AdapterQuestionnaire;
import com.coderulez.senai.leansurvey.model.Interviewer;
import com.coderulez.senai.leansurvey.model.Questionnaire;
import com.coderulez.senai.leansurvey.util.Conexao;
import com.coderulez.senai.leansurvey.util.InterviewerRest;
import com.coderulez.senai.leansurvey.util.JsonUtil;
import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.util.QuestionnaireRest;

import java.util.List;

public class OpcoesActivity extends BaseActivity {

    private Interviewer entrevistador, entrevistador1;
    private List<Interviewer> listaEntrevistadores;
    private List<Questionnaire> listaQuestionarios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_opcoes, contentFrameLayout);

        try {
            if(Conexao.temConexao(this) && Conexao.temPermissao(this)) {
                    new ClasseInternaParaConsulta().execute();
                    Log.d("GLAUBER", "Encontrou " + entrevistador.getName());
                } else {
                    // Avisar que deu erro
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ClasseInternaParaConsulta extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                entrevistador = JsonUtil.consumeJsonInterviewer(1);
                Log.d("GLAUBER", "1");
                listaEntrevistadores = JsonUtil.xd();
                Log.d("GLAUBER", "2");
                listaQuestionarios = JsonUtil.consumeJsonQuestionnaires();
                Log.d("GLAUBER", "3");
                QuestionnaireRest.listar(OpcoesActivity.this);
                Log.d("GLAUBER", "4");
                InterviewerRest.listar(OpcoesActivity.this);
                Log.d("GLAUBER", "5");

            } catch (Exception erro) {
                Log.d("GLAUBER", "Erro consultando JSON: " + erro.getMessage());
                erro.printStackTrace();
            }
            return null;
        }
    }
}
