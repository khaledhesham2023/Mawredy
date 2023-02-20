package com.udacity.mawardy.topics

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.udacity.mawardy.R
import com.udacity.mawardy.base.BaseFragment
import com.udacity.mawardy.databinding.FragmentTopicsBinding
import com.udacity.mawardy.models.Topic


class TopicsFragment : BaseFragment<FragmentTopicsBinding>(),TopicsCallback {
    override val layout: Int
        get() = R.layout.fragment_topics

    private lateinit var title: String
    private lateinit var topicReference: DatabaseReference
    private lateinit var topicList: ArrayList<Topic>
    private lateinit var topicAdapter: TopicAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = TopicsFragmentArgs.fromBundle(requireArguments()).title
        topicList = ArrayList()
        topicAdapter = TopicAdapter(topicList,this)
        viewBinding.topicsList.adapter = topicAdapter
        viewBinding.topicsList.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.lifecycleOwner = this
        viewBinding.topicTitle.text = title
        topicReference =
            FirebaseDatabase.getInstance().getReference("/categories/${title.lowercase()}/list")
        topicReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val smallImage = snapshot.child("smallImage").getValue(String::class.java)
                val title = snapshot.child("name").getValue(String::class.java)
                val description = snapshot.child("description").getValue(String::class.java)
                val details = snapshot.child("details").getValue(String::class.java)
                val image = snapshot.child("image").getValue(String::class.java)
                val mapImage = snapshot.child("mapImage").getValue(String::class.java)
                val titleImage = snapshot.child("titleImage").getValue(String::class.java)

                Log.i("LOGG", description.toString())
                val topic = Topic(description, details, image, mapImage, title, smallImage, titleImage)
                topicList.add(topic)
                topicAdapter.updateDataSet(topicList)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
//                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
            }
        })


    }

    override fun onTopicClicked(topic: Topic?) {
        findNavController().navigate(TopicsFragmentDirections.actionTopicsFragmentToTopicDetailsFragment(topic!!))
    }
}