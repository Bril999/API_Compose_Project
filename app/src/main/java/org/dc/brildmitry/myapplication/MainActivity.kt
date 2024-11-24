package org.dc.brildmitry.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class CatResponse(val url: String)

interface CatApi {
    @GET("cat")
    suspend fun getCatImage(): CatResponse
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatImageScreen()
        }
    }
}

@Composable
fun CatImageScreen() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://cataas.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val catApi = retrofit.create(CatApi::class.java)

    var catImageUrl by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    fun loadCatImage() {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading = true
            try {
                val response = catApi.getCatImage()
                catImageUrl = response.url
            } catch (e: Exception) {
                catImageUrl = ""
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadCatImage()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            Text("Загрузка кошки...")
        } else {
            if (catImageUrl.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(model = catImageUrl),
                    contentDescription = "Кошка",
                    modifier = Modifier.size(200.dp)
                )
            } else {
                Text("Не удалось загрузить кошку.")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { loadCatImage() }) {
            Text("Показать другую кошку")
        }
    }
}

