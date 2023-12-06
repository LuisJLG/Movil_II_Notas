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
package com.example.juicetracker.data.Repositorio

import com.example.juicetracker.data.Model.Tarea
import kotlinx.coroutines.flow.Flow

/**
 * Interface for [TareaRepository] which contains method to access and modify juice items
 */
interface TareaRepository {
    val tareaStream: Flow<List<Tarea>>
    suspend fun addTarea(tarea: Tarea)
    suspend fun deleteTarea(tarea: Tarea)
    suspend fun updateTarea(tarea: Tarea)
}
