package com.udacity.mawardy.topics

import android.os.Bundle
import android.view.View
import com.udacity.mawardy.R
import com.udacity.mawardy.base.BaseFragment
import com.udacity.mawardy.databinding.FragmentTopicsBinding


class TopicsFragment : BaseFragment<FragmentTopicsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_topics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}