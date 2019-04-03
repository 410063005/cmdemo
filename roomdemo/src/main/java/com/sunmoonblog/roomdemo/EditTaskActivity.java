package com.sunmoonblog.roomdemo;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

public class EditTaskActivity extends AppCompatActivity {

    private static final String EXTRA_USER_ID = "com.sunmoonblog.roomdemo.EXTRA_USER_ID";

    public static void start(Context context, long id) {
        Intent starter = new Intent(context, EditTaskActivity.class);
        starter.putExtra(EXTRA_USER_ID, id);
        context.startActivity(starter);
    }

    private TextView mTvDate;
    private EditText mEtTitle;
    private EditText mEtContent;

    private Person mBackup;

    private PersonDao mPersonDao;

    private long mNewDate;

    private AtomicBoolean mModifying = new AtomicBoolean(false);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTvDate = findViewById(R.id.date);
        mEtTitle = findViewById(R.id.task_title);
        mEtContent = findViewById(R.id.task_content);

        AppDatabase db = ((App) getApplication()).getAppDatabase();
        mPersonDao = db.getPersonDao();

        mPersonDao.getById(getIntent().getLongExtra(EXTRA_USER_ID, 0))
                .observe(this, new Observer<Person>() {
                    @Override
                    public void onChanged(@Nullable Person person) {
                        if (person != null) {
                            mBackup = person;
                            mTvDate.setText(Utils.formatDate(person.getDate()));
                            mEtTitle.setText(person.getName());
                            mEtTitle.setSelection(mEtTitle.getText().length());

                            if (mModifying.get()) {
                                toast("修改成功");
                                finish();
                            }
                        }
                    }
                });

        mTvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();

                new DatePickerDialog(EditTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                Calendar c = Calendar.getInstance();
                                c.set(year, month, dayOfMonth);

                                mNewDate = c.getTime().getTime();

                                mTvDate.setText(Utils.formatDate(mNewDate));

                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {

            if (mBackup != null) {
                if (TextUtils.equals(mEtTitle.getText(), mBackup.getName()) &&
                        mBackup.getDate() == mNewDate) {
                    return true;
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            mBackup.setName(mEtTitle.getText().toString());
                            mBackup.setDate(mNewDate);
                            mPersonDao.update(mBackup);

                            mModifying.set(true);
                        }
                    }).start();
                }
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
