package com.udacity.mawardy.models

 class Category(
     val background: String?,
     val categoryId: Long?,
     val image: String?,
     val list: List<CategoryItem>?,
     val title: String?

) {

     constructor():this(null,null,null,null,null)
}