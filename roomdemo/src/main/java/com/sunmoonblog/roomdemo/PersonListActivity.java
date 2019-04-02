package com.sunmoonblog.roomdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class PersonListActivity extends AppCompatActivity {

    private static final String TAG = "PersonListActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, PersonListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        PersonListFragment fragment = (PersonListFragment) getSupportFragmentManager().findFragmentByTag("content");
        if (fragment == null) {
            fragment = new PersonListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment, "content")
                    .commit();
        }

    }
}
