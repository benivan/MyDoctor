package com.example.mydoctor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoctor.data.Lab
import com.example.mydoctor.data.Medication
import com.example.mydoctor.repository.RoomRepository
import com.example.mydoctor.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiseaseDetailsViewModel @Inject constructor(private val roomRepository: RoomRepository) :
    ViewModel() {

    private var _medications: MutableSharedFlow<Resource<List<Medication>>> = MutableSharedFlow(0)
    val medication = _medications.asSharedFlow()

    private var _labs: MutableSharedFlow<Resource<List<Lab>>> = MutableSharedFlow(0)
    val labs = _labs.asSharedFlow()

    fun getMedicationFromId(diseaseId: Long) {
        viewModelScope.launch {
            try {
                _medications.emit(Resource.Loading())
                _medications.emit(
                    Resource.Success(
                        roomRepository.getMedicationFromDiseaseId(
                            diseaseId
                        )
                    )
                )
            } catch (e: Exception) {
                _medications.emit(Resource.Failure(e))
            }
        }
    }

    fun getLabsFromId(diseaseId: Long) {
        viewModelScope.launch {
            try {
                _labs.emit(Resource.Loading())
                _labs.emit(Resource.Success(roomRepository.getLabsFromDiseaseId(diseaseId)))
            } catch (e: Exception) {
                _labs.emit(Resource.Failure(e))
            }
        }
    }
}