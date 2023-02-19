package com.udacity.mawardy.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.udacity.mawardy.R
import com.udacity.mawardy.base.BaseFragment
import com.udacity.mawardy.databinding.FragmentCategoryBinding
import com.udacity.mawardy.models.Category
import com.udacity.mawardy.models.CategoryItem


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(), CategoryCallback {
    override val layout: Int
        get() = R.layout.fragment_category

    private lateinit var adapter: CategoryAdapter
    private lateinit var reference: DatabaseReference
    private lateinit var list: ArrayList<Category>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog.show()
        list = ArrayList()
        adapter = CategoryAdapter(ArrayList(), this)
        viewBinding.categoriesList.layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.categoriesList.adapter = adapter
        viewBinding.lifecycleOwner = this
        reference = FirebaseDatabase.getInstance().getReference("categories")
        reference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val title = snapshot.child("title").getValue(String::class.java)
                val image = snapshot.child("image").getValue(String::class.java)
                val background = snapshot.child("background").getValue(String::class.java)
                val categoryId = snapshot.child("categoryId").getValue(Long::class.java)

                Log.i("TAGG", "image is $image")
                val category = Category(background, categoryId, image, null, title)
                list.add(category)
                adapter.updateDataSet(list)
                loadingDialog.dismiss()
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
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
                loadingDialog.dismiss()
            }
        })


    }

    override fun onCategoryClicked(category: Category) {
        findNavController().navigate(
            CategoryFragmentDirections.actionCategoryFragmentToTopicsFragment(
                category.title!!
            )
        )
    }


}