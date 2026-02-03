package com.example.sdui_engine.utils

import android.content.Context

fun Int.dp(context: Context): Int =
    (this * context.resources.displayMetrics.density).toInt()

fun String.dp(context: Context): Int =
    this.toIntOrNull()?.dp(context) ?: 0