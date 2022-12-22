package com.minimalistlauncher.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.minimalistlauncher.R
import com.minimalistlauncher.fragments.SettingsPopup
import com.minimalistlauncher.classe.appli

class AppliAdapter(
    val context: Context,
    private val list: ArrayList<appli>
    ) : RecyclerView.Adapter<AppliAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img = view.findViewById<ImageView>(R.id.image)
        val txt = view.findViewById<TextView>(R.id.text)
        val layout = view.findViewById<ConstraintLayout>(R.id.layout)
        val card = view.findViewById<CardView>(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_info, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        holder.txt.text = current.name
        holder.img.setImageDrawable(current.image)

        val cont = this

        holder.layout.setOnClickListener() {
            var intent: Intent? = context.getPackageManager().getLaunchIntentForPackage(current.packageName)
            if (intent != null) {
                // We found the activity now start the activity
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } else {
                // Bring user to the market or let them choose an app?
                intent = Intent(Intent.ACTION_VIEW)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.data = Uri.parse("market://details?id=" + current.packageName)
                context.startActivity(intent)
            }
        }

        holder.layout.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                var popup = SettingsPopup(cont, current)
                popup.setGravity()
                popup.show()
                println("created")
                return true
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun remove(current: appli) {
        list.remove(current)
        notifyDataSetChanged()
    }
}

