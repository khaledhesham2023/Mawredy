package com.udacity.mawardy.category

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.udacity.mawardy.R
import com.udacity.mawardy.base.BaseFragment
import com.udacity.mawardy.base.BaseFragmentWithViewModel
import com.udacity.mawardy.databinding.FragmentCategoryBinding
import com.udacity.mawardy.datasource.firebase.DataSource
import com.udacity.mawardy.models.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragmentWithViewModel<FragmentCategoryBinding, CategoryViewModel>(),
    CategoryCallback {
    override val layout: Int
        get() = R.layout.fragment_category

    override val viewModelClass: Class<CategoryViewModel>
        get() = CategoryViewModel::class.java

    private lateinit var adapter: CategoryAdapter
    private lateinit var list: ArrayList<Category>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog.show()
        configureTheAdapter()
        viewBinding.lifecycleOwner = this
        viewModel.getCategories(FirebaseDatabase.getInstance().getReference("categories"),
            ArrayList(),adapter
        )

    }

    override fun onCategoryClicked(category: Category) {
        findNavController().navigate(
            CategoryFragmentDirections.actionCategoryFragmentToTopicsFragment(
                category.title!!
            )
        )
    }

    private fun configureTheAdapter() {
        adapter = CategoryAdapter(ArrayList(), this)
        viewBinding.categoriesList.layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.categoriesList.adapter = adapter
    }

    override fun setupObservers() {
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.updateDataSet(it)
            loadingDialog.dismiss()
        })
    }


}