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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    init {
        fetchCatImages()
    }

    // Функция для загрузки изображений
    fun fetchCatImages(limit: Int = 50) {
        viewModelScope.launch {
            _isLoading.value = true
            _hasError.value = false  // сбрасываем ошибку
            try {
                val images = RetrofitClient.instance.getCatImages(limit = limit)
                _catImages.value = images
            } catch (e: Exception) {
                _catImages.value = emptyList()
                _hasError.value = true // если произошла ошибка, показываем заглушку
            } finally {
                _isLoading.value = false
            }
        }
    }
}
