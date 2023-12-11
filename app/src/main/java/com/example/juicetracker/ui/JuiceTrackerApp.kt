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

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.juicetracker.alarmas.AlarmSchedulerImpl
import com.example.juicetracker.alarmas.AlarmasScreen
//import com.example.juicetracker.alarmas.AlarmasScreen
import com.example.juicetracker.contexto.contecsto

import com.example.juicetracker.ui.bottomsheet.EntryBottomSheet
import com.example.juicetracker.ui.bottomsheet.EntryBottomSheetTarea
import com.example.juicetracker.ui.homescreen.JuiceTrackerFAB
import com.example.juicetracker.ui.homescreen.JuiceTrackerList
import com.example.juicetracker.ui.homescreen.JuiceTrackerTopAppBar
import com.example.juicetracker.ui.homescreen.TareaTrackerFAB
import com.example.juicetracker.ui.homescreen.NotaTrackerList

import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuiceTrackerApp(
    juiceTrackerViewModel: JuiceTrackerViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val estado by remember {
        mutableStateOf(contecsto.getApplicationContext())
    }
    var controlSheet by remember { mutableStateOf(true) }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val bottomSheetScaffoldStateTarea = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val scope = rememberCoroutineScope()
    val trackerState by juiceTrackerViewModel.juiceListStream.collectAsState(emptyList())

    val trackerStateNota by juiceTrackerViewModel.notaListStream.collectAsState(emptyList())


    val scopeTarea = rememberCoroutineScope()
    val trackerStateTarea by juiceTrackerViewModel.tareaListStream.collectAsState(emptyList())

    if (controlSheet){
        EntryBottomSheetTarea(
            juiceTrackerViewModel = juiceTrackerViewModel,
            sheetScaffoldState = bottomSheetScaffoldStateTarea,
            modifier = Modifier,
            onCancel = {
                scopeTarea.launch {
                    bottomSheetScaffoldStateTarea.bottomSheetState.hide()
                }
            },
            onSubmit = {
                juiceTrackerViewModel.saveNota()
                scopeTarea.launch {
                    bottomSheetScaffoldStateTarea.bottomSheetState.hide()
                }
            }
        ) {
            Scaffold(
                topBar = {
                    JuiceTrackerTopAppBar()
                },
                floatingActionButton = {
                    Column {
                        JuiceTrackerFAB(
                            onClick = {
                                juiceTrackerViewModel.resetCurrentNota()
                                scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                                controlSheet = true
                            }
                        )
                        TareaTrackerFAB(
                            onClick = {
                                juiceTrackerViewModel.resetCurrentTarea()
                                scopeTarea.launch { bottomSheetScaffoldStateTarea.bottomSheetState.expand()}
                                controlSheet = false
                            }
                        )
                    }

                }
            ) { contentPadding ->
                Column(Modifier.padding(contentPadding)) {
                    /*AdBanner(
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(R.dimen.padding_medium),
                                bottom = dimensionResource(R.dimen.padding_small)
                            )
                    )*/
                    NotaTrackerList(
                        notas = trackerStateNota,
                        onDelete = { nota -> juiceTrackerViewModel.deleteNota(nota) },
                        onUpdate = { nota ->
                            juiceTrackerViewModel.updateCurrentNota(nota)
                            scope.launch {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        },
                    )
                }
            }
        }
    }
    else{
        EntryBottomSheet(
            juiceTrackerViewModel = juiceTrackerViewModel,
            sheetScaffoldState = bottomSheetScaffoldState,
            modifier = Modifier,
            onCancel = {
                scope.launch {
                    bottomSheetScaffoldState.bottomSheetState.hide()
                }
            },
            onSubmit = {
                juiceTrackerViewModel.saveNota()
                scope.launch {
                    bottomSheetScaffoldState.bottomSheetState.hide()
                }
            }
        ) {
            Scaffold(
                topBar = {
                    JuiceTrackerTopAppBar()
                },
                floatingActionButton = {
                    Column {
                        JuiceTrackerFAB(
                            onClick = {
                                juiceTrackerViewModel.resetCurrentNota()
                                scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                                controlSheet = true
                            }
                        )
                        TareaTrackerFAB(
                            onClick = {
                                juiceTrackerViewModel.resetCurrentTarea()
                                scopeTarea.launch { bottomSheetScaffoldStateTarea.bottomSheetState.expand()}
                                controlSheet = false
                            }
                        )
                    }

                }
            ) { contentPadding ->
                Column(Modifier.padding(contentPadding)) {
                    /*AdBanner(
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(R.dimen.padding_medium),
                                bottom = dimensionResource(R.dimen.padding_small)
                            )
                    )*/
                    JuiceTrackerList(
                        juices = trackerState,
                        onDelete = { juice -> juiceTrackerViewModel.deleteJuice(juice) },
                        onUpdate = { juice ->
                            juiceTrackerViewModel.updateCurrentJuice(juice)
                            scope.launch {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        },
                    )
                    NotaTrackerList(
                        notas = trackerStateNota,
                        onDelete = { nota -> juiceTrackerViewModel.deleteNota(nota) },
                        onUpdate = { nota ->
                            juiceTrackerViewModel.updateCurrentNota(nota)
                            scope.launch {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        },
                    )
                }
            }
        }
    }
    /*val applicationContext=contecsto.getApplicationContext()
    AlarmasScreen(alarmScheduler = AlarmSchedulerImpl(applicationContext))*/
}
