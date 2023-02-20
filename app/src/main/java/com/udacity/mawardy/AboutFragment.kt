package com.udacity.mawardy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.udacity.mawardy.base.BaseFragment
import com.udacity.mawardy.databinding.FragmentAboutBinding


class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override val layout: Int
        get() = R.layout.fragment_about

}