package be.stevenop.mdretrofitdemo2627.data
import kotlinx.serialization.Serializable

@Serializable
data class ProductenResponse (
    val status: Long,
    val data: List<Product>
)

data class ProductenUIState(
    val data: List<Product> = listOf()
)
