package com.coderulez.senai.leansurvey.fragments;


import com.coderulez.senai.leansurvey.model.Question;
import android.app.Fragment;
import com.coderulez.senai.leansurvey.model.Answerquestion;
/**
 * Created by SENAI on 03/12/2016.
 */

public interface IAnswerFragment {

    void Refresh(Question q);

    Answerquestion getAnswer();

    Fragment getFragment();
}
