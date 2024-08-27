package com.shcl.smarthealth.presentation.ui.common


import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun ExoPlayerView(
    title : String,
    onShowDialog : Boolean,
    onClickCancel : ()-> Unit,
    uri : String){

    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    // Create a MediaSource
    val mediaSource = remember(uri) {
        MediaItem.fromUri(uri)
    }

    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    // Manage lifecycle events
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Use AndroidView to embed an Android View (PlayerView) into Compose

    if(onShowDialog){
        Dialog(
            onDismissRequest = {onClickCancel},
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true),
        ){
            Column {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        "$title",
                        style = Typography.headlineMedium,
                        fontSize = 20f.pxToSp(),
                        color = Color1E1E1E
                    )

                    IconButton(
                        onClick = { onClickCancel }
                    ) {
                        Image(
                            modifier = Modifier.size(32.pxToDp(), 32.pxToDp()),
                            painter = painterResource(id = R.drawable.btn_close),
                            contentDescription = null
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(18f.pxToDp()),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(18f.pxToDp()))
                        .background(Color.White)
                        .defaultMinSize(minWidth = 1074f.pxToDp(), minHeight = 707f.pxToDp())
                        .padding(
                            start = 40f.pxToDp(),
                            end = 40f.pxToDp(),
                            top = 40f.pxToDp(),
                            bottom = 40f.pxToDp()
                        )
                ) {
                        AndroidView(
                           // shape = RoundedCornerShape(18f.pxToDp()),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(18f.pxToDp()))
                                .background(Color.White)
                                .defaultMinSize(minWidth = 1074f.pxToDp(), minHeight = 707f.pxToDp())
                                .padding(
                                    start = 40f.pxToDp(),
                                    end = 40f.pxToDp(),
                                    top = 40f.pxToDp(),
                                    bottom = 40f.pxToDp()
                                ),
                            factory = { ctx ->
                                PlayerView(ctx).apply {
                                    player = exoPlayer
                                }
                            },
                        )
                }
            }
            }
        }
}