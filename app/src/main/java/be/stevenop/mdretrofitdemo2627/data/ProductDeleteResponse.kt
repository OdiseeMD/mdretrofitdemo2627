package be.stevenop.mdretrofitdemo2627.data

import kotlinx.serialization.Serializable

@Serializable
data class ProductDeleteResponse (
    val status: Long,
    val message: String
)
