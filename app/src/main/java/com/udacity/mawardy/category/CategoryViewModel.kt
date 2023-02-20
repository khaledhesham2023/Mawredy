package com.udacity.mawardy.category

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.udacity.mawardy.datasource.firebase.DataSource
import com.udacity.mawardy.loading.LoadingDialog
import com.udacity.mawardy.models.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(): ViewModel() {

    private var _list = MutableLiveData<java.util.ArrayList<Category>>()

    val list: LiveData<ArrayList<Category>>
        get() = _list

    fun getCategories(
        reference: DatabaseReference,
        list: ArrayList<Category>,
        adapter: CategoryAdapter
    ) {
        _list.value = DataSource.getCategoriesFromFirebase(reference, list,adapter)
        Log.i("TAGGG",_list.value.toString())
    }
}