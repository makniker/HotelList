package com.example.hotellist.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotellist.domain.GetListUseCase
import com.example.hotellist.presentation.core.UiState
import com.example.hotellist.presentation.core.SortState
import com.example.hotellist.presentation.core.SortingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelListViewModel @Inject constructor(private val getListUseCase: GetListUseCase): ViewModel() {
    private val _hotelsLiveData = MutableLiveData<UiState<List<HotelListUiModel>>>()
    val hotelsLiveData: LiveData<UiState<List<HotelListUiModel>>> = _hotelsLiveData
    private val _sortLiveData = MutableLiveData(SortState(SortingState.DEFAULT, true))
    val sortLiveData: LiveData<SortState> = _sortLiveData

    fun fetchHotelList() {
        viewModelScope.launch(Dispatchers.IO) {
            _hotelsLiveData.postValue(UiState.Loading())
            try {
                _hotelsLiveData.postValue(UiState.Success(getListUseCase()))
            } catch (e: Exception) {
                _hotelsLiveData.postValue(UiState.Failure(e))
            }
        }
    }

    fun setSort(sort: SortingState) {
        if (sortLiveData.isInitialized) {
            val newState = sortLiveData.value
            newState?.stateOfSorting = sort
        }
        _sortLiveData.postValue(sortLiveData.value)
    }

    fun changeOrder() {
        if (sortLiveData.isInitialized) {
            val newState = sortLiveData.value
            newState?.isAscending = !newState?.isAscending!!
        }
        _sortLiveData.postValue(sortLiveData.value)
    }

}