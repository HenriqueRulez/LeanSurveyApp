package com.coderulez.senai.leansurvey.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.fragments.ChoiceAnswerFragment;
import com.coderulez.senai.leansurvey.fragments.IAnswerFragment;
import com.coderulez.senai.leansurvey.fragments.MultipleAnswerFragment;
import com.coderulez.senai.leansurvey.fragments.TextAnswerFragment;
import com.coderulez.senai.leansurvey.model.Questionnaire;
import com.coderulez.senai.leansurvey.model.Question;
import com.coderulez.senai.leansurvey.model.Answerquestion;
import com.coderulez.senai.leansurvey.model.Repository.ICallback;
import com.coderulez.senai.leansurvey.model.Repository.QuestionRepository;
import com.coderulez.senai.leansurvey.model.Repository.QuestionnaireRepository;

import android.app.Fragment;

/**
 * Created by SENAI on 01/12/2016.
 */

public class AnswerActivity extends AppCompatActivity implements MultipleAnswerFragment.OnFragmentInteractionListener, ChoiceAnswerFragment.OnFragmentInteractionListener, TextAnswerFragment.OnFragmentInteractionListener, View.OnClickListener {

    long questionnaireId = 0;
    Questionnaire questionnaire;
    Question[] questions;
    Answerquestion[] answers;
    int questionIndex = 0;
    IAnswerFragment currentFragment;

    Button btnNext, btnPrevious;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_answer);

        btnNext = (Button)findViewById(R.id.proxima_questao);
        btnPrevious  = (Button)findViewById(R.id.questao_anterior);


        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);

        questionnaireId = getIntent().getExtras().getLong("questionnaire");

    }


    @Override
    protected void onResume()
    {
        super.onResume();
        final AnswerActivity that = this;

        QuestionnaireRepository.Get(questionnaireId, new ICallback<Questionnaire>() {
            @Override
            public void Callback(final Questionnaire back, String error) {
                questionnaire = back;
                questionnaire.getQuestions(new ICallback<Question[]>() {
                    @Override
                    public void Callback(final Question[] questions, String error) {
                        if (back == null)
                        {
                            // Toast.makeText(that, error, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    AnswerActivity.this.questions = questions;
                                    AnswerActivity.this.answers = new Answerquestion[questions.length];
                                    Refresh();

                                }
                            });
                        }
                    }
                });
            }
        });

    }

    public void Refresh()
    {
        if(questions.length != 0){

            Question question = this.questions[questionIndex];
            IAnswerFragment frag = GetFragmentFor(question);
            showFragment(frag);
            frag.Refresh(questions[questionIndex]);
        }
    }


    private void showFragment(IAnswerFragment fragment)
    {
        this.getFragmentManager().beginTransaction()
                .replace(R.id.content_question, fragment.getFragment())
                .commit();
        this.currentFragment = fragment;
    }

    private IAnswerFragment GetFragmentFor(Question question)
    {
        IAnswerFragment result;
        switch (question.getQuestiontype().getId().intValue())
        {
            case 3: //radioButton
                result = new ChoiceAnswerFragment();
                break;
            case 2: //checkButton
                result = new MultipleAnswerFragment();
                break;
            case 1: //Text
                result = new TextAnswerFragment();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.proxima_questao)
        {
            //Gravar resposta e ir para a proxima questao
            if(currentFragment == null)
            {
                Toast.makeText(this, "Não há uma questão", Toast.LENGTH_SHORT).show();
            }
            else if(questionIndex + 1 == this.questions.length)
            {
                Toast.makeText(this, "Não hã próxima questão", Toast.LENGTH_SHORT).show();
            }
            else
            {

                Answerquestion resposta = currentFragment.getAnswer();
                if (resposta == null)
                {
                    Toast.makeText(this, "Por favor, responda a pergunta atual", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    answers[questionIndex++] = resposta;
                    Refresh();
                }
            }
        }
        else if (v.getId() == R.id.questao_anterior)
        {//voltar a questao
            if (questionIndex == 0)
            {
                Toast.makeText(this, "Não hã uma questão anterior", Toast.LENGTH_SHORT).show();
            }
            else
            {
                questionIndex--;
                Refresh();
            }
        }
    }
}
