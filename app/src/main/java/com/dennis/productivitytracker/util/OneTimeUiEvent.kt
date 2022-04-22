package com.dennis.productivitytracker.util

sealed class OneTimeUiEvent {
    object PopBackStack: OneTimeUiEvent()
    data class Navigate(val route: String): OneTimeUiEvent()
}