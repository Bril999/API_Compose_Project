package org.dc.brildmitry.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatApp() {
    val viewModel: CatViewModel = viewModel()
    val catImages = viewModel.catImages.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val hasError = viewModel.hasError.collectAsState()
    val backgroundColor = colorResource(id = R.color.Columbia)
    val UpColor = colorResource(id = R.color.Melon)

    Scaffold(
        modifier = Modifier.background(UpColor),
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "CatTerest",
                            fontFamily = FontFamily.Cursive,
                            fontSize = 40.sp,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = UpColor
                )
            )
        }
    ) { paddingValues ->
        when {
            isLoading.value -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Loading...", color = Color.White)
                    }
                }
            }

            hasError.value -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "No internet connection", fontSize = 20.sp, color = Color.Red)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.fetchCatImages() }) {
                            Text("Retry")
                        }
                    }
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 130.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(backgroundColor)
                ) {
                    items(catImages.value) { catImage ->
                        CatImageItem(url = catImage.url)
                    }
                }
            }
        }
    }
}

@Composable
fun CatImageItem(url: String) {
    val painter = rememberImagePainter(url)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .aspectRatio(0.5f)
            .aspectRatio(0.5f)
            .aspectRatio(0.5f)
            .aspectRatio(0.5f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

