package com.pruebita.mydailyfisiapp.ui.components.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.ForgotPasswordViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun PreviewAll() {

}

//@Preview(showBackground = true)
@Composable
fun PreviewOne() {
    val navController = rememberNavController()
    var showMyDialog by remember {
        mutableStateOf(true)
    }

}

@Composable
fun ForgotPasswordDialog(
    showForgotPasswordDialog: Boolean,
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel,
    showSnackbar: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    onShowForgotPasswordDialog: (Boolean) -> Unit,
) {


    if (showForgotPasswordDialog) {
        ContentDialogForgotPassword(
            viewModel,
            showSnackbar,
            coroutineScope,
            onShowForgotPasswordDialog
        )
    }
}

@Composable
fun ContentDialogForgotPassword(
    viewModel: ForgotPasswordViewModel,
    showSnackbar: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    onShowForgotPasswordDialog: (Boolean) -> Unit
) {
    //Password
    val newPassword: String by viewModel.newPassword.observeAsState(initial = "")
    val isNewPasswordCorrect: Boolean by viewModel.isValidationPassCorrect.observeAsState(initial = true)
    val txtNewPasswordCorrect: String by viewModel.txtValidationPassCorrect.observeAsState(initial = "")


    //Confirm Password
    val newPasswordC: String by viewModel.newPasswordC.observeAsState(initial = "")
    val isNewPasswordCCorrect: Boolean by viewModel.isValidationPassCCorrect.observeAsState(initial = true)
    val txtNewPasswordCCorrect: String by viewModel.txtValidationPassCCorrect.observeAsState(initial = "")
    Dialog(
        onDismissRequest = { onShowForgotPasswordDialog(false) },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            Modifier
                .width(344.dp)
                //.height(344.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .padding(30.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            DescriptionChangePassword()
            Spacer(modifier = Modifier.padding(6.dp))
            NewPasswordField(
                newPassword,
                "Contraseña",
                isNewPasswordCorrect,
                txtNewPasswordCorrect
            ) {
                viewModel.onPassFieldChanged(it)
            }
            Spacer(modifier = Modifier.padding(6.dp))
            NewPasswordField(
                newPasswordC,
                "ConfirmarContraseña",
                isNewPasswordCCorrect,
                txtNewPasswordCCorrect
            ) { viewModel.onPassCFieldChanged(it) }
            Spacer(modifier = Modifier.padding(6.dp))
            ButtonChangePassword(onShowForgotPasswordDialog, showSnackbar, coroutineScope){
                viewModel.onChangingButtonSelected()
            }
            Spacer(modifier = Modifier.padding(6.dp))

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordField(
    password: String,
    title: String,
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
        text = "$title",
        color = Color(0xFF7F8388),
        fontSize = 15.sp,
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
        supportingText = { Text(text = passError, fontSize = 11.sp, fontFamily = poppins) }

    )

}


@Composable
fun DescriptionChangePassword() {
    Text(
        text = "Nueva Contraseña",
        style = TextStyle(
            fontSize = 22.sp,
            lineHeight = 22.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF495ECA),
        )
    )
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = "Ingresa una nueva contraseña de mínimo 8 caracteres.",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            letterSpacing = 0.48.sp,
        )
    )
}

@Composable
fun ButtonChangePassword(
    onShowForgotPasswordDialog: (Boolean) -> Unit,
    showSnackbar: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    onChangingButtonSelected:() -> Boolean
) {

    ElevatedButton(
        onClick = {
            if(onChangingButtonSelected()){

                onShowForgotPasswordDialog(false)
                showSnackbar.value = true
                coroutineScope.launch {
                    delayingSnackBar()

                    showSnackbar.value = false
                }

            }

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp),
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
            Text(text = "Cambiar contraseña", fontSize = 13.sp, fontFamily = poppins)
        }
    }
}


@Composable
fun WriteCodeDialog(
    showWriteCodeDialog: Boolean,
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel,
    onShowWriteCodeDialog: (Boolean, Boolean) -> Unit,
) {

    if (showWriteCodeDialog) {
        ContentDialogWriteCode(viewModel, onShowWriteCodeDialog)
    }

}

