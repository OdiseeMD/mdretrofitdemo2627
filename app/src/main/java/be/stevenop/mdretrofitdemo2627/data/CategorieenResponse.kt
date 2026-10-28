package be.stevenop.mdretrofitdemo2627.data
import kotlinx.serialization.Serializable

@Serializable
data class CategorieenResponse (
    val status: Long,
    val data: List<Categorie>
)

data class CategorieUIState(
    val data: List<Categorie> = listOf()
)
