package com.coderulez.senai.leansurvey.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coderulez.senai.leansurvey.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnChamador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChamador = (Button) this.findViewById(R.id.idChamador);

        btnChamador.setOnClickListener(this);
    }

    @Override
    public void onClick (View v)
    {

        Intent intent = new Intent (LoginActivity.this, QuestionnairesActivity.class);

        startActivity(intent);


    }

}
