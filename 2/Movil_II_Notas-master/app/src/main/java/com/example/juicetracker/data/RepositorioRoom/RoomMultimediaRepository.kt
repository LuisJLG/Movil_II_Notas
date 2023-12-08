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

import com.example.juicetracker.data.Model.Multimedia
import com.example.juicetracker.data.MultimediaDao
import com.example.juicetracker.data.Repositorio.MultimediaRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [MultimediaRepository] interface
 * which allow access and modification of Juice items through [MultimediaDao]
 */
class RoomMultimediaRepository(private val multimediaDao: MultimediaDao) : MultimediaRepository {
    override val multimediaStream: Flow<List<Multimedia>> = multimediaDao.getMultimedia(idNota = 1)

    override suspend fun addMultimedia(multimedia: Multimedia): Unit = multimediaDao.insertMultimedia(multimedia)
    override suspend fun deleteMultimedia(multimedia: Multimedia) = multimediaDao.deleteMultimedia(multimedia)
}
