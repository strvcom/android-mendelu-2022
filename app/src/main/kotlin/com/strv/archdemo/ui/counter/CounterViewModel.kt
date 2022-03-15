package com.strv.archdemo.ui.counter

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.strv.archdemo.extension.toBundle
import kotlinx.parcelize.Parcelize

@Parcelize
data class CounterConfig(val text: String) : Parcelable

class CounterViewModel(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args = CounterFragmentArgs.fromBundle(savedStateHandle.toBundle())

    // We should always hold all data (state) inside ViewModel
    val title = args.config.text
}