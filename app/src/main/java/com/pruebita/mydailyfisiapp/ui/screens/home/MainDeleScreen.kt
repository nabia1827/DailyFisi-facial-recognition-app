package com.pruebita.mydailyfisiapp.ui.screens.home

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.ui.components.login.HeaderMenu
import com.pruebita.mydailyfisiapp.ui.navigation.AppNavigation
import com.pruebita.mydailyfisiapp.ui.navigation.DrawerItem
import com.pruebita.mydailyfisiapp.ui.navigation.ItemMenu
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainDeleScreen(navController2: NavHostController){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()

    // Init image
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.parse("https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg"))
    }
    // Selected image from gallery
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        selectedImageUri = it
    }

    val scope = rememberCoroutineScope()
    val navigationBottomItems = listOf(
        ItemMenu.HomeScreen,
        ItemMenu.AttendanceScreen,
        ItemMenu.ScheduleScreen,
        ItemMenu.EventsScreen
    )

    val navigationMenuItems = listOf(
        DrawerItem.AttendanceScreen,
        DrawerItem.ScheduleScreen,
        DrawerItem.EventsScreen,
        DrawerItem.SettingsScreen,
        DrawerItem.HelpScreen
    )
    var showMyDialog by remember {
        mutableStateOf(false)
    }


    MyDialog(showMyDialog,navController2, photoPickerLauncher,selectedImageUri) { newValue ->
        showMyDialog = newValue
    }


    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(328.0.dp)
                    .background(color = Color.White),
                drawerContainerColor = Color.White,
            ) {
                HeaderMenu(selectedImageUri)
                val currentRoute = currentRoute(navController = navController)
                navigationMenuItems.forEach() { item ->
                    Spacer(modifier = Modifier.padding(5.dp))
                    NavigationDrawerItem(
                        label = {

                            Text(text = item.title,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF495ECA),
                                    textAlign = TextAlign.Start,
                                )
                            )

                        },
                        selected = currentRoute == item.routeDele,
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                            navController.navigate(item.routeDele) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,

                                )
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFFB1C6F0),
                            unselectedContainerColor = Color(0xFFFFFFFF),
                        ),
                    )
                }

            }
        },
    ) {
        Scaffold(
            topBar = {
                MyTopBar(drawerState, scope,showMyDialog,selectedImageUri){ newValue ->
                    showMyDialog = newValue
                }
            },
            bottomBar = { MyDeleBottomBar(navController, navigationBottomItems) },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                )
                {
                    AppNavigation(navController = navController, start = ItemMenu.HomeScreen.routeDele)

                }
            },
        )
    }
    // [END android_compose_layout_material_modal_drawer_programmatic]
}

@Composable
fun MyDeleBottomBar(navController: NavHostController, navigationBottomItems: List<ItemMenu>) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        containerColor = Color(0xFFF4F4F4),
        content = {
            NavigationBar {
                val currentRoute = currentRoute(navController = navController)
                navigationBottomItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.routeDele,
                        onClick = { navController.navigate(item.routeDele) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = if (currentRoute == item.routeDele) Color(0xFF495ECA) else Color(
                                    0xFF8B97A8
                                )
                            )
                        }
                    )
                }
            }
        }

    )
}