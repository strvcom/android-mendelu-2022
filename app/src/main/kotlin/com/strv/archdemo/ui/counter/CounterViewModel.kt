package com.strv.archdemo.ui.counter

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strv.archdemo.extension.toBundle
import com.strv.archdemo.ktools.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class CounterConfig(val text: String) : Parcelable

@HiltViewModel
class CounterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val preferences: Preferences,
) : ViewModel() {

    // DI field injection example
    //@Inject
    //lateinit var preferences: Preferences

    private val args = CounterFragmentArgs.fromBundle(savedStateHandle.toBundle())

    // We should always hold all data (state) inside ViewModel
    val title = args.config.text
    val clickCount = MutableStateFlow(preferences.getCounterValueFromPreferences())
    val callStatusText = MutableStateFlow("Ready")

    init {
        coroutinesExample()
    }

    internal fun onMinusClick() {
        val currentClickValue = clickCount.value
        val newClickValue = currentClickValue - 1
        clickCount.value = newClickValue
        preferences.updateCounterValueInPreferences(newClickValue)
    }

    internal fun onPlusClick() {
        val currentClickValue = clickCount.value
        val newClickValue = currentClickValue + 1
        clickCount.value = newClickValue
        preferences.updateCounterValueInPreferences(newClickValue)
    }

    internal fun onBlockingCallClick() {
        runBlocking { callStatusText.emit("Running") }
        Thread.sleep(3000)
        runBlocking { callStatusText.emit("Ready") }
    }

    internal fun onAsyncCallClick() {
        viewModelScope.launch(Dispatchers.IO) {
            callStatusText.emit("Running")
            Thread.sleep(3000)
            callStatusText.emit("Ready")
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