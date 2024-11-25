package org.dc.brildmitry.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {
    private val _catImages = MutableStateFlow<List<CatImageResponse>>(emptyList())
    val catImages: StateFlow<List<CatImageResponse>> = _catImages

    private val _isLoading = MutableStateFlow(false)  // Состояние загрузки
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchCatImages()
    }

    private fun fetchCatImages() {
        viewModelScope.launch {
            _isLoading.value = true  // Начинаем загрузку
            try {
                val images = RetrofitClient.instance.getCatImages(limit = 10)
                _catImages.value = images
            } catch (e: Exception) {
                _catImages.value = emptyList()
            } finally {
                _isLoading.value = false  // Завершаем загрузку
            }
        }
    }
}
