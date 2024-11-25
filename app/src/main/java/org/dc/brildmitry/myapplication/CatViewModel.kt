package org.dc.brildmitry.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {
    private val _catImages = MutableStateFlow<List<CatImageResponse>>(emptyList())
    val catImages: StateFlow<List<CatImageResponse>> = _catImages

    init {
        fetchCatImages()
    }

    private fun fetchCatImages() {
        viewModelScope.launch {
            try {
                val images = RetrofitClient.instance.getCatImages(limit = 10)
                _catImages.value = images
            } catch (e: Exception) {
                _catImages.value = emptyList()
            }
        }
    }
}