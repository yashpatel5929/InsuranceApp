package com.example.insuranceapp.presentation.extensions

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

fun String.Companion.empty() = ""
fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()


val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@#.!])(?=\\S+$).{8,}$"
val pattern: Pattern = Pattern.compile(PASSWORD_PATTERN)

fun String.isValidPassword() = pattern.matcher(this).matches()

fun String.isNotValidPassword() = !pattern.matcher(this).matches()
