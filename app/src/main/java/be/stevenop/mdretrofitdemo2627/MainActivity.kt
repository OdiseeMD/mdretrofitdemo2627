package be.stevenop.mdretrofitdemo2627


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.stevenop.mdretrofitdemo2627.ui.main.components.EmptyProductCard
import be.stevenop.mdretrofitdemo2627.ui.main.components.ProductCardList
import be.stevenop.mdretrofitdemo2627.ui.theme.MDRetrofitDemo2627Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MDRetrofitDemo2627Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TestPostApp(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}

@Composable
fun ButtonsRow(
    onLoginClicked : ()-> Unit,
    onGetProductenClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Button(
            onClick = onLoginClicked,
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp, pressedElevation = 2.dp),
            modifier = Modifier.weight(1f)
        ){
            Text(stringResource(R.string.test_login))
        }

        Spacer( modifier = Modifier.weight(0.2f) )

        Button(
            onClick = onGetProductenClicked,
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp, pressedElevation = 2.dp),
            modifier = Modifier.weight(1f)
        ){
            Text(stringResource(R.string.get_producten))
        }
    }
}

@Composable
fun OutputArea(response: String, modifier: Modifier = Modifier) {

    Text(
        text = response,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    )
}

@Composable
fun TestPostApp(
    viewModel: MainViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val responseState by viewModel.responseState.collectAsState()
    val productenUIState by viewModel.productenUIState.collectAsState()


    Column(modifier = modifier){
        OutputArea(
            response = responseState.output,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        )

        if(productenUIState.data.isEmpty())
            EmptyProductCard(paddingValues = PaddingValues(all = 8.dp), modifier = Modifier.weight(5f))

        ProductCardList(
            producten = productenUIState.data,
            paddingValues = PaddingValues(all = 8.dp),
            onGetCategorieIconUrl = { viewModel.getCategorieIconUrl(it) },
            onDelete = { viewModel.deleteProduct(it) },
            modifier = Modifier.weight(5f)
        )
        ButtonsRow(
            onLoginClicked = {  },
            onGetProductenClicked = { viewModel.getProducten() } ,
            Modifier.weight(1f)
        )
    }
}




@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AppPreview() {
    MDRetrofitDemo2627Theme {
        TestPostApp()
    }
}