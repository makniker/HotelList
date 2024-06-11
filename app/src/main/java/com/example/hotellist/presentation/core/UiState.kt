package com.example.hotellist.presentation.core

sealed class UiState<T> {
    class Loading<T> : UiState<T>()
    class Success<T>(val data: T) : UiState<T>()
    class Failure<T>(val e: Exception) : UiState<T>()
}

enum class FlipperStates {
    FAILURE_VIEW,
    SUCCESS_VIEW,
    LOADING_VIEW
}