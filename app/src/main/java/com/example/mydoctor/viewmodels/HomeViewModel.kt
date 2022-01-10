package com.example.mydoctor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoctor.data.Disease
import com.example.mydoctor.repository.RoomRepository
import com.example.mydoctor.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val roomRepository: RoomRepository) : ViewModel() {

    private var _diseases: MutableSharedFlow<Resource<List<Disease>>> = MutableSharedFlow(0)
    val disease = _diseases.asSharedFlow()

    fun getDiseases() {
        viewModelScope.launch {
            try {
                _diseases.emit(Resource.Loading())
                _diseases.emit(Resource.Success(roomRepository.getAllDisease()))

            } catch (e: Exception) {
                _diseases.emit(Resource.Failure(e))
            }
        }
    }

    fun insertDisease(disease: Disease) {
        viewModelScope.launch {
            roomRepository.insertDisease(disease)
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}