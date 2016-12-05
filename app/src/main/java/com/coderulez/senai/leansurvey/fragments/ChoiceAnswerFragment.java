package com.coderulez.senai.leansurvey.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ListView;

import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.adapter.AdapterQuestionCB;
import com.coderulez.senai.leansurvey.adapter.AdapterQuestionRB;
import com.coderulez.senai.leansurvey.model.Option;
import com.coderulez.senai.leansurvey.model.Question;
import com.coderulez.senai.leansurvey.model.Repository.ICallback;
import com.coderulez.senai.leansurvey.model.Repository.QuestionRepository;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChoiceAnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ChoiceAnswerFragment extends Fragment implements IAnswerFragment {

    private OnFragmentInteractionListener mListener;

    public ChoiceAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_question_rb, container, false);
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

    @Override
    public void Refresh(Question q) {
        ((TextView) getView().findViewById(R.id.txtTitleRB)).setText(q.getTitle());
        ((TextView) getView().findViewById(R.id.txtTextDescriptionRB)).setText(q.getDescription());

        QuestionRepository.GetOptions(q.getId(), new ICallback<Option[]>() {
            @Override
            public void Callback(final Option[] back, String error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((ListView)getView().findViewById(R.id.listViewRB)).setAdapter(new AdapterQuestionRB(getActivity().getApplicationContext(), back));
                    }
                });
            }
        });
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
