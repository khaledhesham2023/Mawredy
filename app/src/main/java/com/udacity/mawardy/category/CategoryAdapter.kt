package com.udacity.mawardy.category

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.mawardy.R
import com.udacity.mawardy.databinding.ItemCategoryBinding
import com.udacity.mawardy.models.Category

class CategoryAdapter(private var data: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        Log.i("TAGG","OnCreateViewHolder called")
        return CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Log.i("TAGG","OnBindViewHolder called")
        holder.binding.category = data[position]
    }

    override fun getItemCount(): Int = data.size

    fun updateDataSet(data: List<Category>){
        this.data = data
        notifyDataSetChanged()
        Log.i("TAGG","updateDataSet called")
    }
}