package com.minimalistlauncher.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.minimalistlauncher.R
import com.minimalistlauncher.classe.appli
import com.minimalistlauncher.utilities.*


class HomeFragment : Fragment(){

    var list = ArrayList<appli>()
    var v : View? = null
    var done = false

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

        return v
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