package be.stevenop.mdretrofitdemo2627.data

import kotlinx.serialization.Serializable

@Serializable
data class ProductAddResponse (
    val status: Long,
    val message: String?,
    val missingFields: List<String>?
)