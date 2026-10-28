package be.stevenop.mdretrofitdemo2627.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Categorie (
    @SerialName("ct_id")
    val id: Long,

    @SerialName("categorie")
    val naam: String,

    @SerialName("icon")
    val iconUrl: String,
)