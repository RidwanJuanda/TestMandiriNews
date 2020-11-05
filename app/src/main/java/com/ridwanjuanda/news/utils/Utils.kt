package com.ridwanjuanda.news.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Suppress("DEPRECATION")
fun getHtmlFromString(text: String?): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text ?: "", Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text ?: "")
    }
}

fun isNotNull(text: String?): Boolean {
    var tempText: String? = text ?: return false
    tempText = tempText!!.trim { it <= ' ' }
    return tempText != ""
}

fun isNotNull(text: CharSequence?): Boolean {
    if (text == null) {
        return false
    }
    val newText = text.toString().trim { it <= ' ' }
    return newText != ""
}

fun isNotNull(list: List<*>?): Boolean {
    return list != null && list.isNotEmpty()
}

fun isNotNull(list: Array<out String>?): Boolean {
    return list != null && list.isNotEmpty()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}