package com.pruebita.mydailyfisiapp.ui.screens.attendance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.components.attendance.AttendanceLogin
import com.pruebita.mydailyfisiapp.ui.components.attendance.BottomFond
import com.pruebita.mydailyfisiapp.ui.components.attendance.BottomFond2

@Preview(showBackground = true)
@Composable
fun AttendanceScreenPreview(){
    val navAttendance = rememberNavController()
    AttendanceScreen(navAttendance)
}
@Composable
fun AttendanceScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Attendance(Modifier.padding(0.dp),navController)
    }
}

@Composable
fun Attendance(modifier: Modifier,navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Bottom
        ){
            Column(modifier = Modifier.weight(0.3f)) {
                AttendanceLogin()
            }
            Column(
                modifier = Modifier.weight(0.1f)

                ) {
                BottomFond()
            }
            Column(
                modifier = Modifier.weight(0.1f)

            ) {
                BottomFond2()
            }
        }

    }
}
