package com.udacity.mawardy.topics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.udacity.mawardy.R
import com.udacity.mawardy.databinding.ItemTopicBinding
import com.udacity.mawardy.models.Topic

class TopicAdapter(private var data: List<Topic>, private val callback: TopicsCallback) :
    Adapter<TopicAdapter.TopicViewHolder>() {

    inner class TopicViewHolder(val binding: ItemTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                callback.onTopicClicked(data[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_topic,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.binding.topic = data[position]
    }

    override fun getItemCount(): Int = data.size

    fun updateDataSet(data: List<Topic>) {
        this.data = data
        notifyDataSetChanged()
    }

}