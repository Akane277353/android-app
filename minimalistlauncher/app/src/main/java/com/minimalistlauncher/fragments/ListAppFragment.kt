package com.minimalistlauncher.fragments

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minimalistlauncher.utilities.AppliAdapter
import com.minimalistlauncher.R
import com.minimalistlauncher.classe.appli
import com.minimalistlauncher.utilities.getApp

class ListAppFragment : Fragment(){

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.app_list, container, false)

        val layout = view?.findViewById<ConstraintLayout>(R.id.layout)
        if (layout != null) {
            layout.setBackgroundColor(getResources().getColor(R.color.black))
        }

        val list = context?.let { getApp(it) }

        if (list != null) {
            list.sortBy { it.name }
        }

        val recycler = view?.findViewById<RecyclerView>(R.id.recycler)
        val manager = LinearLayoutManager(context)
        if (recycler != null) {
            recycler.setLayoutManager(manager)
            recycler.setHasFixedSize(true)
            val adapter = list?.let { AppliAdapter(requireContext(), it) }
            recycler.setAdapter(adapter)
        }

        return view
    }

}