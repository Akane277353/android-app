package com.minimalistlauncher.fragments

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.minimalistlauncher.R
import com.minimalistlauncher.classe.appli
import com.minimalistlauncher.utilities.AppliAdapter
import com.minimalistlauncher.utilities.remove
import com.minimalistlauncher.utilities.saveAppMain

class SettingsPopup(
    private val adapter: AppliAdapter,
    private val current: appli
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.settings_popup)

        //val card = findViewById<CardView>(R.id.card)
        //card.setCardBackgroundColor(Color.WHITE)

        //val frame = findViewById<FrameLayout>(R.id.frame)
        //frame.setBackgroundColor(Color.TRANSPARENT);

        //val hide = findViewById<Button>(R.id.hide)
        //val delete = findViewById<Button>(R.id.delete)
        //val home = findViewById<Button>(R.id.home)
        /*
        if (current.home) {
            //home.text = "remove from home"
        }

        hide.setOnClickListener() {
            this.dismiss()
        }

         */
/*
        delete.setOnClickListener() {
            this.dismiss()
        }

        home.setOnClickListener() {
            if (current.home) {
                remove(context, current.packageName)
                adapter.remove(current)
            } else {
                saveAppMain(context, current)
            }
            adapter.notifyDataSetChanged()
            this.dismiss()
        }

 */
    }

    fun setGravity() {
        val window = window
        val param = window!!.attributes
        param.gravity = Gravity.BOTTOM
        window!!.attributes = param
        window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
}
