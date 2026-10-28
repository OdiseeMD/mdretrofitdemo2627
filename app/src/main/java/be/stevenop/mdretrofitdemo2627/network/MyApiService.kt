package be.stevenop.mdretrofitdemo2627.network

import be.stevenop.mdretrofitdemo2627.data.CategorieenResponse
import be.stevenop.mdretrofitdemo2627.data.DeleteResponse
import be.stevenop.mdretrofitdemo2627.data.ProductenResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Vervang dit endpoint door het endpoint van jouw api.
 * In dit geval moet deze URI eindigen met een /
 */
private const val baseUrl = "https://stevenop.be/market/v2/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(baseUrl)
    .build()

interface MyApiService {

    /**
     * Voor een get request is er niet veel speciaal aan te passen, hier volgen we
     * de cursus.
     * Let wel op : deze api is publiek, studenten kunnen producten verwijderen dus
     * het is mogelijk dat als je dit test, dat je een lege lijst krijgt. Is dat het
     * geval, voeg dan een paar producten toe via de test-app die je eerder al gebruikte.
     */
    @GET("producten")
    suspend fun getProducten() : ProductenResponse

    /**
     * Let bij de parameters vooral op wat je wil meegeven :
     * - in dit geval verwacht de api in de body van de Request de eigenschappen die in het
     *   Product object zitten, maar dan de serialized vorm (@SerialName)
     */
    @DELETE("producten/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Long
    ) : DeleteResponse

    @GET("categorieen")
    suspend fun getCategorieen() : CategorieenResponse

}

object MyApi {
    val retroFitService : MyApiService by lazy {
        retrofit.create(MyApiService::class.java)
    }
}

