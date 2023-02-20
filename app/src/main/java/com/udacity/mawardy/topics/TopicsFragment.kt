package com.udacity.mawardy.topics

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.udacity.mawardy.R
import com.udacity.mawardy.base.BaseFragment
import com.udacity.mawardy.base.BaseFragmentWithViewModel
import com.udacity.mawardy.databinding.FragmentTopicsBinding
import com.udacity.mawardy.models.Topic
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicsFragment : BaseFragmentWithViewModel<FragmentTopicsBinding, TopicsViewModel>(),
    TopicsCallback {
    override val layout: Int
        get() = R.layout.fragment_topics

    private lateinit var title: String
    private lateinit var topicList: ArrayList<Topic>
    private lateinit var topicAdapter: TopicAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = TopicsFragmentArgs.fromBundle(requireArguments()).title
        topicList = ArrayList()
        configureTheAdapter()
        viewBinding.lifecycleOwner = this
        viewBinding.topicTitle.text = title
        viewModel.getCategoryTopics(
            FirebaseDatabase.getInstance().getReference("categories/${title.lowercase()}/list"),
            topicList,
            topicAdapter
        )

    }



    private fun configureTheAdapter() {
        topicAdapter = TopicAdapter(topicList, this)
        viewBinding.topicsList.adapter = topicAdapter
        viewBinding.topicsList.layoutManager = LinearLayoutManager(requireContext())
    }

    override val viewModelClass: Class<TopicsViewModel>
        get() = TopicsViewModel::class.java

    override fun setupObservers() {
        viewModel.list.observe(viewLifecycleOwner, Observer {
            topicAdapter.updateDataSet(it)
            loadingDialog.dismiss()
        })
    }

    override fun onTopicClicked(topic: Topic?) {
        findNavController().navigate(
            TopicsFragmentDirections.actionTopicsFragmentToTopicDetailsFragment(
                topic!!
            )
        )
    }
}