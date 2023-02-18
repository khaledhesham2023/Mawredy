package com.udacity.mawardy.category

import com.udacity.mawardy.models.Category

interface CategoryCallback {

    fun onCategoryClicked(category: Category)
}