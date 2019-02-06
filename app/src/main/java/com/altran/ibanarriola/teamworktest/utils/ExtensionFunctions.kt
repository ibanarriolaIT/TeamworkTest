package com.altran.ibanarriola.teamworktest.utils

import android.util.Base64
import java.nio.charset.StandardCharsets

fun String.generateEncodedToken() : String {
    val token = "$this:"
    var encodedAuthorization = Base64.encodeToString(
            token.toByteArray(StandardCharsets.UTF_8), Base64.NO_WRAP or Base64.URL_SAFE)
    encodedAuthorization = "Basic $encodedAuthorization"
    return encodedAuthorization
}