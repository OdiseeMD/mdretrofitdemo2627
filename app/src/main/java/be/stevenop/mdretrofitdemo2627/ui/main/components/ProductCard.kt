package be.stevenop.mdretrofitdemo2627.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.stevenop.mdretrofitdemo2627.R
import be.stevenop.mdretrofitdemo2627.data.Product
import be.stevenop.mdretrofitdemo2627.ui.theme.MDRetrofitDemo2627Theme
import coil3.compose.AsyncImage


@Composable
fun ProductCard(
    product: Product,
    iconUrl: String,
    onDelete: (Product) -> Unit = {},
    modifier: Modifier = Modifier
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primaryContainer)
    ){
        // https://github.com/coil-kt/coil#quick-start
        AsyncImage(
            model = iconUrl,
            contentDescription = product.omschrijving,
            modifier = Modifier
                .height(24.dp)
                .width(48.dp)
                .padding(start = 8.dp, end = 8.dp)
        )
        Text(text = product.omschrijving, modifier = Modifier
            .weight(4f)
            .height(24.dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(start = 8.dp, end = 8.dp)
        )
        Text(text = product.prijs.toString(), modifier = Modifier
            .weight(1f)
            .height(24.dp)
            .padding(start = 8.dp, end = 8.dp)
        )
        if(product.id > 145) {
            // deze API staat enkel toe om met id > 145 te verwijderen
            Image(
                painter = painterResource(R.drawable.delete),
                contentDescription = stringResource(R.string.delete_product),
                modifier = Modifier
                    .height(24.dp)
                    .width(32.dp)
                    .clickable(
                        enabled = true,
                        onClick = { onDelete(product) }
                    )
            )
        } else {
            Spacer(
                modifier = Modifier
                    .width(32.dp)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            )
        }

    }
}




@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProductCardPreview() {
    MDRetrofitDemo2627Theme {
        ProductCard(
            Product(id=146, categorieId = 1, omschrijving = "Appel", prijs = 2.5f),
            iconUrl="https://stevenop.be/market/img/icon-1.png"
        )
    }
}