package com.minimalistlauncher.utilities

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun requestPermission(context: Context) {
    if (context?.let {
            ContextCompat.checkSelfPermission(
                it, android.Manifest.permission.SEND_SMS)
        }
        != PackageManager.PERMISSION_GRANTED) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, android.Manifest.permission.SEND_SMS)) {

        } else {
            ActivityCompat.requestPermissions(context as Activity,
                arrayOf(android.Manifest.permission.SEND_SMS), 1)
        }
    } else {

    }
}