package com.sunmoonblog.roomdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtName;
    private EditText mEtAge;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtName = findViewById(R.id.editText);
        mEtAge = findViewById(R.id.editText2);
        mBtnSubmit = findViewById(R.id.button);
        mBtnSubmit.setOnClickListener(this);

        PersonListFragment fragment = (PersonListFragment) getSupportFragmentManager()
                .findFragmentByTag("content");
        if (fragment == null) {
            fragment = new PersonListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment, "content")
                    .commit();
        }

        findViewById(R.id.preview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PersonListActivity.start(MainActivity.this);
                CollectActivity.start(MainActivity.this);
//                HomeActivity.start(MainActivity.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        final String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            toast("名字不能为空");
            return;
        }
        String age = mEtAge.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            toast("年龄不能为空");
            return;
        }
        try {
            final int ageInt = Integer.parseInt(age);
            final PersonDao dao = ((App) getApplication()).getAppDatabase().getPersonDao();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dao.insertAll(new Person(name, ageInt));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toast("添加数据成功");
                        }
                    });
                }
            }).start();
        } catch (NumberFormatException e) {
            toast("年龄不为数字");
        }
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
