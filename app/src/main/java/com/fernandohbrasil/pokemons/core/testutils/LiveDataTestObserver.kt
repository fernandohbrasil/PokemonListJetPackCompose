package com.fernandohbrasil.pokemons.core.testutils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock

class LiveDataTestObserver<T>(liveData: LiveData<T>) {

    private val sequence = mutableListOf<T>()

    init {
        val lifecycle = LifecycleRegistry(mock {})
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        liveData.observe({ lifecycle }) { nullableObject ->
            nullableObject?.let { sequence.add(it) }
        }
    }

    fun assertEmpty() {
        assertThat(sequence).isEmpty()
    }

    fun assertValues(vararg values: T) {
        assertSize(values.toList())
        assertSequence(values.toList())
    }

    private fun assertSize(values: List<T>) {
        assertThat(sequence.size).isEqualTo(values.size)
    }

    private fun assertSequence(values: List<T>) {
        for (i in values.indices) {
            assertThat(sequence[i]).isEqualTo(values[i])
        }
    }
}
