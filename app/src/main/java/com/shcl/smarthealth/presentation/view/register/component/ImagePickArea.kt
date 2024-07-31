package com.shcl.smarthealth.presentation.view.register.component

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.Utils.dp
import com.shcl.smarthealth.domain.utils.pxToDp
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyImageArea(
    uri: Uri? = null, //target url to preview
    directory: File? = null, // stored directory
    onSetUri : (Uri) -> Unit, // selected / taken uri
) {
    val context = LocalContext.current
    var tempUri by remember { mutableStateOf<Uri?>(null) }
    val authority = stringResource(id = R.string.fileprovider)

    // for takePhotoLauncher used
    fun getTempUri(): Uri? {
        directory?.let {
            it.mkdirs()
            val file = File.createTempFile(
                "image_" + System.currentTimeMillis().toString(),
                ".jpg",
                it
            )

            return FileProvider.getUriForFile(
                context,
                authority,
                file
            )
        }
        return null
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri->

            uri?.let{
                tempUri = uri
                Log.d("register" , "${tempUri}")
                onSetUri.invoke(uri)
            }
        }
    )

    val takePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { isSaved ->
            tempUri?.let {
                tempUri = it
                Log.d("register" , "${tempUri}")
                onSetUri.invoke(it)
            }
        }
    )

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted, launch takePhotoLauncher
            val tmpUri = getTempUri()
            tempUri = tmpUri

            tempUri?.let {
                takePhotoLauncher.launch(it)
            }
        } else {
            // Permission is denied, handle it accordingly
        }
    }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    AsyncImage(
        contentScale = ContentScale.Crop,
        contentDescription = "User",
        modifier =  Modifier
            .size(200.pxToDp())
            .conditional(tempUri != null ){
                clip(CircleShape)
            }
            .clickable { showBottomSheet = true },
        model = ImageRequest.Builder(LocalContext.current).data(tempUri).build(),
        error = painterResource(id = R.drawable.reg_picture)
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 350f.pxToDp())
            ) {
                Button(
                    onClick = {
                        //showBottomSheet = true
                    }
                ) {
                    Text(modifier = Modifier.clickable {
                        showBottomSheet = false
                        imagePicker.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }, text = "사진 선택")

                }

                Spacer(modifier = Modifier.height(50f.pxToDp()))

                Button(
                    onClick = {
                        //showBottomSheet = true
                    }
                ) {
                    Text(modifier = Modifier.clickable {
                        showBottomSheet = false
                        val permission = Manifest.permission.CAMERA
                        if (ContextCompat.checkSelfPermission(
                                context,
                                permission
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            // Permission is already granted, proceed to step 2
                            val tmpUri = getTempUri()
                            tempUri = tmpUri
                            tempUri?.let {
                                takePhotoLauncher.launch(it)
                            }
                        } else {
                            // Permission is not granted, request it
                            cameraPermissionLauncher.launch(permission)
                        }
                    }, text = "사진 찍기")
                }

                //preview selfie
                /*
                uri?.let {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(200.pxToDp(), 200.pxToDp()),
                            painter = painterResource(id = R.drawable.reg_picture),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(16f.pxToDp()))
                }*/

            }

        }

    }
}

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}