@Composable
fun ContentDialogWriteCode(
    viewModel: ForgotPasswordViewModel,
    onShowWriteCodeDialog: (Boolean, Boolean) -> Unit
) {

    Dialog(
        onDismissRequest = { onShowWriteCodeDialog(false, false) },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            Modifier
                .width(344.dp)
                //.height(344.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .padding(30.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            DescriptionWriteCode()
            Spacer(modifier = Modifier.padding(3.dp))
            FourDigitCodeField(viewModel)
            Spacer(modifier = Modifier.padding(6.dp))
            ButtonWriteCode(onShowWriteCodeDialog){
                viewModel.onVerifyingButtonSelected()
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Reenviar Codigo",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF495ECA),
                    letterSpacing = 0.48.sp,
                )
            )
        }

    }
}

@Composable
fun ButtonWriteCode(
    onShowWriteCodeDialog: (Boolean, Boolean) -> Unit,
    onVerifyingButtonSelected: () ->Boolean
    ) {
    ElevatedButton(
        onClick = {
            if(onVerifyingButtonSelected())
                onShowWriteCodeDialog(false, true)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp),
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
            Text(text = "Verificar", fontSize = 13.sp, fontFamily = poppins)
        }
    }
}

@Composable
fun DescriptionWriteCode() {
    Text(
        text = "Revisa tu Gmail",
        style = TextStyle(
            fontSize = 22.sp,
            lineHeight = 22.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF495ECA),
        )
    )
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = "Hemos enviado un código a miguel.perez@unmsm.edu.pe para poder restablecer tu contraseña. Ingresalo a continuciación",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            letterSpacing = 0.48.sp,
        )
    )

}

@Composable
fun SendCodeDialog(
    showSendCodeDialog: Boolean,
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel,
    onShowSendCodeDialog: (Boolean, Boolean) -> Unit,
) {
    if (showSendCodeDialog) {
        ContentDialogSendCode(viewModel, onShowSendCodeDialog)
    }

}


@Composable
fun ContentDialogSendCode(
    viewModel: ForgotPasswordViewModel,
    onShowSendCodeDialog: (Boolean, Boolean) -> Unit
) {
    val emailToSend: String by viewModel.emailToSend.observeAsState(initial = "")
    val isEmailCorrect: Boolean by viewModel.isValidationEmailCorrect.observeAsState(initial = true)
    val txtEmailCorrect: String by viewModel.txtValidationEmailCorrect.observeAsState(initial = "")

    Dialog(
        onDismissRequest = { onShowSendCodeDialog(false, false) },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            Modifier
                .width(344.dp)
                //.height(344.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .padding(30.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            DescriptionSendCode()
            Spacer(modifier = Modifier.padding(3.dp))
            TextFieldSendCode(
                emailToSend,
                isEmailCorrect,
                txtEmailCorrect
            ) { viewModel.onEmailFieldChanged(it) }
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "** En caso no recuerde su correo institucional, comunicarse con Red Telematica UNMSM.",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    letterSpacing = 0.48.sp,
                )
            )
            Spacer(modifier = Modifier.padding(6.dp))
            ButtonSendCode(onShowSendCodeDialog){
                viewModel.onSendingButtonSelected()
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "soporte.correo@unmsm.edu.pe",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF495ECA),
                    letterSpacing = 0.48.sp,
                )
            )
        }

    }
}

@Composable
fun DescriptionSendCode() {
    Text(
        text = "Enviar Codigo",
        style = TextStyle(
            fontSize = 22.sp,
            lineHeight = 22.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF495ECA),
        )
    )
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = "Escribe tu correo institucional para enviar el codigo de restablecimiento.",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            letterSpacing = 0.48.sp,
        )
    )
    Spacer(modifier = Modifier.padding(6.dp))
    Text(
        text = "Correo Institucional",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 13.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            letterSpacing = 0.48.sp,
        )
    )
}

@Composable
fun ButtonSendCode(
    onShowSendCodeDialog: (Boolean, Boolean) -> Unit,
    onSendingButtonSelected: () -> Boolean
) {
    ElevatedButton(
        onClick = {
            if(onSendingButtonSelected())
                onShowSendCodeDialog(false, true)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp),
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
            Text(text = "Enviar codigo", fontSize = 13.sp, fontFamily = poppins)
        }
    }
}

@Composable
fun TextFieldSendCode(
    email: String,
    isUserCorrect: Boolean,
    textError: String,
    onTextFieldChanged: (String) -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    OutlinedTextField(

        value = email,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .onFocusChanged { isPressed = !isPressed },
        textStyle = TextStyle(
            fontSize = 12.sp,
            fontFamily = poppins
        ),
        placeholder = {
            Text(
                text = "alguien@unmsm.edu.pe",
                color = Color(0xFFA9ACAF),
                fontSize = 12.sp,
                fontFamily = poppins
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
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
suspend fun delayingSnackBar(){
    delay(3000)

}