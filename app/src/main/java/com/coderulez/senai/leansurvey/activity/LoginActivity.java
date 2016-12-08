package com.coderulez.senai.leansurvey.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.coderulez.senai.leansurvey.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnChamador;
    EditText email, senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChamador = (Button) this.findViewById(R.id.idChamador);

        btnChamador.setOnClickListener(this);

        email = (EditText) findViewById(R.id.editText);
        senha = (EditText) findViewById(R.id.editText2);
    }

    @Override
    public void onClick (View v)
    {

            if((email.getText().toString().equals("adm") && (senha.getText().toString().equals("adm")))){

                Intent intent = new Intent (LoginActivity.this, QuestionnairesActivity.class);

                startActivity(intent);


            }else{

                Toast.makeText(this, "Email ou senha inválido", Toast.LENGTH_SHORT).show();

            }




            



    }

}
