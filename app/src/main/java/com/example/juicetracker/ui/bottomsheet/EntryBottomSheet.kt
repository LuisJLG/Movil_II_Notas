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
package com.example.juicetracker.ui.bottomsheet

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.juicetracker.ImagePicker
import com.example.juicetracker.R
import com.example.juicetracker.data.Model.Juice
import com.example.juicetracker.data.Model.JuiceColor
//import com.example.juicetracker.mapas.location.CurrentLocationScreen
import com.example.juicetracker.mapas.location.PermissionBox
import com.example.juicetracker.mapas.mapasosmandroidcompose.OSMComposeMapa
import com.example.juicetracker.ui.JuiceTrackerViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Locale




// Optando por la API ExperimentalMaterial3
@OptIn(ExperimentalMaterial3Api::class)
// Función componible para la interfaz de usuario de la hoja inferior
@Composable
fun EntryBottomSheet(
    juiceTrackerViewModel: JuiceTrackerViewModel,
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    // Recopilando el estado actual del jugo mediante un StateFlow
    val juice by juiceTrackerViewModel.currentJuiceStream.collectAsState()

    // Construyendo el BottomSheetScaffold con encabezado, formulario y contenido personalizado
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetContent = {
            Column {
                // Sección de encabezado de la hoja inferior
                SheetHeader(Modifier.padding(dimensionResource(R.dimen.padding_small)))
                // Sección de formulario de la hoja inferior
                SheetForm(
                    juice = juice,
                    onUpdateJuice = juiceTrackerViewModel::updateCurrentJuice,
                    onCancel = onCancel,
                    onSubmit = onSubmit,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                )

            }
        }
    ) {
        // Contenido personalizado proporcionado por el llamador
        content()
    }
}

// Función componible para el encabezado de la hoja inferior
@Composable
fun SheetHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        // Texto del titular para la hoja inferior
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            text = stringResource(R.string.bottom_sheet_headline),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        // Línea divisoria
        Divider()
    }
}

// Función componible para el contenido del formulario de la hoja inferior
@Composable
fun SheetForm(
    juice: Juice,
    onUpdateJuice: (Juice) -> Unit,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
){

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        //content =
    ) {
        /*item {
            // Fila de entrada para el nombre del jugo
            TextInputRow(
                inputLabel = stringResource(R.string.juice_name),
                fieldValue = juice.name,
                onValueChange = { name -> onUpdateJuice(juice.copy(name = name)) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            // Fila de entrada para la descripción del jugo
            TextInputRow(
                inputLabel = stringResource(R.string.juice_description),
                fieldValue = juice.description,
                onValueChange = { description -> onUpdateJuice(juice.copy(description = description)) },
                modifier = Modifier.fillMaxWidth()
            )
        }*/
        /*item {
            // Fila del selector de color para seleccionar el color del jugo
            ColorSpinnerRow(
                colorSpinnerPosition = findColorIndex(juice.color),
                onColorChange = { color ->
                    onUpdateJuice(juice.copy(color = JuiceColor.values()[color].name))
                }
            )
        }*/
        /*item {
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                AlarmasScreen(
                    alarmScheduler = AlarmSchedulerImpl(applicationContext))
            }*/
            ImagePicker()
        }*/
        /*item {
            // Fila de botones con botones de cancelar y enviar
            ButtonRow(
                modifier = Modifier
                    //.align(Alignment.End)
                    .padding(bottom = dimensionResource(R.dimen.padding_medium)),
                onCancel = onCancel,
                onSubmit = onSubmit,
                submitButtonEnabled = juice.name.isNotEmpty(),
            )
        }*/
        item {
            CurrentLocationScreen()
        }
    }


}

// Función componible para una fila con botones de cancelar y enviar
@Composable
fun ButtonRow(
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    submitButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Botón de cancelar
        OutlinedButton(
            onClick = onCancel,
            border = null
        ) {
            Text(stringResource(android.R.string.cancel).uppercase(Locale.getDefault()))
        }
        // Botón de enviar
        Button(
            onClick = onSubmit,

        ) {
            Text(stringResource(R.string.save).uppercase(Locale.getDefault()))
        }


    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //ACTUA COMO UN BOTON
        ImageSelectionButton { selectedUri ->

            println("Imagen seleccionada: $selectedUri")
        }


    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //ACTUA COMO UN BOTON
        ImageSelectionButton { selectedUri ->

            println("Imagen seleccionada: $selectedUri")
        }

    }
    Row(
            modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //ACTUA COMO UN BOTON
        ImageSelectionButton { selectedUri ->

            println("Imagen seleccionada: $selectedUri")
        }

    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //ACTUA COMO UN BOTON
        ImageSelectionButton { selectedUri ->

            println("Imagen seleccionada: $selectedUri")
        }

    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //ACTUA COMO UN BOTON
        ImageSelectionButton { selectedUri ->

            println("Imagen seleccionada: $selectedUri")
        }

    }
}

