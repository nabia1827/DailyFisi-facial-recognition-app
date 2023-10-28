package com.pruebita.mydailyfisiapp.ui.screens.login

import android.content.Context
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.di.AppModule.provideContext
import com.pruebita.mydailyfisiapp.ui.components.login.ForgotPasswordDialog
import com.pruebita.mydailyfisiapp.ui.components.login.HeaderLogin
import com.pruebita.mydailyfisiapp.ui.components.login.SendCodeDialog
import com.pruebita.mydailyfisiapp.ui.components.login.WriteCodeDialog
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.ForgotPasswordViewModel
import com.pruebita.mydailyfisiapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}


@Composable
fun LoginScreen(navController: NavHostController) {

    val loginViewModel: LoginViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Login(Modifier.padding(0.dp), loginViewModel, navController)

    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, navController: NavHostController) {
    var snackbarVisible = remember { mutableStateOf(false) }
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val isUserCorrect: Boolean by viewModel.isValidationUserCorrect.observeAsState(initial = true)
    val isPassCorrect: Boolean by viewModel.isValidationPassCorrect.observeAsState(initial = true)
    val txtUserCorrect: String by viewModel.txtValidationUserCorrect.observeAsState(initial = "")
    val txtPassCorrect: String by viewModel.txtValidationPassCorrect.observeAsState(initial = "")
    val isFirstLogin: Boolean by viewModel.isFirstLogin.observeAsState(initial = true)
    val coroutineScope = rememberCoroutineScope()

    //Mutable
    val viewModel2 = ForgotPasswordViewModel()

    //States
    var showSendCodeDialog by remember {
        mutableStateOf(false)
    }
    var showWriteCodeDialog by remember {
        mutableStateOf(false)
    }
    var showForgotPasswordDialog by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Bottom
        ) {

            Column(modifier = Modifier.weight(0.3f)) {
                HeaderLogin()

            }

            Column(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .weight(0.55f)
            ) {
                Spacer(modifier = Modifier.padding(20.dp))
                Column() {
                    EmailField(
                        email,
                        isUserCorrect,
                        txtUserCorrect
                    ) { viewModel.onLoginChanged(it, password) }
                    Spacer(modifier = Modifier.padding(13.dp))
                    PasswordField(
                        password, isPassCorrect,
                        txtPassCorrect
                    ) { viewModel.onLoginChanged(email, it) }
                    Spacer(modifier = Modifier.padding(10.dp))
                    ForgotPassword(Modifier.align(Alignment.End)) {
                        showSendCodeDialog = true // Mostrar el primer diálogo
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column {
                    Continue()
                    Spacer(modifier = Modifier.padding(3.dp))
                    ContinueWithGoogle()
                }

            }
            Column(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .weight(0.15f),
                verticalArrangement = Arrangement.Center
            )
            {
                LoginButton(
                    true,
                    isFirstLogin,
                    navController,
                    { viewModel.onLoginSelected() },
                    { viewModel.saveLocallyUserData() }) {
                    viewModel.getMainRoute()
                }

            }

            //Dialog 1
            SendCodeDialog(
                showSendCodeDialog,
                navController,
                viewModel2
            )
            { sendCodeValue, writeCodeValue ->
                showSendCodeDialog = sendCodeValue
                showWriteCodeDialog = writeCodeValue
            }
            //Dialog 2
            WriteCodeDialog(
                showWriteCodeDialog,
                navController,
                viewModel2
            ) { writeCodeValue, forgotPasswordValue ->
                showWriteCodeDialog = writeCodeValue
                showForgotPasswordDialog = forgotPasswordValue
            }
            //Dialog 3
            ForgotPasswordDialog(
                showForgotPasswordDialog,
                navController,
                viewModel2,
                snackbarVisible,
                coroutineScope,
            ) { forgotPasswordValue ->
                showForgotPasswordDialog = forgotPasswordValue
            }


        }
        if (snackbarVisible.value) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                containerColor = Color.Black,
                contentColor = Color.White,
                content = { Text("Cambio realizado con exito") }
            )
        }
    }
}

