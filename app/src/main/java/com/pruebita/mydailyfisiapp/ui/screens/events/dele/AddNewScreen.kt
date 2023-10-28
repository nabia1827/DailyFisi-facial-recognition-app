package com.pruebita.mydailyfisiapp.ui.screens.events.dele

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pruebita.mydailyfisiapp.ui.theme.poppins


@Preview(showBackground = true)
@Composable
fun PreviewAddNewScreen(){
    val navController = rememberNavController()
    AddNewScreen(navController)
}
@Composable
fun AddNewScreen(navController: NavHostController) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/imagenesnoticias/sin_imagen.png"))
    }
    // Selected image from gallery
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        selectedImageUri = it
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFF))
            .padding(25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        item {
            HeaderAddNew(navController)
        }
        item {
            FieldAddNew(
                title = "Contenido",
                isSingleLine = false, 12,20
            )
            FieldUpload(photoPickerLauncher) { selectedImageUri }
        }
        item {
            ButtonAddNew(navController)
        }
    }

}

@Composable
fun FieldUpload(
    photoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    getImage: () -> Uri?
) {
    FieldUploadImage(photoPickerLauncher,getImage)

}



@Composable
fun FieldUploadImage(
    photoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    getImage: () -> Uri?
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Imagen (Opcional)",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        )

    }
    Column(
        modifier = Modifier
            .fillMaxWidth().height(200.dp)
            .background(
                color = Color(0xFFF1F5F9),
                shape = RoundedCornerShape(size = 10.dp)
            )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getImage())
                    .build(),
                contentDescription = "This is an example image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(7.dp)),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .background(
                        color = Color(0x99000000),
                        shape = RoundedCornerShape(size = 8.dp)
                    ).fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                IconButton(onClick = { photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
                }) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "icon",
                        tint = Color.White,
                    )


                }


            }

        }
    }
}


@Composable
fun ButtonAddNew(navController: NavHostController) {
    ElevatedButton(
        onClick = { navController.popBackStack()},
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0xFFB3B6C4)

        ), contentPadding = PaddingValues(),
        enabled = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                    ),
                    shape = RoundedCornerShape(22.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Crear Noticia", fontSize = 16.sp, fontFamily = poppins)
        }
    }
}





@OptIn(ExperimentalTextApi::class)
@Composable
fun HeaderAddNew(navController: NavHostController) {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier.weight(0.2f)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        brush = brush
                    )

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White,
                )

            }
        }

        Column(
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                text = "Nueva \nNoticia",
                style = TextStyle(
                    brush = brush,
                    fontSize = 34.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,

                    )
            )

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldAddNew(title: String, isSingleLine: Boolean, minLines: Int, maxLines: Int) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(top = 18.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            )

        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            enabled = true,
            textStyle = TextStyle(
                fontSize = 13.sp,
                fontFamily = poppins,
                fontWeight = FontWeight(400),
                color = Color.Black,

                textAlign = TextAlign.Start,
            ),
            placeholder = {
                Text(
                    text = "",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF49454F),
                        textAlign = TextAlign.Start,
                    )
                )
            },
            singleLine = isSingleLine,
            minLines = minLines,
            maxLines = maxLines,
            shape = RoundedCornerShape(size = 14.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFFF1F5F9),
                unfocusedBorderColor = Color(0xFFF1F5F9),
                focusedBorderColor = Color(0xFF495ECA),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,

                ),

            )
    }
}