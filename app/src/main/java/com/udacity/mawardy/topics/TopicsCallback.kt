package com.udacity.mawardy.topics

import com.udacity.mawardy.models.Topic

interface TopicsCallback {

    fun onTopicClicked(topic: Topic?)
}