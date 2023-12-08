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
package com.example.juicetracker.data.RepositorioRoom

import com.example.juicetracker.data.Model.Tarea
import com.example.juicetracker.data.JuiceDao
import com.example.juicetracker.data.Repositorio.TareaRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [TareaRepository] interface
 * which allow access and modification of Juice items through [JuiceDao]
 */
class RoomTareaRepository(private val tareaDao: JuiceDao) : TareaRepository {
    override val tareaStream: Flow<List<Tarea>> = tareaDao.getAllTarea()

    override suspend fun addTarea(tarea: Tarea) = tareaDao.insertTarea(tarea)
    override suspend fun deleteTarea(tarea: Tarea) = tareaDao.deleteTarea(tarea)
    override suspend fun updateTarea(tarea: Tarea) = tareaDao.updateTarea(tarea)
}