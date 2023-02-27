package com.minimalistlauncher.utilities

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.minimalistlauncher.classe.appli

/*
    Save data in shared preferences
    key: used to access the data
    something: the info we want to save
 */
fun saveData(context: Context, key : String, something : String) {
    val sharedPreferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, something)
    editor.apply()
}
/*
    Load date in the shared preferences
    key: what data we want to access
 */
fun loadData(context: Context, key: String): String? {
    val sharedPreferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    return sharedPreferences.getString(key, "")
}

/*
    get the number of app stored if it exist
 */
fun init(context: Context) {
    try {
        var nb = getNbMain(context)
    } catch (e: Exception) {
        saveData(context, "nbHidden", "0")
    }
}

/*
    save app in main screen
 */
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

/*
    get all the app saved in shared preferences
 */
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

/*
    check if an app is saved
 */
fun checkInside(context: Context, pkname: String) : Boolean {
    var liste = getAllApp(context)
    return liste.contains(pkname)
}

/*
    get the number of app
 */
fun getNbMain(context: Context) : Int? {
    return loadData(context, "nbHidden")?.toInt()
}

/*
    remove an app
 */
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