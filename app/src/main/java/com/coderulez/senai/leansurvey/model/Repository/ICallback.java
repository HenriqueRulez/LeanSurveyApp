package com.coderulez.senai.leansurvey.model.Repository;

/**
 * Created by SENAI on 26/11/2016.
 */

public interface ICallback<T>
{
    void Callback(T back, String error);
}
