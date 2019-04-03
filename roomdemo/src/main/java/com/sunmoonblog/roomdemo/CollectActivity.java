package com.sunmoonblog.roomdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CollectActivity extends AppCompatActivity {

    private static final String TAG = "CollectActivity";


    private static final int FAB_STATE_INIT = 1;
    private static final int FAB_STATE_CANCEL = 2;
    private static final int FAB_STATE_EDIT = 3;

    private int mFabState = FAB_STATE_INIT;


    public static void start(Context context) {
        Intent starter = new Intent(context, CollectActivity.class);
        context.startActivity(starter);
    }

    private BottomNavigationView mNavigation;
    private LinearLayout mLlInput;
    private EditText mEditText;

    private boolean mPendingOpen;
    private boolean mPendingClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listenKeyboardVisibility(findViewById(R.id.content));

        mNavigation = findViewById(R.id.navigation);
        mLlInput = findViewById(R.id.ll_input);
        mEditText = findViewById(R.id.editText);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                showSoftKeyboard(mEditText);
            }
        });

        final ViewPager viewPager = findViewById(R.id.view_pager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(mEditText);
                String text = mEditText.getText().toString().trim();
                if (text.isEmpty()) {
                    toast("内容为空");
                } else {
                    addTask(text);
                }
                mEditText.setText("");
            }
        });

        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0, false);
                        return true;

                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(1, false);
                        return true;

                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(2, false);
                        return true;
                }
                return false;
            }
        });

    }

    private void addTask(final String text) {
        final PersonDao dao = ((App) getApplication()).getAppDatabase().getPersonDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(new Person(text, 20));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toast("添加数据成功");
                    }
                });
            }
        }).start();
    }

    private void onKeyboardOpen() {
        if (mPendingOpen) {
            return;
        }
        mPendingOpen = true;

        Log.i(TAG, "onKeyboardOpen: ");

        mLlInput.setVisibility(View.VISIBLE);
        mPendingOpen = false;

//        mLlInput.animate()
//                .setDuration(180)
//                .alpha(1)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        mLlInput.setVisibility(View.VISIBLE);
//                        mPendingOpen = false;
//                    }
//                }).start();

    }

    private void onKeyboardClose() {
        if (mPendingClose) {
            return;
        }
        mPendingClose = true;

        Log.i(TAG, "onKeyboardClose: ");

        mLlInput.setVisibility(View.INVISIBLE);
        mPendingClose = false;

//        mLlInput.animate()
//                .setDuration(180)
//                .alpha(0)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        mLlInput.setVisibility(View.INVISIBLE);
//                        mPendingClose = false;
//                    }
//                })
//                .start();

    }

    // https://stackoverflow.com/questions/4745988/how-do-i-detect-if-software-keyboard-is-visible-on-android-device
    private void listenKeyboardVisibility(final View contentView) {
        // ContentView is the root view of the layout of this activity/fragment
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        Rect r = new Rect();
                        contentView.getWindowVisibleDisplayFrame(r);
                        int screenHeight = contentView.getRootView().getHeight();

                        // r.bottom is the position above soft keypad or device button.
                        // if keypad is shown, the r.bottom is smaller than that before.
                        int keypadHeight = screenHeight - r.bottom;

                        Log.d(TAG, "keypadHeight = " + keypadHeight);

                        if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                            // keyboard is opened
                            onKeyboardOpen();
                        } else {
                            // keyboard is closed
                            onKeyboardClose();
                        }
                    }
                });
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
