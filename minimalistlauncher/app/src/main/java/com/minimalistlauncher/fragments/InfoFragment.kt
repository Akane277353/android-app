package com.minimalistlauncher.fragments

import android.os.Bundle
import android.service.notification.StatusBarNotification
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minimalistlauncher.R
import com.minimalistlauncher.utilities.AppliAdapter
import com.minimalistlauncher.utilities.NoteAdapter
import com.minimalistlauncher.utilities.TaskAdapter


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

        val list: ArrayList<String> = arrayListOf("ok", "oui", "pas beau")
        val list2: ArrayList<String> = arrayListOf("ok", "oui", "pas beau")


        val reminder = view?.findViewById<RecyclerView>(R.id.reminder)
        val m1 = LinearLayoutManager(context)
        if (reminder != null) {
            reminder.setLayoutManager(m1)
            reminder.setHasFixedSize(true)
            val adapter = list?.let { TaskAdapter(requireContext(), it) }
            reminder.setAdapter(adapter)
        }

        val note = view?.findViewById<RecyclerView>(R.id.note)
        val m2 = LinearLayoutManager(context)
        if (note != null) {
            note.setLayoutManager(m2)
            note.setHasFixedSize(true)
            val adapter = list2?.let { NoteAdapter(requireContext(), it) }
            note.setAdapter(adapter)
        }

        return view
    }

    companion object {
        var notifList = ArrayList<StatusBarNotification>()
    }
}