package com.aungbophyoe.space.statedatacodetest

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/*
* 5.9.2022
* created by aungbophyoe
* */
object Utility {
    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    fun getCurrentDateOnly(): String? {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val date = Date()
        val dd = formatter.format(date)
        val a = dd.split("/".toRegex()).toTypedArray()
        val day = a[0].toInt() + 0
        val month = a[1].toInt() + 0
        val year = a[2].toInt() + 0
        return "$day/$month/$year"
    }

    fun hideSoftKeyboard(activity: Activity) {
        val edittext = activity.currentFocus
        if (edittext != null) {
            val imm = (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            imm.hideSoftInputFromWindow(edittext.applicationWindowToken, 0)
        }
    }

    fun View.showSnackbar(snackbarText: String, timeLength: Int) {
        Snackbar.make(this, snackbarText, timeLength).show()
    }

    fun String.isEmail(): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(this).matches()
    }
}