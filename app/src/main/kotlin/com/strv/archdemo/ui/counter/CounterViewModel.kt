package com.strv.archdemo.ui.counter

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.strv.archdemo.extension.toBundle
import com.strv.archdemo.ktools.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class CounterConfig(val text: String) : Parcelable

@HiltViewModel
class CounterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val preferences: Preferences,
) : ViewModel() {
    private val args = CounterFragmentArgs.fromBundle(savedStateHandle.toBundle())

    // We should always hold all data (state) inside ViewModel
    val title = args.config.text
    val clickCount = MutableStateFlow(preferences.getCounterValueFromPreferences())

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
}