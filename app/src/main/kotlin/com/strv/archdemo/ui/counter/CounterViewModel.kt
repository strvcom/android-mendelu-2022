package com.strv.archdemo.ui.counter

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strv.archdemo.ktools.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val preferences: Preferences,
) : ViewModel() {

    // DI field injection example
    //@Inject
    //lateinit var preferences: Preferences

    // We should always hold all data (state) inside ViewModel
    val title = savedStateHandle.get<String>("title")

    val viewState = MutableStateFlow(
        CounterViewState(
            title = title ?: "Some message here",
            clickCount = preferences.getCounterValueFromPreferences(),
            callStatus = "Ready",
        )
    )

    init {
        coroutinesExample()
    }

    internal fun onMinusClick() {
        val currentClickValue = viewState.value.clickCount
        val newClickValue = currentClickValue - 1
        viewState.update { it.copy(clickCount = newClickValue) }
        preferences.updateCounterValueInPreferences(newClickValue)
    }

    internal fun onPlusClick() {
        val currentClickValue = viewState.value.clickCount
        val newClickValue = currentClickValue + 1
        viewState.update { it.copy(clickCount = newClickValue) }
        preferences.updateCounterValueInPreferences(newClickValue)
    }

    internal fun onBlockingCallClick() {
        runBlocking { viewState.update { it.copy(callStatus = "Running") } }
        Thread.sleep(3000)
        runBlocking { viewState.update { it.copy(callStatus = "Ready") } }
    }

    internal fun onAsyncCallClick() {
        viewModelScope.launch(Dispatchers.IO) {
            runBlocking { viewState.update { it.copy(callStatus = "Running") } }
            Thread.sleep(3000)
            runBlocking { viewState.update { it.copy(callStatus = "Ready") } }
        }
    }

    private fun coroutinesExample() {
        Log.i("Counter", "\tSerial 1")

        viewModelScope.launch {
            Log.i("Counter", "\t\tParalel 1")

            delay(2000)
            Log.i("Counter", "\t\tParalel 2")

            delay(2000)
            Log.i("Counter", "\t\tParalel 3")
        }

        Log.i("Counter", "\tSerial 2")
    }
}