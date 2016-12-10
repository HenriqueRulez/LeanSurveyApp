package com.coderulez.senai.leansurvey.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.coderulez.senai.leansurvey.activity.dummy.DummyContent;
import com.coderulez.senai.leansurvey.fragments.AjudaFragment;
import com.coderulez.senai.leansurvey.fragments.ErrorFragment;
import com.coderulez.senai.leansurvey.fragments.QuestionairesFragment;
import com.coderulez.senai.leansurvey.model.Questionnaire;
import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.util.QuestionnaireRest;
import 	android.view.View;

import java.util.List;
import android.app.Fragment;
import android.app.FragmentManager;


public class QuestionnairesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, QuestionairesFragment.OnListFragmentInteractionListener, ErrorFragment.OnFragmentInteractionListener{

    FrameLayout content;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_drawer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(this);

        content = (FrameLayout) findViewById(R.id.content_frame);

        QuestionairesFragment fragment = new QuestionairesFragment();

        this.ShowFragment(fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment;
        View view;
        switch (item.getItemId()) {
            case R.id.nav_questionario:

                fragment = new QuestionairesFragment();
                break;
            case R.id.nav_listaresposta:
                fragment = new AjudaFragment();
                break;
            case R.id.nav_config:
                fragment = new ErrorFragment();
                break;
            default:
                fragment = new ErrorFragment();
                break;
        }
        SetActive(item.getItemId());
        this.ShowFragment(fragment);
        drawerLayout.closeDrawers();

        return false;
    }

    private void SetActive(int id)
    {
        //TODO DEIXAR APENAS SELECIONADO O DO ID QUE AGENTE MANDOU
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    private void ShowFragment(Fragment fragment)
    {
        this.getFragmentManager()
            .beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item)
    {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
