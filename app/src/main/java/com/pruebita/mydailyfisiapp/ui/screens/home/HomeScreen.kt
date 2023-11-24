package com.pruebita.mydailyfisiapp.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.ClockViewModel
import java.util.Calendar
import kotlin.math.floor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(clockViewModel: ClockViewModel) {

    val currentUser: User by clockViewModel.user.observeAsState(initial = User())

    val listTodayEvents: MutableList<Event>? by clockViewModel.todayEvents.observeAsState(initial = mutableListOf())
    val listCourses: MutableList<Course> by clockViewModel.courses.observeAsState(initial = mutableListOf())

    val pagerState = rememberPagerState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 18.dp, end = 18.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item{
            Spacer(modifier = Modifier.padding(2.dp))
            HeaderHome(currentUser.names,currentUser.idRol) { listTodayEvents?.size ?: 0 }
        }
        item {
            Spacer(modifier = Modifier.padding(4.dp))
            HorizontalPager(pageCount = listTodayEvents?.size ?: 0, state = pagerState) {
                    index ->CardEvent(listTodayEvents?.get(index) ?: Event(), index%3)
            }
        }
        item{
            Spacer(modifier = Modifier.padding(8.dp))
            ClasesSection(clockViewModel)
        }
        item{
            Spacer(modifier = Modifier.padding(8.dp))
            AttendanceSection(listCourses)

        }
    }
}

@Composable
fun AttendanceSection(listCourses: MutableList<Course>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ){
        AttendanceSectionTitle()
        AttendanceSectionContent(listCourses)

    }
}

@Composable
fun AttendanceSectionContent(listCourses: MutableList<Course>) {
    val rows = floor(listCourses.size/2.0).toInt()
    for (i in 0 until rows) {
        AttendanceSectionCard(listCourses[2*i],listCourses[2*i+1])
        Spacer(modifier = Modifier.padding(4.dp))
    }
    if(listCourses.size % 2 != 0){
        AttendanceSectionCard(listCourses[listCourses.size - 1],null)
    }
}

@Composable
fun AttendanceSectionCard(course1: Course, course2: Course?) {
    val backgroundList: MutableList<Int> = mutableListOf(
        R.drawable.espiral_background,
        R.drawable.fondo_evento_2,
        R.drawable.fondo_evento_3)
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                    ),
                    shape = RoundedCornerShape(22.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                /*Image(
                        painter = painterResource(id = backgroundList[0]),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )*/
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    course1.courseName?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 22.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                    Text(
                        text = "Seccion ${course1.section}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }

        }
        Spacer(modifier = Modifier.padding(4.dp))
        if(course2 != null){
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                        ),
                        shape = RoundedCornerShape(22.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    /*Image(
                        painter = painterResource(id = backgroundList[0]),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )*/
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        course2.courseName?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 22.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFFFFFFFF),
                                )
                            )
                        }
                        Text(
                            text = "Seccion ${course2.section}",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                }
            }

        }else{
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFFFFF))
                        ),
                        shape = RoundedCornerShape(22.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }

    }

}

@Composable
fun AttendanceSectionTitle() {
    Row(){
        Text(
            text = "Mis Asistencias ",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        )

    }
}

