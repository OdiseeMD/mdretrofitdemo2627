package be.stevenop.mdretrofitdemo2627.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product (
    @SerialName("id")
    val id: Long,

    @SerialName("ct_id")
    val categorieId: Long,

    @SerialName("omschrijving")
    val omschrijving: String,

    @SerialName("prijs")
    val prijs: Float
)