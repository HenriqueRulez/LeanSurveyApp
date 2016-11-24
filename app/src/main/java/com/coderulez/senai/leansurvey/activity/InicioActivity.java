package com.coderulez.senai.leansurvey.activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.coderulez.senai.leansurvey.adapter.AdapterInterviewer;
import com.coderulez.senai.leansurvey.adapter.AdapterNocao;
import com.coderulez.senai.leansurvey.adapter.AdapterQuestionnaire;
import com.coderulez.senai.leansurvey.model.Model;
import com.coderulez.senai.leansurvey.model.Questionnaire;
import com.coderulez.senai.leansurvey.model.Interviewer;
import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.util.JsonUtil;
import com.coderulez.senai.leansurvey.util.QuestionnaireRest;

import java.util.ArrayList;
import java.util.List;


public class InicioActivity extends BaseActivity {

    ListView myListView;
    AdapterNocao adapterNocao;
    AdapterQuestionnaire adapterQuestionnaire;
    private List<Questionnaire> questionnaireList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_inicio, contentFrameLayout);

        myListView = (ListView) findViewById(R.id.myListView);

        List<Model> dataSource = new ArrayList<Model>();
        dataSource.add(new Model("Questionário TIM"));
        dataSource.add(new Model("Futebol é sua paixão?"));
        dataSource.add(new Model("Questionario do 321"));
        dataSource.add(new Model("Quem quer SENAI novamente?"));
        dataSource.add(new Model("Questionário FIAT"));
        dataSource.add(new Model("Questionario BBB21"));





        adapterNocao = new AdapterNocao(dataSource, this);

        myListView.setAdapter(adapterNocao);



        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

    @Override
    protected void onStart(){
        super.onStart();

        try {
            new BuscaListaQuestionarioTask().execute();
        }catch (Exception erro){
            Toast.makeText(InicioActivity.this, "Deu algum error ( endereço da API )", Toast.LENGTH_SHORT);
        }

    }

    private class BuscaListaQuestionarioTask extends AsyncTask<Void, Void, List<Questionnaire>>{

        private String erro;

        @Override
        protected List<Questionnaire> doInBackground(Void... params) {
            try {
                return questionnaireList = QuestionnaireRest.listar(InicioActivity.this);
            }catch (Exception e){
                Log.e("GLAUBER", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Infla o menu com os botões da action bar

        getMenuInflater().inflate(R.menu.menu_inicio, menu);

        return true;

    }*/



   /* @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.idMenuOpcoes:

                Intent intentOp = new Intent(this, OpcoesActivity.class);
                startActivity(intentOp);

                return true;

            case R.id.idMenuAjuda:

                Intent intentAj = new Intent(this, AjudaActivity.class);

                startActivity(intentAj);

                return true;

            case R.id.idMenuSair:

                System.exit(0);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
  }*/

}