@Composable
fun ClasesSection(viewModel: ClockViewModel) {
    val pendingCourses:Int by viewModel.pendingCourses.observeAsState(initial = 0)
    val actualCourse: Course? by viewModel.actualCourse.observeAsState(initial = null )
    val nextCourse: Course? by viewModel.nextCourse.observeAsState(initial = null )
    val subNextCourse: Course? by viewModel.subNextCourse.observeAsState(initial = null )
    val isFinished: Boolean by viewModel.isFinished.observeAsState(initial = true)

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ){
        ClasesSectionTitle(pendingCourses)
        if(!isFinished){

            ClasesSectionInstantClass(viewModel,actualCourse)
            if(nextCourse != null){
                Spacer(modifier = Modifier.padding(7.dp))
                ClasesSectionLaterClass(nextCourse,subNextCourse,viewModel)
            }

        }else{
            Image(
                painter = painterResource(id = R.drawable.ic_no_classes),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}

@Composable
fun ClasesSectionLaterClass(nextCourse: Course?, subNextCourse: Course?, viewModel: ClockViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ){
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
                .background(
                    color = Color(0xFFF8F8F8),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (nextCourse != null) {
                LaterClassContent(nextCourse){ calendar -> viewModel.formatHour(calendar) }
            }
        }

        Spacer(modifier = Modifier.padding(5.dp))
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
                .background(
                    color = Color(0xFFF8F8F8),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if(subNextCourse != null){
                LaterClassContent(subNextCourse){ calendar -> viewModel.formatHour(calendar) }
            }else{
                Icon(
                    painter = painterResource(id = R.drawable.book_bookmark),
                    contentDescription = "bookmark",
                    tint = Color(0xFF495ECA),
                )
            }

        }
    }
}

@Composable
fun ClasesSectionInstantClass(viewModel: ClockViewModel, course: Course?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp)
            .background(
                color = Color(0xFFF8F8F8),
                shape = RoundedCornerShape(size = 10.dp)
            )

    ){
        Column(
            modifier = Modifier
                .weight(0.55f)
                .fillMaxHeight()
                .padding(start = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (course != null) {
                InstantClassIzq(course) { calendar -> viewModel.formatHour(calendar) }
            }
        }
        Column(
            modifier = Modifier
                .weight(0.45f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InstantClassDer(viewModel)
        }

    }
}

@Composable
fun InstantClassDer(viewModel: ClockViewModel) {
    val currentTime: String by viewModel.currentTimeText.observeAsState(initial = "")
    val timeRemaining: String by viewModel.timeRemaining.observeAsState(initial = "")
    Text(
        text = timeRemaining,
        style = TextStyle(
            fontSize = 13.sp,
            lineHeight = 15.sp,
            fontFamily = poppins,
            fontWeight = FontWeight(500),
            color = Color(0xFF000000),
        )
    )
    Text(
        text = currentTime,
        style = TextStyle(
            fontSize = 24.sp,
            lineHeight = 36.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF44336),
        )
    )
}
@Composable
fun LaterClassContent(course: Course, dateToString: (Calendar) -> String) {
    course.courseName?.let {
        Text(
        text = it,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            textAlign = TextAlign.Start
        )
    )
    }
    Row(
        modifier = Modifier.height(30.dp)
    ){
        Column (
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        color = Color(0xFFE8EBFF),
                        shape = RoundedCornerShape(size = 103.dp)
                    )
                    .padding(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
            ){
                Text(
                    text = " ${course.theoryPart.room.typeRoom} ${course.theoryPart.room.idRoom} ",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF495ECA),
                    )
                )
            }

        }
        Spacer(modifier = Modifier.padding(4.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.4f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "clock",
                    tint = Color(0xFF495ECA),

                    )
                Text(
                    text = "${dateToString(course.theoryPart.startHour)}",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF495ECA),
                    ),


                    )
            }

        }
    }
}





@Composable
fun InstantClassIzq(course: Course, dateToString: (Calendar) -> String) {
    course.courseName?.let {
        Text(
        text = it,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 24.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            textAlign = TextAlign.Start
        )
    )
    }
    Row(
        modifier = Modifier.height(30.dp)
    ){
        Column (
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        color = Color(0xFFE8EBFF),
                        shape = RoundedCornerShape(size = 103.dp)
                    )
                    .padding(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
            ){
                Text(
                    text = " ${course.theoryPart.room.typeRoom} ${course.theoryPart.room.idRoom} ",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF495ECA),
                    )
                )
            }

        }
        Spacer(modifier = Modifier.padding(4.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "clock",
                    tint = Color(0xFFF44336),

                )
                Text(
                    text = "${dateToString(course.theoryPart.startHour)}",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF44336),
                    ),


                )
            }

        }
    }
}

@Composable
fun ClasesSectionTitle(numOfClasses: Int) {
    Row(){
        Text(
            text = "$numOfClasses Clase(s) pendiente(s) hoy",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        )
        Spacer(modifier = Modifier.padding(4.dp))
        if(numOfClasses !=0){
            Icon(
                painter = painterResource(id = R.drawable.warning),
                contentDescription = "class",
                tint = Color(0xFFF44336)
            )
        }else{
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "class",
                tint = Color.Black
            )
        }

    }
}

@Composable
fun CardEvent(event: Event, i: Int) {
    val backgroundList: MutableList<Int> = mutableListOf(
        R.drawable.fondo_evento_1,
        R.drawable.fondo_evento_2,
        R.drawable.fondo_evento_3)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .background(
                color = Color(0xFF495ECA),
                shape = RoundedCornerShape(size = 20.dp)
            )

    ) {
        Box(){
            Image(
                painter = painterResource(id = backgroundList[i]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            ContentCardEvent(event)
        }

    }
}

@Composable
fun ContentCardEvent(event: Event) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.weight(0.7f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = event.title,
                    style = TextStyle(
                        fontSize = 28.sp,
                        lineHeight = 34.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 0.8.sp,
                    )
                )
                Text(
                    text = "   ",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                    )
                )
            }
            Row(){
                Text(
                    text = event.shortDescription,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
        }
        Column(
            modifier = Modifier.weight(0.3f)
        ) {
            ElevatedButton(
                onClick = {
                          //Navigate with event
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF495EC9),
                    disabledContainerColor = Color(0xFFB3B6C4)

                ), contentPadding = PaddingValues(),
                enabled = true
            ) {
                Text(text = "Ver mas", fontSize = 13.sp, fontFamily = poppins)
            }

        }
    }
}

@Composable
fun HeaderHome(
    name:String,
    rol:Int,
    getNumEvents: () ->Int
) {
    Text(
        text =
        if(rol == 3){"¡Hola, Prof. $name!"}
        else{ "¡Hola, $name!" },
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 28.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            letterSpacing = 0.78.sp,
        )

    )
    Text(
        text = "Hay ${getNumEvents()} evento(s) en la facultad hoy ",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            letterSpacing = 0.48.sp,
        )
    )
}


