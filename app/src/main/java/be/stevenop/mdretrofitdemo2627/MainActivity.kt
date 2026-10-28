package be.stevenop.mdretrofitdemo2627


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import be.stevenop.mdretrofitdemo2627.ui.main.TestPostApp
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
