package com.strv.archdemo.extension

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle

@Suppress("SpreadOperator")
fun SavedStateHandle.toBundle() = bundleOf(
    *keys().map { it to get<Any>(it) }.toTypedArray()
)
