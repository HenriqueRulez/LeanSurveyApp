package com.coderulez.senai.leansurvey.model;

/**
 * Created by SENAI on 03/11/2016.
 */

public class Model
{
    public Model(String titulo)
    {
        this.Titulo = titulo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    String Titulo;
}
