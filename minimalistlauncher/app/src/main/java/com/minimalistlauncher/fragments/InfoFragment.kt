package com.minimalistlauncher.fragments

import android.os.Bundle
import android.service.notification.StatusBarNotification
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.minimalistlauncher.R


class InfoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_info, container, false)

        val layout = view?.findViewById<ConstraintLayout>(R.id.layout)
        if (layout != null) {
            layout.setBackgroundColor(getResources().getColor(R.color.black))
        }

        return view
    }

    companion object {
        var notifList = ArrayList<StatusBarNotification>()
    }
}