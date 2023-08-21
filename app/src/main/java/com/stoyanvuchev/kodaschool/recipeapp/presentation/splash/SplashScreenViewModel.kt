package com.stoyanvuchev.kodaschool.recipeapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.kodaschool.recipeapp.data.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _isSetupCompleted = MutableStateFlow<Boolean?>(null)
    val isSetupCompleted = _isSetupCompleted.asStateFlow()

    private val _uiActionChannel = Channel<SplashScreenUiAction>()
    val uiActionFlow = _uiActionChannel.receiveAsFlow()

    init {
        getIsSetupCompleted()
    }

    fun onUiAction(uiAction: SplashScreenUiAction) {
        when (uiAction) {
            is SplashScreenUiAction.CompleteSetup -> completeSetup()
        }
    }

    private fun getIsSetupCompleted() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val isCompleted = preferences.getIsSetupCompleted() ?: false
                withContext(Dispatchers.Default) {
                    _isSetupCompleted.update { isCompleted }
                    println("AJA: isSetup: $isCompleted")
                    if (isCompleted) {
                        sendUiAction(SplashScreenUiAction.CompleteSetup)
                    }
                }
            }
        }
    }

    private fun completeSetup() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                preferences.setIsSetupCompleted(isSetupCompleted = true)
                withContext(Dispatchers.Default) {
                    sendUiAction(SplashScreenUiAction.CompleteSetup)
                }
            }
        }
    }

    private fun sendUiAction(uiAction: SplashScreenUiAction) {
        viewModelScope.launch { _uiActionChannel.send(uiAction) }
    }

}