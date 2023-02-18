package com.udacity.mawardy.models

import java.io.Serializable

class Category(
     val background: String?,
     val categoryId: Long?,
     val image: String?,
     val list: List<CategoryItem>?,
     val title: String?

): Serializable {

     constructor():this(null,null,null,null,null)
}