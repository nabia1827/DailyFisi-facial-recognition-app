package com.pruebita.mydailyfisiapp.ui.screens.events

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import com.pruebita.mydailyfisiapp.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun EventsScreen() {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/imageneseventos/101.jpg"))
    }
    var img1 by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/fotosperfil/persona_prueba1.png"))
    }
    var img2 by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/fotosperfil/persona_prueba2.png"))
    }
    var img3 by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/fotosperfil/persona_prueba3.png"))
    }

    val uris_goers = remember { mutableStateListOf(img1, img2, img3) }

    var isTrendSection by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFF)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {
                        HeaderEventsScreen()
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    ) {
                        Text(text = "       ")
                    }


                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(start = 10.dp),
                ) {
                    item {
                        FilterCard(Color(0xFFF25E56), R.drawable.pelota_ic, "Deportes")
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                    item {
                        FilterCard(Color(0xFFFA9032), R.drawable.book_ic, "Estudios")
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                    item {
                        FilterCard(Color(0xFF29D697), R.drawable.medal_ic, "Logros")
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                }

            }
        }
        item {
            Spacer(modifier = Modifier.padding(9.dp))
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    SelectorPosts({isTrendSection}){
                        newValue:Boolean ->
                        isTrendSection = newValue
                    }
                }
                Spacer(modifier = Modifier.padding(7.dp))
                if(isTrendSection){
                    TrendSection(selectedImageUri,uris_goers,img1)
                }
                else{
                    PersonalSection(selectedImageUri,uris_goers,img1)
                }


            }
        }
    }
}

@Composable
fun PersonalSection(selectedImageUri:Uri?, uris_goers: SnapshotStateList<Uri?>, img1: Uri?) {
    Column(
        modifier = Modifier.padding(7.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            UpcomingEvents(selectedImageUri,uris_goers, 23,true)
        }
        Spacer(modifier = Modifier.padding(7.dp))
        Column() {
            TrendingNews(img1,true)
        }
    }
}

@Composable
fun TrendSection(selectedImageUri:Uri?, uris_goers: SnapshotStateList<Uri?>, img1: Uri?) {
    Column(
        modifier = Modifier.padding(7.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            UpcomingEvents(selectedImageUri,uris_goers, 23,false)
        }
        Spacer(modifier = Modifier.padding(7.dp))
        Column() {
            TrendingNews(img1,false)
        }
    }
}

@Composable
fun SelectorPosts(getTrendSection: () -> Boolean,changeIsTrendSection: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
                .padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ButtonTrends(getTrendSection,changeIsTrendSection)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
                .padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ButtonMyPosts(getTrendSection,changeIsTrendSection)

        }


    }
}

@Composable
fun ButtonMyPosts(
    getTrendSection: () -> Boolean,
    changeIsTrendSection: (Boolean) -> Unit
) {
    val brushSelected = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )

    val brushNotSelected = Brush.horizontalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFFFFF))
    )


    ElevatedButton(
        onClick = {
            if(!getTrendSection()){
                
            }else{
                changeIsTrendSection(false)
            }
        },
        modifier = Modifier
            .fillMaxSize(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if(!getTrendSection()) Color(0xFFFFFFFF) else Color(0xFF8B97A8),
            disabledContainerColor = Color(0xFFB3B6C4)

        ), contentPadding = PaddingValues(), //Es soluuu no tocar
        enabled = true,
        shape = RoundedCornerShape(14.dp),
        elevation =ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )

    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = if (!getTrendSection()) brushSelected else brushNotSelected

                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.folder_edit),
                contentDescription = "my posts",
                tint = if(!getTrendSection()) Color(0xFFFFFFFF) else Color(0xFF8B97A8),
            )
            Text(text = "Mis Posts", fontSize = 16.sp, fontFamily = poppins)
        }

    }
}

