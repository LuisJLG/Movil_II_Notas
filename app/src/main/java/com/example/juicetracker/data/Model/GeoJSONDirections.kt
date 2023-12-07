package com.example.juicetracker.data.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class Geometry (val coordinates : List<List<Double>>)
@Serializable
data class Feature(val geometry : Geometry)
@Serializable
data class GeoJSONDirection(
    val type: String,
    val features: List<Feature>
)




