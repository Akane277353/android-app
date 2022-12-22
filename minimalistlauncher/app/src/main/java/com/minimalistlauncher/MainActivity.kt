package com.minimalistlauncher

import com.minimalistlauncher.utilities.SimpleFragmentPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

    }

}