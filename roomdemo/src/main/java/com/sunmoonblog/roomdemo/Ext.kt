package com.sunmoonblog.roomdemo

import android.content.Context
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

const val TAG = "Ext"

fun AppCompatActivity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun AppCompatActivity.hideSoftKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

// https://stackoverflow.com/questions/4745988/how-do-i-detect-if-software-keyboard-is-visible-on-android-device
fun AppCompatActivity.listenKeyboardVisibility(contentView: View) {
    // ContentView is the root view of the layout of this activity/fragment
    contentView.viewTreeObserver.addOnGlobalLayoutListener {
        val r = Rect()
        contentView.getWindowVisibleDisplayFrame(r)
        val screenHeight = contentView.rootView.height

        // r.bottom is the position above soft keypad or device button.
        // if keypad is shown, the r.bottom is smaller than that before.
        val keypadHeight = screenHeight - r.bottom

        Log.d(TAG, "keypadHeight = $keypadHeight")

        if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
            // keyboard is opened
            if (this is KeyboardMonitor) {
                onKeyboardOpen()
            } else {
                Log.w(TAG, "$javaClass forget to implements KeyboardMonitor?")
            }
        } else {
            // keyboard is closed
            if (this is KeyboardMonitor) {
                onKeyboardClose()
            } else {
                Log.w(TAG, "$javaClass forget to implements KeyboardMonitor?")
            }
        }
    }
}

interface KeyboardMonitor {
    fun onKeyboardOpen()
    fun onKeyboardClose()
}