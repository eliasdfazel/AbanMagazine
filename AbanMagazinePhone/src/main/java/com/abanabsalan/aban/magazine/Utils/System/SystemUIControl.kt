/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 12/5/20 7:30 AM
 * Last modified 12/5/20 7:00 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.System

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.inputmethod.InputMethodManager

fun showKeyboard(context: Context, toggleView: View) {

    val inputMethodManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(toggleView, InputMethodManager.SHOW_FORCED)

}

fun hideKeyboard(context: Context, toggleView: View) {

    val inputMethodManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(toggleView.windowToken, 0)

}

fun doVibrate(context: Context, millisecondVibrate: Long) {

    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        vibrator?.vibrate(
            VibrationEffect.createOneShot(
                millisecondVibrate,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )

    } else {
        vibrator?.vibrate(millisecondVibrate)
    }
}