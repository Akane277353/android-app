package com.minimalistlauncher.utilities

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo

import com.minimalistlauncher.classe.appli

fun getApp(context: Context): ArrayList<appli> {
    val pm = context.packageManager
    val list = ArrayList<appli>()
    val packList: List<PackageInfo> = context.getPackageManager().getInstalledPackages(0)
    for (i in packList.indices) {
        val packInfo = packList[i]
        if (packInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
            val appName = packInfo.applicationInfo.loadLabel( context.getPackageManager()).toString()
            val packageName = packInfo.applicationInfo.packageName
            val img = packInfo.applicationInfo.loadIcon(context.getPackageManager())
            list.add(appli(appName, img, packageName, false))
        }
    }
    return list
}