package com.minimalistlauncher.fragments

import android.annotation.SuppressLint
import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minimalistlauncher.R
import com.minimalistlauncher.classe.appli
import com.minimalistlauncher.utilities.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(){

    var list = ArrayList<appli>()
    var v : View? = null
    var done = false
    val c: Calendar = Calendar.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater?.inflate(R.layout.fragment_home, container, false)

        val layout = v?.findViewById<LinearLayout>(R.id.layout)
        if (layout != null) {
            layout.setBackgroundColor(getResources().getColor(R.color.black))
        }

        init(requireContext())
        refresh()
        done = true

        val time = v?.findViewById<TextView>(R.id.time)
        val battery = v?.findViewById<ProgressBar>(R.id.progressBar)
        if (time != null && battery != null) {
            updateUI(time, battery)
        }

        return v
    }

    fun updateUI(txt: TextView, bat: ProgressBar) {
        GlobalScope.launch(Dispatchers.Main) {
            val bm = context?.getSystemService(BATTERY_SERVICE) as BatteryManager
            while (true) {
                val sdf = SimpleDateFormat("HH:mm:ss")
                txt.text = sdf.format(Date())
                val batLevel:Int = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
                bat.setProgress(batLevel)
                delay(10000)
            }
        }
    }

    fun refresh() {
        list = ArrayList<appli>()
        var app = getApp(requireContext())
        var home = getAllApp(requireContext())

        if (!home.isEmpty()) {
            for (j in 0..app.size-1) {
                for (i in 0..home.size-1) {
                    if (app[j].packageName == home[i]) {
                        app[j].home = true
                        list.add(app[j])
                    }
                }
            }
        }
        update()
    }

    fun update() {
        if (!list.isEmpty()) {
            list.sortBy { it.name }
            val recycler = v?.findViewById<RecyclerView>(R.id.recycler)
            val manager = LinearLayoutManager(context)
            if (recycler != null) {
                recycler.layoutManager = manager
                recycler.setHasFixedSize(true)
                val adapter = AppliAdapter(requireContext(), list)
                recycler.adapter = adapter

            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (done)
            {
                refresh()
            }
        }
    }

}