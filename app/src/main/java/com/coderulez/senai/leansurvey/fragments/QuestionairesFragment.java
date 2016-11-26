package com.coderulez.senai.leansurvey.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.activity.dummy.DummyContent.DummyItem;
import com.coderulez.senai.leansurvey.adapter.AdapterQuestionnaire;
import com.coderulez.senai.leansurvey.model.Questionnaire;
import com.coderulez.senai.leansurvey.model.Repository.ICallback;
import com.coderulez.senai.leansurvey.model.Repository.QuestionnairRepository;

import android.*;
import android.widget.Toast;

import okhttp3.internal.framed.FrameReader;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class QuestionairesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ListView listView;

    public AdapterQuestionnaire getAdapter() {
        return adapter;
    }

    public AdapterQuestionnaire adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestionairesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static QuestionairesFragment newInstance(int columnCount) {
        QuestionairesFragment fragment = new QuestionairesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_inicio, container, false);

        Context context = view.getContext();
        listView = (ListView) view.findViewById(R.id.myListView);
        this.adapter = new AdapterQuestionnaire(this.getContext());
        listView.setAdapter(adapter);

        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }



        final Fragment that = this;

        QuestionnairRepository.List(new ICallback<Questionnaire[]>()
        {
            @Override
            public void Callback(final Questionnaire[] back, final String error)
            {
                new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable()
                {
                    @Override
                    public void run() {
                        if (error != null)
                        {
                            Toast.makeText(that.getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            getAdapter().SetItems(back);
                            listView.invalidate();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
