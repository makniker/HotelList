package com.example.hotellist.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotellist.domain.GetHotelDetailsUseCase
import com.example.hotellist.presentation.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val useCase: GetHotelDetailsUseCase) :
    ViewModel() {
    private val _hotelLiveData = MutableLiveData<UiState<HotelDetailsUiModel>>()
    val hotelLiveData: LiveData<UiState<HotelDetailsUiModel>> = _hotelLiveData

    fun fetchDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _hotelLiveData.postValue(UiState.Loading())
            try {
                _hotelLiveData.postValue(UiState.Success(useCase(id)))
            } catch (e: Exception) {
                _hotelLiveData.postValue(UiState.Failure(e))
            }
        }
    }
}