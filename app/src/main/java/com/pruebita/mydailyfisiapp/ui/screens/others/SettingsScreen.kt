package com.pruebita.mydailyfisiapp.ui.screens.others

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.screens.login.PasswordField
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.LoginViewModel
import com.pruebita.mydailyfisiapp.viewmodel.SettingsViewModel


@Preview(showBackground = true)
@Composable
fun SettingsScreen() {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/imageneseventos/101.jpg"))
    }
    var notificationActive by remember { mutableStateOf(true) }
    var changePasswordActive by remember { mutableStateOf(false) }
    var curseActive by remember { mutableStateOf(true) }
    var eventsActive by remember { mutableStateOf(true) }
    var remainderActive by remember { mutableStateOf(true) }

    val viewModel: SettingsViewModel = SettingsViewModel()
    val password: String by viewModel.password.observeAsState(initial = "123456")
    val isPassCorrect: Boolean by viewModel.isValidationPassCorrect.observeAsState(initial = true)
    val txtPassCorrect: String by viewModel.txtValidationPassCorrect.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFF)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderSettingsScreen(selectedImageUri)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Notificaciones:",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),

                        )
                )

                Switch(
                    checked = notificationActive,
                    onCheckedChange = {
                        notificationActive = it
                        if (notificationActive) {
                            curseActive = true
                            eventsActive = true
                            remainderActive = true
                        } else {
                            curseActive = false
                            eventsActive = false
                            remainderActive = false
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color(0xFF495ECA)
                    )
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Notificaciones de cursos",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),

                        )
                )

                RadioButton(
                    selected = curseActive,
                    onClick = {
                        curseActive = !curseActive

                        notificationActive = curseActive && eventsActive && remainderActive

                    },
                    modifier = Modifier.semantics {
                        var contentDescription = "Localized Description"
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF699CE9)

                    )
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Notificaciones de eventos",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),

                        )
                )

                RadioButton(
                    selected = eventsActive,
                    onClick = {
                        eventsActive = !eventsActive
                        notificationActive = curseActive && eventsActive && remainderActive

                    },
                    modifier = Modifier.semantics {
                        var contentDescription = "Localized Description"
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF699CE9)

                    )
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Notificaciones de recordatorios",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )

                RadioButton(
                    selected = remainderActive,
                    onClick = {
                        remainderActive = !remainderActive
                        notificationActive = curseActive && eventsActive && remainderActive

                    },
                    modifier = Modifier.semantics {
                        var contentDescription = "Localized Description"
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF699CE9)

                    )
                )

            }
            Spacer(modifier = Modifier.padding(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cambiar Contraseña:",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),

                        )
                )
                Switch(
                    checked = changePasswordActive,
                    onCheckedChange = {
                        changePasswordActive = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color(0xFF495ECA)
                    )
                )
            }
            Spacer(modifier = Modifier.padding(7.dp))
            ChangePasswordField(
                password, isPassCorrect,
                txtPassCorrect, { changePasswordActive }
            ) { viewModel.onPasswordChanged(it) }

        }
    }


}

@Composable
fun HeaderSettingsScreen(selectedImageUri: Uri?) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.polygon_small_secundary_pastel),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds

            )
        }

        Box {
            Image(
                painter = painterResource(id = R.drawable.polygon_small_primary_gradient),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )

        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier.size(130.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.LightGray)
                                .size(110.dp)
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(selectedImageUri)
                                    .build(),
                                contentDescription = "This is an example image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop


                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center

                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFC05AFF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "User",
                                tint = Color.White
                            )
                        }

                    }

                }
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Lucia Alejandra",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = "Rivera Benites",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                    )
                )

            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordField(
    password: String,
    isPassCorrect: Boolean,
    passError: String,
    getEnableState: () -> Boolean,
    onTextFieldChanged: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val visualTransformation = if (!passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    var isPressed by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        enabled = getEnableState(),
        placeholder = {
            Text(text = "Mi contraseña", color = Color(0xFFA9ACAF), fontFamily = poppins)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "User",
                tint = if (isPassCorrect) if (!isPressed) Color(0xFF495ECA) else Color(0xFF7F8388) else Color(
                    0xFFF44336
                )
            )
        },
        trailingIcon = {
            if (passwordVisible) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_visibility_24),
                    contentDescription = "User",
                    modifier = Modifier.clickable { passwordVisible = false },
                    tint = if (isPassCorrect) if (!isPressed) Color(0xFF495ECA) else Color(
                        0xFF7F8388
                    ) else Color(0xFFF44336)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_visibility_off_24),
                    contentDescription = "User",
                    modifier = Modifier.clickable { passwordVisible = true },
                    tint = if (isPassCorrect) if (!isPressed) Color(0xFF495ECA) else Color(
                        0xFF7F8388
                    ) else Color(0xFFF44336)
                )
            }
        },
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isPressed = !isPressed },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            errorBorderColor = Color(0xFFF44336),
            errorCursorColor = Color(0xFFF44336),
            errorPlaceholderColor = Color(0xFFF44336),
            errorLeadingIconColor = Color(0xFFF44336),
            errorSupportingTextColor = Color(0xFFF44336),
            errorTrailingIconColor = Color(0xFFF44336),
            focusedBorderColor = Color(0xFF495ECA),
            focusedPlaceholderColor = Color(0xFF495ECA),
            focusedLeadingIconColor = Color(0xFF495ECA),
            focusedSupportingTextColor = Color(0xFF495ECA),
            focusedTrailingIconColor = Color(0xFF495ECA),
        ),
        isError = !isPassCorrect,
        supportingText = { Text(text = passError, fontSize = 13.sp, fontFamily = poppins) }

    )

}
