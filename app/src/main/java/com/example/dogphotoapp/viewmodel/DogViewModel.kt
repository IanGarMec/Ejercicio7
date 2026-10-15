package com.example.dogphotoapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogphotoapp.model.DogPhoto
import com.example.dogphotoapp.network.DogApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DogUiState {
    data class Success(val photos: List<DogPhoto>) : DogUiState
    object Error : DogUiState
    object Loading : DogUiState
}

class DogViewModel : ViewModel() {
    var dogUiState: DogUiState by mutableStateOf(DogUiState.Loading)
        private set

    init {
        getDogPhotos()
    }

    private fun getDogPhotos() {
        viewModelScope.launch {
            dogUiState = try {
                val response = DogApi.retrofitService.getPhotos()

                /*
                Nota sobre esto:
                A diferencia de la API de gatos vista en clase (que regresa una lista directa),
                la API de Dog CEO que escogí, según mi busqueda, regresa un objeto contenedor que
                almacena las URL de las imágenes en una propiedad llamada "message".
                Investigando la documentación y estructura JSON de Dog CEO, mejor usé este mapeo
                (mapIndexed) para adaptar las URL.*/


                val listResult = response.message.mapIndexed { index, url ->
                    DogPhoto(id = index.toString(), url = url)
                }
                DogUiState.Success(listResult)
            } catch (e: Exception) {
                DogUiState.Error
            }
        }
    }
}