package com.coderulez.senai.leansurvey.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;

import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.model.Answerquestion;
import com.coderulez.senai.leansurvey.model.Question;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TextAnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TextAnswerFragment extends Fragment implements IAnswerFragment {

    private OnFragmentInteractionListener mListener;
    EditText etAnswer;
    public TextAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View result = inflater.inflate(R.layout.activity_questao_it, container, false);
        etAnswer = (EditText) result.findViewById(R.id.editTextBox);

        if(_shouldRefresh)
        {
            _shouldRefresh = false;

            ((TextView)result.findViewById(R.id.txtTextTitle)).setText(_questionToRefresh.getTitle());
            ((TextView)result.findViewById(R.id.txtTextDescription)).setText(_questionToRefresh.getDescription());
        }
        return  result;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private boolean _shouldRefresh;
    private Question _questionToRefresh;
    @Override
    public void Refresh(Question q) {
        View view = this.getView();
        if (view != null)
        {
            ((TextView)view.findViewById(R.id.txtTextTitle)).setText(q.getTitle());
            ((TextView)view.findViewById(R.id.txtTextDescription)).setText(q.getDescription());
        }
        else
        {
            _questionToRefresh = q;
            _shouldRefresh = true;

        }
    }

    @Override
    public Answerquestion getAnswer()
    {
        String text = etAnswer.getText().toString();
        if (text.isEmpty()) return null;

        Answerquestion answer = new Answerquestion();
        answer.setAnswerString(text);
        return  answer;
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
