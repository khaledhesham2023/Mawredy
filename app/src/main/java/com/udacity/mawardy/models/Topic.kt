package com.udacity.mawardy.models

import java.io.Serializable

class Topic(
    var description: String?,
    var details: String?,
    var image: String?,
    var mapImage: String?,
    var name: String?,
    var smallImage: String?,
    var titleImage: String?
): Serializable {
}