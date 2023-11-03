package com.pruebita.mydailyfisiapp.ui.screens.home

import android.net.Uri
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.data.model.User
import com.pruebita.mydailyfisiapp.ui.components.login.HeaderMenu
import com.pruebita.mydailyfisiapp.ui.navigation.AppNavigation
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.navigation.DrawerItem
import com.pruebita.mydailyfisiapp.ui.navigation.ItemMenu
import com.pruebita.mydailyfisiapp.ui.navigation.ItemMenu.*
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainStudentScreen(
    navController2: NavHostController,
    isGoogleAccount: Boolean,
    onSignOutGoogle: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()
    val currentUser: User by viewModel.currentUser.observeAsState(initial = User())
    val currentUserRol: String by viewModel.currentUserRol.observeAsState(initial = "")
    viewModel.refreshCurrentUser()

    // Init image
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.parse(currentUser.imageUser))
    }
    println(currentUser.imageUser)
    println(currentUser.names)
    // Selected image from gallery
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        selectedImageUri = it
        if (it != null) {
            viewModel.setUserImageUri(it)
        }
    }

    val scope = rememberCoroutineScope()
    val navigationBottomItems = listOf(
        HomeScreen,
        AttendanceScreen,
        ScheduleScreen,
        EventsScreen
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


    MyDialog(
        showMyDialog,
        navController2,
        photoPickerLauncher,
        selectedImageUri,
        viewModel.getUserFullName(),
        currentUserRol,
        isGoogleAccount,
        onSignOutGoogle,
        {viewModel.logOut()}
    ) { newValue ->
        showMyDialog = newValue
    }


    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(328.0.dp)
                    .background(color = Color.White),
                drawerContainerColor = Color.White,
            ) {
                HeaderMenu(
                    selectedImageUri,
                    viewModel.getUserFullName(),
                    currentUser.email,
                    currentUserRol
                )
                val currentRoute = currentRoute(navController = navController)
                navigationMenuItems.forEach() { item ->
                    Spacer(modifier = Modifier.padding(5.dp))
                    NavigationDrawerItem(
                        label = {

                            Text(
                                text = item.title,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF495ECA),
                                    textAlign = TextAlign.Start,
                                )
                            )

                        },
                        selected = currentRoute == item.routeStudent,
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                            navController.navigate(item.routeStudent)
                        },
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
                MyTopBar(drawerState, scope, showMyDialog, selectedImageUri) { newValue ->
                    showMyDialog = newValue
                }
            },
            bottomBar = { MyStudentBottomBar(navController, navigationBottomItems) },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                )
                {
                    AppNavigation(
                        navController = navController,
                        start = ItemMenu.HomeScreen.routeStudent,
                        null,
                        null,
                        null
                    )

                }
            },
        )
    }
    // [END android_compose_layout_material_modal_drawer_programmatic]
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}


@Composable
fun MyStudentBottomBar(navController: NavHostController, navigationBottomItems: List<ItemMenu>) {
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
                        selected = currentRoute == item.routeStudent,
                        onClick = { navController.navigate(item.routeStudent) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = if (currentRoute == item.routeStudent) Color(0xFF495ECA) else Color(
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    drawerState: DrawerState,
    scope: CoroutineScope,
    showMyDialog: Boolean,
    selectedImageUri: Uri?,
    onShowMyDialog: (Boolean) -> Unit
) {
    /*var showMenu by remember {
        mutableStateOf(false)
    }*/
    var showNot by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")

            }
        },
        title = { },
        actions = {
            Notificacion(showNot) { newValue ->
                showNot = newValue
            }

            Avatar(showMyDialog, selectedImageUri) { newValue ->
                onShowMyDialog(newValue)
            }

        }

    )
}

@Composable
fun MyDialog(
    showMyDialog: Boolean,
    navController: NavHostController,
    photoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    selectedImageUri: Any?,
    fullName: String,
    rol: String,
    isGoogleAccount: Boolean,
    onSignOutGoogle: () -> Unit,
    onSignOut: ()->Unit,
    onShowMyDialog: (Boolean) -> Unit,

    ) {
    if (showMyDialog) {
        Dialog(
            onDismissRequest = { onShowMyDialog(false) },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = true
            )
        ) {
            Column(
                Modifier
                    .width(249.dp)
                    .height(344.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 15.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                IconButton(
                    onClick = {
                        onShowMyDialog(false)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Hola"
                    )
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.Red, shape = CircleShape), // Tamaño de la imagen circular
                    contentAlignment = Alignment.Center,

                    ) {

                    Image(
                        painter = rememberAsyncImagePainter(model = selectedImageUri),
                        contentDescription = null, // Proporciona una descripción significativa si es necesario
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(shape = CircleShape) // Hace que la imagen sea redonda
                            .background(Color.Gray)
                            .fillMaxSize()
                    )


                    // Círculo más pequeño en la parte inferior derecha
                    Box(
                        modifier = Modifier
                            .size(40.dp) // Tamaño del círculo pequeño
                            .align(Alignment.BottomEnd) // Alineación en la parte inferior derecha
                            .background(
                                Color.Red,
                                shape = CircleShape
                            ) // Color y forma del círculo pequeño
                    ) {
                        PerfilButton(photoPickerLauncher)
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = fullName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = rol,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
                Spacer(modifier = Modifier.padding(10.dp))
                LogOutButton(navController, isGoogleAccount,onSignOutGoogle, onSignOut)
            }

        }

    }


}

@Composable
fun LogOutButton(
    navController: NavHostController,
    isGoogleAccount: Boolean,
    onSignOutGoogle: () -> Unit,
    onSignOut: () -> Unit
) {

    ElevatedButton(
        onClick = {
            if(isGoogleAccount){
                onSignOutGoogle()
            }
            onSignOut()
            navController.navigate(route = AppScreens.LoginScreen.route)
        },
        modifier = Modifier
            .width(180.dp)
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
            Row(

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "LogOut"
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(text = "Cerrar Sesión", fontSize = 16.sp, fontFamily = poppins)
            }

        }
    }
}

@Composable
fun PerfilButton(photoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) {
    ElevatedButton(
        onClick = {
            photoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )


        },
        modifier = Modifier
            .size(40.dp),
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
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center,
        ) {
            Row(

            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "LogOut")
            }

        }
    }
}

@Composable
fun Notificacion(showNot: Boolean, onShowNotChanged: (Boolean) -> Unit) {

    IconButton(onClick = {
        onShowNotChanged(!showNot)// showNot =! showNot
    }) {
        if (showNot)
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_notifications_off_24
                ),
                contentDescription = "Menu",
                tint = Color(0xFF8B97A8)
            )
        else
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_notifications_active_24
                ),
                contentDescription = "Menu",
                tint = Color(0xFF2F68D7)

            )
    }

}

@Composable
fun Avatar(showMyDialog: Boolean, selectedImageUri: Uri?, onShowMyDialog: (Boolean) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp) // Espacio alrededor de la imagen dentro del contenedor
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = selectedImageUri),
            contentDescription = null, // Proporciona una descripción significativa si es necesario
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .clip(shape = CircleShape) // Hace que la imagen sea redonda
                .clickable {
                    onShowMyDialog(true)
                }
                .background(Color.Gray)
                .fillMaxSize()
                .align(Alignment.Center)
        )
    }
}


@Composable
fun Content() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

    }
}