@Composable
fun ButtonTrends(
    getTrendSection: () -> Boolean,
    changeIsTrendSection: (Boolean) -> Unit
) {

    val brushSelected = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )

    val brushNotSelected = Brush.horizontalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFFFFF))
    )

    ElevatedButton(
        onClick = {
            if(getTrendSection()) {

            }
            else{
                changeIsTrendSection(true)

            }
        },
        modifier = Modifier
            .fillMaxSize(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if(getTrendSection()) Color(0xFFFFFFFF) else Color(0xFF8B97A8),
            disabledContainerColor = Color(0xFFB3B6C4)

        ),
        contentPadding = PaddingValues(), //Es soluuu no tocar
        enabled = true,
        shape = RoundedCornerShape(14.dp),
        elevation =ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = if (getTrendSection()) brushSelected else brushNotSelected
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.chart_line),
                contentDescription = "my posts",
                tint = if(getTrendSection()) Color(0xFFFFFFFF) else Color(0xFF8B97A8),
            )
            Text(text = "Tendencias", fontSize = 16.sp, fontFamily = poppins)
        }

    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun TrendingNews(img1: Uri?, editable: Boolean) {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }

    Column {
        Spacer(modifier = Modifier.padding(3.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Noticias en Tendencia ",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            )
            if(editable){
                TextButton(onClick = {}) {
                    Text(
                        text = "+ Agregar",
                        style = TextStyle(brush = brush)
                    )

                }
            }

        }
        Spacer(modifier = Modifier.padding(3.dp))
        Column(

        ) {

            NewPostCard(img1,null, editable)
            Spacer(modifier = Modifier.padding(7.dp))
            NewPostCard(img1, img1,editable)

        }
    }

}

@Composable
fun NewPostCard(img1: Uri?,img2: Uri?, editable: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White

        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ){
        NewsPostHeader(img1, editable)
        NewsPostContent(
            uri = img2,
            content = "Lorem \uD83D\uDE0A Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
            maxLines = 4
        )
        NewsPostReactions()

    }
}

@Composable
fun NewsPostReactions() {
    Row(
        modifier = Modifier.padding(3.dp)
    ) {
        ReactionButton(R.drawable.heart_react_ic, 14, Color(0xFFFF1744))
        ReactionButton(R.drawable.hand_react_ic, 11, Color(0xFF2F68D7))
        ReactionButton(R.drawable.sad_react_ic, 10, Color(0xFFE8AB0C))

    }

}

@Composable
fun ReactionButton(idIcon: Int, value:Int, colorActive: Color) {
    var isPressed by rememberSaveable { mutableStateOf(false) }
    ElevatedButton(
        onClick = { isPressed= !isPressed},
        modifier = Modifier.padding(2.dp),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color.White,
            contentColor =colorActive

        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp
        )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = idIcon),
                contentDescription = "my posts",
                tint = if (isPressed) colorActive else Color.Black,
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = "$value",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(400),
                    color = if (isPressed) colorActive else Color.Black,

                    )
            )
        }
        
    }
}

@Composable
fun NewsPostContent(uri: Uri?,content: String, maxLines: Int = 4) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(start = 13.dp, end = 13.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = content,
            maxLines = if (expanded) Int.MAX_VALUE else maxLines,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = poppins,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                lineHeight = 17.sp

            )
        )
        if (!expanded && content.length > 270) {

            Text(
                text = "Ver más",
                modifier = Modifier.clickable { expanded = true },
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF747980),

                    )
            )
        }

        if(uri != null){
            Spacer(modifier = Modifier.padding(3.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uri)
                    .build(),
                contentDescription = "This is an example image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp)),
                contentScale = ContentScale.Crop


            )
        }
    }

}

@Composable
fun NewsPostHeader(img1: Uri?, editable: Boolean) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(13.dp)
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier.weight(0.2f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(img1)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = "This is an example image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(45.0.dp)
                    .border(
                        width = 1.33333.dp,
                        color = Color(0xFF853D3D),
                        shape = RoundedCornerShape(size = 62.dp)
                    )

            )

        }
        Column(
            modifier = Modifier.weight(0.6f)
        ) {
            Text(
                text = "Mariano Diaz",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF312E49),

                    )
            )
            Text(
                text = "9 de Agosto",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF747980),

                    )
            )

        }
        Column(
            modifier = Modifier.weight(0.2f),
            horizontalAlignment = Alignment.End
        ) {
            if(editable){
                IconButton(onClick = { expanded = true })
                {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "my posts",
                        tint = Color(0xFF000000),
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    modifier = Modifier.background(Color.White),
                    onDismissRequest = { expanded = false }
                ){
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Editar noticia",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF000000),

                                    )
                            )
                        },
                        onClick = { /* Handle edit! */ },
                        colors = MenuDefaults.itemColors(
                        )
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Eliminar noticia",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF000000),

                                    )
                            )
                        },
                        onClick = { /* Handle edit! */ },
                    )

                }
            }
        }

    }

}

