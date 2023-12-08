package com.example.juicetracker

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.example.juicetracker.providers.ComposeFileProvider

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
) {

    // 1
    var hasImage by remember {
        mutableStateOf(false)
    }
    var hasVideo by remember {
        mutableStateOf(false)
    }
    // 2
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            // TODO
            // 3
            hasImage = uri != null
            imageUri = uri
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )

    val videoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo(),
        onResult = { success ->
            hasVideo = success
        }
    )

    val context = LocalContext.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // 4
        /*if ((hasImage or hasVideo) && imageUri != null) {
            // 5
            if(hasImage){
            AsyncImage(
                model = imageUri,
                modifier = Modifier
                    .fillMaxWidth(1f/2f)
                    .padding(all = 15.dp),
                contentDescription = "Selected image",
            )
            }
            if(hasVideo) {
                VideoPlayer(videoUri = imageUri!!,
                    modifier = Modifier.padding(all = 15.dp)
                )
            }
        }
        */
        Spacer(modifier = Modifier.padding(all = 15.dp))

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Column (
                    modifier = Modifier
                        .weight(1f/3f)
                        .padding(start = 15.dp)
                ){
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        onClick = { imagePicker.launch("image/*") },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.galeria),
                            contentDescription = "Seleccionar imagen",
                        )
                    }
                }
                Column (modifier = Modifier.weight(1f/3f)
                ){
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 8.dp, end = 8.dp),
                        onClick = {
                            val uri = ComposeFileProvider.getImageUri(context)
                            imageUri = uri
                            cameraLauncher.launch(uri) },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.camara),
                            contentDescription = "Tomar foto",
                        )
                    }
                }
                Column (
                    modifier = Modifier
                        .weight(1f/3f)
                ){
                    Button(
                        modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                        onClick = {
                            val uri = ComposeFileProvider.getImageUri(context)
                            imageUri = uri
                            videoLauncher.launch(uri) },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.video),
                            contentDescription = "Tomar video",
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun VideoPlayer(videoUri: Uri, modifier: Modifier = Modifier.fillMaxWidth()) {
    val context = LocalContext.current
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
        }
    }
    val playbackState = exoPlayer
    val isPlaying = playbackState?.isPlaying ?: false

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
        modifier = modifier
    )

    IconButton(
        onClick = {
            if (isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
        },
        modifier = Modifier
            //.align(Alignment.BottomEnd)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = if (isPlaying) Icons.Filled.Refresh else Icons.Filled.PlayArrow,
            contentDescription = if (isPlaying) "Pause" else "Play",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}