@Composable
fun LoginButton(
    loginEnable: Boolean,
    isFirstLogin: Boolean,
    navController: NavHostController,
    onLoginSelected: () -> Boolean,
    saveUser: () -> Unit,
    getMainRoute: () -> String
) {
    ElevatedButton(
        onClick = {
            if (onLoginSelected()) {
                saveUser()
                if (isFirstLogin) {
                    navController.navigate(route = AppScreens.FaceRecognizerScreen.route + "/false")
                } else {
                    val route = getMainRoute()
                    navController.navigate(route)
                }

            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0xFFB3B6C4)

        ), contentPadding = PaddingValues(),
        enabled = loginEnable
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
            Text(text = "Iniciar Sesion", fontSize = 16.sp, fontFamily = poppins)
        }
    }
}

@Composable
fun ForgotPassword(
    modifier: Modifier,
    activate: () -> Unit
) {

    var rememberMe by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(22.dp)
    ) {
        Column(
            modifier = Modifier.weight(0.4f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = {
                        rememberMe = !rememberMe
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF4678CA)
                    )
                )

                Text(
                    text = "Recordar",
                    modifier = modifier.clickable { },
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF4678CA),
                    fontFamily = poppins,

                    )
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Column(
            modifier = Modifier.weight(0.6f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Olvidaste tu contraseña?",
                modifier = modifier.clickable { activate() },
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4678CA),
                fontFamily = poppins,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    password: String,
    isPassCorrect: Boolean,
    passError: String,
    onTextFieldChanged: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val visualTransformation = if (!passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    var isPressed by rememberSaveable { mutableStateOf(false) }

    Text(
        text = "Contraseña",
        color = Color(0xFF7F8388),
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = poppins
    )

    OutlinedTextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        placeholder = {
            Text(text = "Mi contrseña", color = Color(0xFFA9ACAF), fontFamily = poppins)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    email: String,
    isUserCorrect: Boolean,
    textError: String,
    onTextFieldChanged: (String) -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Text(
        text = "Usuario",
        color = Color(0xFF7F8388),
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = poppins
    )
    OutlinedTextField(

        value = email,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isPressed = !isPressed },
        placeholder = {
            Text(
                text = "alguien@unmsm.edu.pe",
                color = Color(0xFFA9ACAF),
                fontFamily = poppins
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User",
                tint = if (isUserCorrect) if (!isPressed) Color(0xFF495ECA) else Color(0xFF7F8388) else Color(
                    0xFFF44336
                )
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            errorCursorColor = Color(0xFFF44336),
            focusedBorderColor = Color(0xFF495ECA),
            errorBorderColor = Color(0xFFF44336),
            focusedLeadingIconColor = Color(0xFF495ECA),
            errorLeadingIconColor = Color(0xFFF44336),
            focusedTrailingIconColor = Color(0xFF495ECA),
            errorTrailingIconColor = Color(0xFFF44336),
            focusedPlaceholderColor = Color(0xFF495ECA),
            errorPlaceholderColor = Color(0xFFF44336),
            focusedSupportingTextColor = Color(0xFF495ECA),
            errorSupportingTextColor = Color(0xFFF44336),
        ),
        supportingText = { Text(text = textError, fontSize = 13.sp, fontFamily = poppins) },
        isError = !isUserCorrect
    )
}

@Composable
fun Continue() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Primera línea vertical
        Divider(
            color = Color(0xFFCDCED1),
            modifier = Modifier
                .weight(1f) // Espacio igual a la mitad del Row
                .height(1.dp) // Grosor de la línea
        )

        // Carácter "a"
        Text(
            text = "  o continua con:  ",
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp),
            color = Color(0xFF747980),
            fontFamily = poppins,
        )

        // Segunda línea vertical
        Divider(
            color = Color(0xFFCDCED1),
            modifier = Modifier
                .weight(1f) // Espacio igual a la mitad del Row
                .height(1.dp) // Grosor de la línea
        )
    }
}

@Composable
fun ContinueWithGoogle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,

        ) {
        OutlinedIconButton(
            onClick = { },
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color(0xFF969AA0)),
            colors = IconButtonDefaults.outlinedIconButtonColors(
                containerColor = Color.White,
            ),
            modifier = Modifier.size(80.dp)
        ) {
            Box(modifier = Modifier.padding(18.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.iconinicio),
                    contentDescription = "Log In Google",
                    modifier = Modifier.size(82.dp, 82.dp)
                )
            }
        }
    }
}