@OptIn(ExperimentalTextApi::class)
@Composable
fun UpcomingEvents(selectedImageUri: Uri?, uris_goers: SnapshotStateList<Uri?>, nGoers: Int, editable: Boolean) {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Próximos Eventos ",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            )
            if(editable){
                TextButton(onClick = {}) {
                    Text(
                        text = "+ Agregar",
                        style = TextStyle(brush = brush)
                    )

                }
            }

        }

        Spacer(modifier = Modifier.padding(7.dp))
        LazyRow(){
            item {
                EventoCard(selectedImageUri,uris_goers, nGoers, editable)
                Spacer(modifier = Modifier.padding(7.dp))
            }
            item{
                EventoCard(selectedImageUri,uris_goers, nGoers,editable)
                Spacer(modifier = Modifier.padding(7.dp))
            }
            item{
                EventoCard(selectedImageUri,uris_goers, nGoers,editable)
                Spacer(modifier = Modifier.padding(7.dp))
            }

        }
    }

}

@Composable
fun TitleEventCard(title: String,editable:Boolean) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier= Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier.weight(0.8f),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),

                    )
            )
        }
        Column(
            modifier = Modifier.weight(0.2f),
            horizontalAlignment = Alignment.End
        ){
            if(editable){
                IconButton(onClick = { expanded = true })
                {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "my posts",
                        tint = Color(0xFF000000),
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White),

                    ){
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Editar evento",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF000000),

                                    )
                            )

                        },
                        onClick = { /* Handle edit! */ },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Eliminar evento",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF000000),

                                    )
                            )
                        },
                        onClick = { /* Handle edit! */ },
                    )

                }
            }
        }

    }
}

@Composable
fun EventoCard(selectedImageUri: Uri?, uris_goers: SnapshotStateList<Uri?>,nGoers:Int, editable: Boolean) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(290.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White

        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
            ) {
                ImageEventCard(selectedImageUri)
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TitleEventCard("Open Day Interbank", editable)
                GoersEventCard(uris_goers, nGoers)
                Spacer(modifier = Modifier.padding(4.dp))
                PlaceEventCard()
            }
        }

    }
}
@Composable
fun PlaceEventCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "my posts",
            tint = Color(0xFF8B97A8),
        )
        Text(
            text = "Carlos Villarán 140, La Victoria",
            style = TextStyle(
                fontSize = 13.sp,
                fontFamily = poppins,
                fontWeight = FontWeight(400),
                color = Color(0xFF2B2849),

                )
        )
    }

}

@Composable
fun GoersEventCard(uris: List<Uri?>, nGoers:Int) {
    var nGoersMinus = remember { mutableIntStateOf(nGoers - 3) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Box(){
            GoerImage(uris[0],0.dp)
            GoerImage(uris[1],17.dp)
            GoerImage(uris[2],34.dp)
        }
        Spacer(modifier = Modifier.padding(7.dp))
        Text(
            text = "+${nGoersMinus.value} asistirán",
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 19.24.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2F68D7),

                )
        )
    }
}

@Composable
fun GoerImage(uri:Uri?, paddingIzq: Dp) {
    Column(
        modifier = Modifier.padding(start = paddingIzq)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(uri)
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = "This is an example image",
            modifier = Modifier
                .clip(CircleShape)
                .size(35.0.dp)
                .border(
                    width = 1.33333.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(size = 62.dp)
                )


        )
    }

}





@Composable
fun ImageEventCard(selectedImageUri: Uri?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(selectedImageUri)
            .build(),
        contentDescription = "This is an example image",
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop


    )
}


@Composable
fun FilterCard(color: Color, id: Int, text: String) {
    Row(
        modifier = Modifier
            .width(140.dp)
            .height(55.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(size = 20.96263.dp)
            )
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Icon(
            painter = painterResource(id = id),
            contentDescription = "ball",
            tint = Color.White,
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 25.sp,
                fontFamily = poppins,
                fontWeight = FontWeight(600),
                color = Color.White,
            )
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderEventsScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                    ),
                ),
        ) {

        }
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FISI - UNMSM",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),

                    textAlign = TextAlign.Center,
                )
            )

            Text(
                text = "Lima, Peru",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF4F4FE),

                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.padding(2.dp))

            OutlinedTextField(
                value = "",
                onValueChange = { },
                enabled = true,
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF4F4FE),

                    textAlign = TextAlign.Start,
                ),
                placeholder = {
                    Text(
                        text = "Realizar busqueda",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFF4F4FE),

                            textAlign = TextAlign.Center,
                        )
                    )
                },
                leadingIcon =
                {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "User",
                        tint = Color.White
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(size = 50.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0x8FFFFFFF),
                    unfocusedBorderColor = Color(0x8FFFFFFF),
                    focusedBorderColor = Color(0x8FFFFFFF),
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,

                    ),

                )

        }


    }
}