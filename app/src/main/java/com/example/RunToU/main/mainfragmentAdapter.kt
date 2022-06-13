package com.example.RunToU.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.RunToU.status.statusFragment
import com.example.RunToU.chat_match.msgFragment
import com.example.RunToU.profile.myProfileFragment
import com.example.RunToU.ordersheet.workFragment


class mainfragmentAdapter(fm : FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return workFragment()
            1 -> return statusFragment()
            2 -> return msgFragment()
            3 -> return myProfileFragment()

            else -> return workFragment()
        }
    }

    override fun getCount(): Int = fragmentCount // 자바에서는 { return fragmentCount }

}