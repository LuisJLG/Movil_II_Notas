/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.juicetracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juicetracker.data.Model.*
import com.example.juicetracker.data.Repositorio.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * View Model which maintain states for [JuiceTrackerApp]
 */
class JuiceTrackerViewModel(
    private val juiceRepository: JuiceRepository,
    private val notaRepository: NotaRepository,
    private val tareaRepository: TareaRepository,
    private val multimediaRepository: MultimediaRepository
) : ViewModel() {
    private val emptyJuice = Juice(
        0,
        "",
        "",
        JuiceColor.Red.name,
        imageUri = null,
        videoUri = null,
        audioUri = null,
        4
    )

    private val emptyNota = Nota(
        0,
        "",
        "",
        ""
    )

    private val emptyTarea = Tarea(
        0,
        "",
        "",
        ""
    )

    private val emptyMultimedia = Multimedia(
        0,
        "",
        null,
        null,
        null,
        null,
        null
    )


    private val _currentJuiceStream = MutableStateFlow(emptyJuice)
    val currentJuiceStream: StateFlow<Juice> = _currentJuiceStream
    val juiceListStream: Flow<List<Juice>> = juiceRepository.juiceStream

    fun resetCurrentJuice() = _currentJuiceStream.update { emptyJuice }
    fun updateCurrentJuice(juice: Juice) = _currentJuiceStream.update { juice }

    fun saveJuice() = viewModelScope.launch {
        if (_currentJuiceStream.value.id > 0) {
            juiceRepository.updateJuice(_currentJuiceStream.value)
        } else {
            juiceRepository. addJuice(_currentJuiceStream.value)
        }
    }

    fun deleteJuice(juice: Juice) = viewModelScope.launch {
        juiceRepository.deleteJuice(juice)
    }

    //////////////////////////////|Nota|\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private val _currentNotaStream = MutableStateFlow(emptyNota)
    val currentNotaStream: StateFlow<Nota> = _currentNotaStream
    val notaListStream: Flow<List<Nota>> = notaRepository.notaStream

    fun resetCurrentNota() = _currentNotaStream.update { emptyNota }
    fun updateCurrentNota(nota: Nota) = _currentNotaStream.update { nota }

    fun saveNota() = viewModelScope.launch {
        if (_currentNotaStream.value.id > 0) {
            notaRepository.updateNota(_currentNotaStream.value)
        } else {
            notaRepository. addNota(_currentNotaStream.value)
        }
    }

    fun deleteNota(nota: Nota) = viewModelScope.launch {
        notaRepository.deleteNota(nota)
    }


    //////////////////////////////|Tarea|\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private val _currentTareaStream = MutableStateFlow(emptyTarea)
    val currentTareaStream: StateFlow<Tarea> = _currentTareaStream
    val tareaListStream: Flow<List<Tarea>> = tareaRepository.tareaStream

    fun resetCurrentTarea() = _currentTareaStream.update { emptyTarea }
    fun updateCurrentTarea(tarea: Tarea) = _currentTareaStream.update { tarea }

    fun saveTarea() = viewModelScope.launch {
        if (_currentTareaStream.value.id > 0) {
            tareaRepository.updateTarea(_currentTareaStream.value)
        } else {
            tareaRepository. addTarea(_currentTareaStream.value)
        }
    }

    fun deleteTarea(tarea: Tarea) = viewModelScope.launch {
        tareaRepository.deleteTarea(tarea)
    }


    //////////////////////////////|Multimedia|\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private val _currentMultimediaStream = MutableStateFlow(emptyMultimedia)
    val currentMultimediaStream: StateFlow<Multimedia> = _currentMultimediaStream
    val multimediaListStream: Flow<List<Multimedia>> = multimediaRepository.multimediaStream

    fun resetCurrentMultimedia() = _currentMultimediaStream.update { emptyMultimedia }
    fun updateCurrentMultimedia(multimedia: Multimedia) = _currentMultimediaStream.update { multimedia }

    fun saveMultimedia() = viewModelScope.launch {
            multimediaRepository.addMultimedia(_currentMultimediaStream.value)
    }

    fun deleteMultimedia(multimedia: Multimedia) = viewModelScope.launch {
        multimediaRepository.deleteMultimedia(multimedia)
    }
}
