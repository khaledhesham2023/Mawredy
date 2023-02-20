package com.udacity.mawardy.datasource.firebase

import android.util.Log
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.udacity.mawardy.category.CategoryAdapter
import com.udacity.mawardy.loading.LoadingDialog
import com.udacity.mawardy.models.Category
import com.udacity.mawardy.models.Topic
import com.udacity.mawardy.topics.TopicAdapter

class DataSource {

    companion object {


        fun getCategoriesFromFirebase(
            ref: DatabaseReference,
            list: ArrayList<Category>,
            adapter: CategoryAdapter
        ): ArrayList<Category> {

            ref.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val title = snapshot.child("title").getValue(String::class.java)
                    val image = snapshot.child("image").getValue(String::class.java)
                    val background = snapshot.child("background").getValue(String::class.java)
                    val categoryId = snapshot.child("categoryId").getValue(Long::class.java)

                    val category = Category(background, categoryId, image, null, title)
                    list.add(category)
                    adapter.updateDataSet(list)
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
//                    Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
//                    loadingDialog.dismiss()
                }
            })

            return list

        }


        fun getCategoryTopics(
            ref: DatabaseReference,
            list: ArrayList<Topic>,
            adapter: TopicAdapter
        ): ArrayList<Topic> {

            ref.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val smallImage = snapshot.child("smallImage").getValue(String::class.java)
                    val title = snapshot.child("name").getValue(String::class.java)
                    val description = snapshot.child("description").getValue(String::class.java)
                    val details = snapshot.child("details").getValue(String::class.java)
                    val image = snapshot.child("image").getValue(String::class.java)
                    val mapImage = snapshot.child("mapImage").getValue(String::class.java)
                    val titleImage = snapshot.child("titleImage").getValue(String::class.java)

                    Log.i("LOGG", description.toString())
                    val topic =
                        Topic(description, details, image, mapImage, title, smallImage, titleImage)
                    list.add(topic)
                    adapter.updateDataSet(list)
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

            return list
        }
    }
}