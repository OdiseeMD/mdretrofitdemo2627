package be.stevenop.mdretrofitdemo2627.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.stevenop.mdretrofitdemo2627.R
import be.stevenop.mdretrofitdemo2627.ui.theme.MDRetrofitDemo2627Theme

@Composable
fun EmptyProductCard(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ){
        Text(
            text = stringResource(R.string.no_products_fetched_yet),
            textAlign = TextAlign.Center,
            modifier = modifier
                .weight(1f)
                .height(24.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(start = 8.dp, end = 8.dp)
        )
    }
}

@Preview
@Composable
fun EmptyProductCardPreview() {
    MDRetrofitDemo2627Theme {
        EmptyProductCard(paddingValues = PaddingValues(all = 8.dp))
    }
}