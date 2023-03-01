package com.minimalistlauncher.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.minimalistlauncher.fragments.HomeFragment
import com.minimalistlauncher.fragments.ListAppFragment
import com.minimalistlauncher.fragments.InfoFragment

/*
    used to switch screen displayed
 */
class SimpleFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                InfoFragment()
            }
            1 -> {

                HomeFragment()
            }
            else -> {
                ListAppFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}