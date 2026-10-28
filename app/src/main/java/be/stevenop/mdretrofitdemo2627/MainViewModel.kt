package be.stevenop.mdretrofitdemo2627


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.stevenop.mdretrofitdemo2627.data.CategorieUIState
import be.stevenop.mdretrofitdemo2627.data.Product
import be.stevenop.mdretrofitdemo2627.data.ProductenUIState
import be.stevenop.mdretrofitdemo2627.data.ResponseState
import be.stevenop.mdretrofitdemo2627.network.MyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // State, simple as Strings in ResponseState
    private val _responseState = MutableStateFlow(ResponseState(
        loginResponse = "",
        productenResponse = "",
        categorieenResponse = "",
        output = "Klik op 1 van de knoppen om te testen")
    )
    val responseState: StateFlow<ResponseState> = _responseState.asStateFlow()

    private val _productenUIState = MutableStateFlow(ProductenUIState())
    val productenUIState = _productenUIState.asStateFlow()

    private val _categorieenUIState = MutableStateFlow(CategorieUIState())
    val categorieenUIState = _categorieenUIState.asStateFlow()


    init {
        getCategorieen()
    }
    /**
     * Haal de producten uit de lijst van de API
     * Opgelet : aangezien deze API verspreid is, is het mogelijk dat andere studenten de
     * producten verwijderd hebben. In dat geval voeg je best nieuwe producten toe als je met
     * hetzelfde endpoint werkt.
     */
    fun getProducten() {
        viewModelScope.launch {
            _productenUIState.update {
                it.copy(
                    data = listOf() // niet nodig, maar doen we om iets visueel te testen bij het laden
                )
            }
            try {
                val productenResponse = MyApi.retroFitService.getProducten()

                if(productenResponse.status in 200..299 && !productenResponse.data.isEmpty()) {
                    // zet de nieuwe Producten in _productenUIState
                    _productenUIState.update {
                        it.copy(
                            data = productenResponse.data
                        )
                    }

                    // de output aanpassen (voor het bovenste deel)
                    _responseState.update { currentState ->
                        currentState.copy(
                            productenResponse = productenResponse.data.size.toString(),
                            output = productenResponse.toString()
                        )
                    }
                }
            } catch(e: Exception) {
                Log.e("getProducten", e.toString())
            }


        }
    }

    fun getCategorieen() {
        viewModelScope.launch {
            _categorieenUIState.update {
                it.copy(
                    data = listOf() // niet nodig, maar doen we om iets visueel te testen bij het laden
                )
            }
            try {
                val categorieenResponse = MyApi.retroFitService.getCategorieen()

                if(categorieenResponse.status in 200..299 && !categorieenResponse.data.isEmpty()) {
                    // zet de nieuwe Producten in _productenUIState
                    _categorieenUIState.update {
                        it.copy(
                            data = categorieenResponse.data
                        )
                    }

                    // de output aanpassen (voor het bovenste deel)
                    _responseState.update { currentState ->
                        currentState.copy(
                            categorieenResponse = categorieenResponse.data.size.toString(),
                            output = categorieenResponse.toString()
                        )
                    }
                }
            } catch(e: Exception) {
                Log.e("getCategorieen", e.toString())
            }


        }
    }

    fun getCategorieIconUrl(categorieId: Long) : String {
        val categorie = _categorieenUIState.value.data.find { it.id == categorieId }
        return categorie?.iconUrl ?: ""
    }
    fun deleteProduct(product: Product) {
        viewModelScope.launch {

            val deleteResult = MyApi.retroFitService.deleteProduct(product.id)

            _responseState.update { currentState ->
                currentState.copy(
                    deleteResponse = deleteResult.toString(),
                    output = deleteResult.toString()
                )
            }

            _productenUIState.update {
                it.copy(
                    data = it.data.filter { it.id != product.id }
                )
            }
        }
    }

}