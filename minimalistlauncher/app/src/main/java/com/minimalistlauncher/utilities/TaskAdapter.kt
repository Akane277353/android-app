package com.minimalistlauncher.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.minimalistlauncher.R
import com.minimalistlauncher.classe.appli
import com.minimalistlauncher.fragments.SettingsPopup

class TaskAdapter (
    val context: Context,
    private val list: ArrayList<String>
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txt = view.findViewById<TextView>(R.id.text)
        val layout = view.findViewById<ConstraintLayout>(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_info, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        holder.txt.text = current


        holder.layout.setOnClickListener() {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun remove(current: String) {
        list.remove(current)
        notifyDataSetChanged()
    }
}

