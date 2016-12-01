package com.coderulez.senai.leansurvey.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.fragments.ChoiceAnswerFragment;
import com.coderulez.senai.leansurvey.fragments.MultipleAnswerFragment;
import com.coderulez.senai.leansurvey.fragments.TextAnswerFragment;
import com.coderulez.senai.leansurvey.model.Questionnaire;
import com.coderulez.senai.leansurvey.model.Question;
import com.coderulez.senai.leansurvey.model.Repository.ICallback;
import com.coderulez.senai.leansurvey.model.Repository.QuestionRepository;
import com.coderulez.senai.leansurvey.model.Repository.QuestionnaireRepository;

import android.app.Fragment;

/**
 * Created by SENAI on 01/12/2016.
 */

public class AnswerActivity extends AppCompatActivity
{
    long questionnaireId = 0;
    Questionnaire questionnaire;
    Question[] questions;
    int questionIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        questionnaireId = getIntent().getExtras().getLong("questionnaire");

    }


    @Override
    protected void onResume()
    {
        super.onResume();
        final AnswerActivity that = this;

        QuestionnaireRepository.Get(questionnaireId, new ICallback<Questionnaire>() {
            @Override
            public void Callback(Questionnaire back, String error) {
                questionnaire = back;
                questionnaire.getQuestions(new ICallback<Question[]>() {
                    @Override
                    public void Callback(Question[] back, String error) {
                        if (back == null)
                        {
                            Toast.makeText(that, error, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            questions = back;
                            Refresh();
                        }
                    }
                });
            }
        });

    }

    public void Refresh()
    {
        Question question = this.questions[questionIndex];
        Fragment frag = GetFragmentFor(question);
    }

    private void showFragment(Fragment fragment)
    {
        this.getFragmentManager().beginTransaction()
                .replace(R.id.content_question, fragment)
                .commit();
    }

    private Fragment GetFragmentFor(Question question)
    {
        Fragment result;
        switch (question.getQuestiontype().getId().intValue())
        {
            case 3: //radioButton
                result = new ChoiceAnswerFragment();
                break;
            case 2: //checkbutton
                result = new MultipleAnswerFragment();
                break;
            default:
                result = new TextAnswerFragment();
                break;
        }
        return  result;
    }
}
