package com.coderulez.senai.leansurvey.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.coderulez.senai.leansurvey.R;


public class BaseActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(BaseActivity.this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_questionario:
                        Intent anIntent = new Intent(getApplicationContext(), QuestionnairesActivity.class);
                        startActivity(anIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_listaresposta:
                        Intent abIntent = new Intent(getApplicationContext(), OpcoesActivity.class);
                        startActivity(abIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_config:
                        Intent acIntent = new Intent(getApplicationContext(), AjudaActivity.class);
                        startActivity(acIntent);
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

}