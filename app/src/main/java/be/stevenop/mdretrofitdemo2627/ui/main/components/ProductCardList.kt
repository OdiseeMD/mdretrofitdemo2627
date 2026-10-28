package be.stevenop.mdretrofitdemo2627.ui.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.stevenop.mdretrofitdemo2627.data.Product
import be.stevenop.mdretrofitdemo2627.ui.theme.MDRetrofitDemo2627Theme


@Composable
fun ProductCardList(
    producten: List<Product> ,
    paddingValues: PaddingValues,
    onGetCategorieIconUrl: (Long) -> String,
    onDelete: (Product) -> Unit = {},
    modifier: Modifier = Modifier
){
    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxHeight(0.5f)

    ) {
        items(producten) {
            ProductCard(
                product = it,
                iconUrl =onGetCategorieIconUrl(it.categorieId),
                onDelete = { onDelete(it) },
                modifier = Modifier.padding(8.dp)
            )
        }

    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProductCardListPreview() {
    MDRetrofitDemo2627Theme {
        ProductCardList(
            paddingValues = PaddingValues(),
            producten = listOf(
                Product(id=1, categorieId = 1, omschrijving = "Appel", prijs = 2.5f),
                Product(id=190, categorieId = 2, omschrijving = "Kwamkwammer", prijs = 3.5f),
            ),
            onGetCategorieIconUrl = { "https://stevenop.be/market/img/icon-1.png" }
        )
    }
}

