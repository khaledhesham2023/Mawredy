package com.udacity.mawardy.topicdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.udacity.mawardy.R
import com.udacity.mawardy.base.BaseFragment
import com.udacity.mawardy.databinding.FragmentTopicDetailsBinding


class TopicDetailsFragment : BaseFragment<FragmentTopicDetailsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_topic_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topic = TopicDetailsFragmentArgs.fromBundle(requireArguments()).topic
        viewBinding.lifecycleOwner = this
        viewBinding.topic = topic

    }

}