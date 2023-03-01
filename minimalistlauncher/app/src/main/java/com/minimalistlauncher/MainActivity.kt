package com.minimalistlauncher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.minimalistlauncher.utilities.SimpleFragmentPagerAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        viewPager = findViewById<ViewPager>(R.id.viewpager)
        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.currentItem = 1
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 1) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}