// Función componible para una fila con un campo de entrada de texto
@Composable
fun TextInputRow(
    inputLabel: String,
    fieldValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Fila de entrada genérica con un campo de texto
    InputRow(inputLabel, modifier) {
        TextField(
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small)),
            value = fieldValue,
            onValueChange = onValueChange,
            singleLine = true,
            maxLines = 1,
            // Personalización de los colores del campo de texto según el tema Material
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
    }
}

// Función componible para una fila de entrada genérica con etiqueta y contenido
@Composable
fun InputRow(
    inputLabel: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // Fila que contiene una etiqueta y contenido
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Texto de la etiqueta
        Text(
            text = inputLabel,
            fontWeight = FontWeight.SemiBold,
            // La etiqueta ocupa 1/3 del espacio disponible
            modifier = modifier.weight(1f)
        )
        // El contenido ocupa 2/3 del espacio disponible
        Box(modifier = Modifier.weight(2f)) {
            content()
        }
    }
}

// Función para encontrar el índice de un color dado en el enum JuiceColor
private fun findColorIndex(color: String): Int {
    val juiceColor = JuiceColor.valueOf(color)
    return JuiceColor.values().indexOf(juiceColor)
}

//METODO TIPO BOTON PARA Las imagenes//Efren//
@Composable
fun ImageSelectionButton(
    onImageSelected: (Uri) -> Unit
) {
    // Crear el launcher para la actividad de selección de imagen
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { result ->
            if (result != null) {
                // Lógica para manejar la imagen seleccionada
                onImageSelected(result)
            }
        }
    )

    // Botón que activa la selección de imagen desde el almacenamiento
    Button(
        onClick = {
            // Lanzar la actividad de selección de imagen
            launcher.launch("image/*")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(text = "Seleccionar imagen")
    }
}

@SuppressLint("MissingPermission")
@Composable
fun CurrentLocationScreen() {
    val permissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    PermissionBox(
        permissions = permissions,
        requiredPermissions = listOf(permissions.first()),
        onGranted = {
            CurrentLocationContent(
                usePreciseLocation = it.contains(Manifest.permission.ACCESS_FINE_LOCATION),
            )
        },
    )
}

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun CurrentLocationContent(usePreciseLocation: Boolean) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var controlmapa by remember { mutableStateOf(false) }
    var longitud by remember { mutableStateOf(0.0) }
    var latitud by remember { mutableStateOf(0.0) }
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationInfo by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                // getting last known location is faster and minimizes battery usage
                // This information may be out of date.
                // Location may be null as previously no client has access location
                // or location turned of in device setting.
                // Please handle for null case as well as additional check can be added before using the method
                scope.launch(Dispatchers.IO) {
                    val result = locationClient.lastLocation.await()
                    locationInfo = if (result == null) {
                        "No last known location. Try fetching the current location first"
                    } else {
                        "Current location is \n" + "lat : ${result.latitude}\n" +
                                "long : ${result.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
                    }
                }
            },
        ) {
            Text("Get last known location")
        }

        Button(
            onClick = {
                //To get more accurate or fresher device location use this method
                scope.launch(Dispatchers.IO) {
                    val priority = if (usePreciseLocation) {
                        Priority.PRIORITY_HIGH_ACCURACY
                    } else {
                        Priority.PRIORITY_BALANCED_POWER_ACCURACY
                    }
                    val result = locationClient.getCurrentLocation(
                        priority,
                        CancellationTokenSource().token,
                    ).await()
                    result?.let { fetchedLocation ->
                        locationInfo =
                            "Current location is \n" + "lat : ${fetchedLocation.latitude}\n" +
                                    "long : ${fetchedLocation.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
                        longitud = fetchedLocation.longitude
                        latitud = fetchedLocation.latitude
                    }
                    controlmapa = true
                }
            },
        ) {
            Column {
                Text(text = "Get current location")
            }
        }
        Text(
            text = locationInfo+"${controlmapa}",
        )

        Spacer(modifier = Modifier.height(190.dp))
        if (controlmapa){
            OSMComposeMapa(
                longitud = longitud,
                latitud = (latitud)
            )
        }

    }
}

