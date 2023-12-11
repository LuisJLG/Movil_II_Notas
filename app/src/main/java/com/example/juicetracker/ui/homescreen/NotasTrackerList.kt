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
package com.example.juicetracker.ui.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.juicetracker.R
import com.example.juicetracker.data.Model.Nota

@Composable
fun NotaTrackerList(
    notas: List<Nota>,
    onDelete: (Nota) -> Unit,
    onUpdate: (Nota) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.padding_small)),
    ) {
        items(items = notas) { nota ->
            NotaTrackerListItem(
                nota = nota,
                onDelete = onDelete,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onUpdate(nota) }
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
            )
        }
    }
}

@Composable
fun NotaTrackerListItem(
    nota: Nota,
    onDelete: (Nota) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //JuiceIcon(juice.color)
        NotaDetails(nota, Modifier.weight(1f))
        DeleteButtonNota(
            onDelete = {
                onDelete(nota)
            },
            modifier = Modifier.align(Alignment.Top)
        )
    }
}

@Composable
fun NotaDetails(nota: Nota, modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.Top) {
        Text(
            text = nota.noteTitle,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Text(nota.noteBody)
    }
}

@Composable
fun DeleteButtonNota(onDelete: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { onDelete() },
    ) {
        Icon(
            modifier = modifier,
            painter = painterResource(R.drawable.delete_icon),
            contentDescription = stringResource(R.string.delete)
        )
    }
}

/*@Preview
@Composable
fun NotaTrackerListPreview() {
    MaterialTheme {
        NotaTrackerList(
            notas = listOf(
                Nota(1, "Mango", "Yummy!", "Yellow", imageUri = null, videoUri = null, audioUri = null, 5),
                Nota(2, "Orange", "Refreshing~", "Orange", imageUri = null, videoUri = null, audioUri = null, 4),
                Nota(3, "Grape", "Refreshing~", "Magenta", imageUri = null, videoUri = null, audioUri = null, 2),
                Nota(4, "Celery", "Refreshing~", "Green", imageUri = null, videoUri = null, audioUri = null, 1),
                Nota(5, "ABC", "Refreshing~", "Red", imageUri = null, videoUri = null, audioUri = null, 4)

            ),
            onDelete = {},
            onUpdate = {})
    }
}*/
