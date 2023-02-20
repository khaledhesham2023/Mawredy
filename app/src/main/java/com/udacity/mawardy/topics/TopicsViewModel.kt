package com.udacity.mawardy.topics

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.udacity.mawardy.datasource.firebase.DataSource
import com.udacity.mawardy.loading.LoadingDialog
import com.udacity.mawardy.models.Category
import com.udacity.mawardy.models.Topic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(): ViewModel() {

    private var _list = MutableLiveData<ArrayList<Topic>>()

    val list: LiveData<ArrayList<Topic>>
        get() = _list

    fun getCategoryTopics(
        reference: DatabaseReference,
        list: ArrayList<Topic>,
        adapter: TopicAdapter
    ) {
        _list.value = DataSource.getCategoryTopics(reference, list,adapter)
    }
}