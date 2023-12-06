package com.example.juicetracker.data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Multimediab (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idNote: Int,
    val type: String,
    val path: String,
    val description: String
) /*{
    constructor(idNote: Int, type: String, path: String, description: String)
            : this(0, idNote, type, path, description )
}*/


@Entity
class Multimedia (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uri: String,
    val tipo: Int?,
    val idNota: Int?,
    val idTarea: Int?,
    val descripcion: String?,
    val ruta: String?
) /*{
    constructor(uri: String, tipo: Int, idNota: Int, idTarea: Int, type: String, path: String, description: String)
            : this(0, idNote, type, path, description )
}*/