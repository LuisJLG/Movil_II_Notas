package com.example.juicetracker.mapas.mapasosmandroidcompose

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import com.example.juicetracker.mapas.network.OpenRouteServiceApi
import com.example.juicetracker.data.Model.GeoJSONDirection
import org.osmdroid.util.GeoPoint
import kotlin.coroutines.coroutineContext

data class DirectUIstate(var resp: GeoJSONDirection? = null)
class OpenRouteServiceViewModel : ViewModel() {

    var directUiState : MutableState<DirectUIstate> = mutableStateOf(DirectUIstate())

    init{
        directions_get("driving-car",
            GeoPoint(20.139261336104898, -101.15026781862757),
            GeoPoint(20.142110828753893, -101.1787275290486),
        )
    }

    fun directions_get( profile: String,  start: GeoPoint,  end: GeoPoint){

        viewModelScope.launch(Dispatchers.IO) {

            val route = OpenRouteServiceApi.retrofitService.directions_get(
                profile = "driving-car",
                start = "${start.longitude},${start.latitude}",
                end = "${end.longitude},${end.latitude}"
            )
            //val route = OpenRouteServiceApi.retrofitService.directions_get()
            directUiState.value = DirectUIstate(route)
            Log.d("GIVO ","")

        }
    }

}