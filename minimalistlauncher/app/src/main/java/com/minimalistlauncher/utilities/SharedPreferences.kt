package com.minimalistlauncher.utilities

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.minimalistlauncher.classe.appli

fun saveData(context: Context, key : String, something : String) {
    val sharedPreferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, something)
    editor.apply()
}

fun loadData(context: Context, key: String): String? {
    val sharedPreferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    return sharedPreferences.getString(key, "")
}

fun init(context: Context) {
    try {
        var nb = getNbMain(context)
    } catch (e: Exception) {
        saveData(context, "nbHidden", "0")
    }
}

fun saveAppMain(context: Context, appli: appli) {
    if (!checkInside(context, appli.packageName)) {
        var nb = getNbMain(context)
        if (nb == null) {
            nb = 0
        }
        nb++
        saveData(context, "appli||" + nb.toString(), appli.packageName)
        saveData(context, "nbHidden", nb.toString())
    }
}

fun getAllApp(context: Context): ArrayList<String> {
    var list = ArrayList<String>()
    var nb = getNbMain(context)
    if (nb != null && nb > 0) {
        for (i in 1..nb) {
            loadData(context, "appli||" + i.toString())?.let { list.add(it) }
        }
    }
    return list
}

fun checkInside(context: Context, pkname: String) : Boolean {
    var liste = getAllApp(context)
    return liste.contains(pkname)
}

fun getNbMain(context: Context) : Int? {
    return loadData(context, "nbHidden")?.toInt()
}

fun remove(context: Context, pkname : String): Boolean {
    var nb = getNbMain(context)
    if (nb != null && nb > 0) {
        for (i in 1..nb) {
            var el = loadData(context, "appli||" + i.toString())
            if (pkname == el){
                el = loadData(context, "appli||" + nb.toString())
                if (el != null) {
                    saveData(context, "appli||" + i.toString(), el)
                }
                saveData(context, "nbHidden", (nb-1).toString())
            }
        }
    }
    return true
}