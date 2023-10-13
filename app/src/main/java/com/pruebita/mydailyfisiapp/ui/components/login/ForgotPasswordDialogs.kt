package com.pruebita.mydailyfisiapp.ui.components.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.ForgotPasswordViewModel
@Preview
@Composable
fun PreviewFourDigit(){
    FourDigitCodeField(ForgotPasswordViewModel())
}


@Composable
fun FourDigitCodeField(
    viewModel: ForgotPasswordViewModel
) {
    val code: String by viewModel.code.observeAsState(initial = "")
    val isCodeCorrect: Boolean by viewModel.isVerifiedCode.observeAsState(initial = true)
    val prueba: Int =27
    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }

    val digit1:String by viewModel.digit1.observeAsState(initial="")
    val digit2:String by viewModel.digit2.observeAsState(initial="")
    val digit3:String by viewModel.digit3.observeAsState(initial="")
    val digit4:String by viewModel.digit4.observeAsState(initial="")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = digit1,
            onValueChange = {
                if (it.length <= 1) {
                    viewModel.onFourCodeChanged(it,digit2,digit3,digit4)
                    if (it.isNotEmpty() ) {

                        focusRequester2.requestFocus()
                    }
                }
                else{
                    viewModel.onFourCodeChanged(it.last()+"",digit2,digit3,digit4)
                    if (it.isNotEmpty()) {

                        focusRequester2.requestFocus()
                    }
                }
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester1)
                .padding(4.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = poppins,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F6F7),
                unfocusedBorderColor = Color(0xFF8B97A8),
                focusedBorderColor = Color(0xFF495ECA),
                focusedContainerColor = Color(0xFFC8DBF8),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color(0xFF495ECA),
                errorBorderColor = Color(0xFFF44336),
                errorCursorColor = Color(0xFFF44336),
                errorTextColor = Color(0xFFF44336),
                errorContainerColor = Color(0xFFF44336)
            ),
            isError = !isCodeCorrect

        )

        OutlinedTextField(
            value = digit2,
            onValueChange = {
                if (it.length <= 1) {
                    viewModel.onFourCodeChanged(digit1,it,digit3,digit4)
                    if (it.isNotEmpty()) {
                        focusRequester3.requestFocus()
                    }
                }else{
                    viewModel.onFourCodeChanged(digit1,it.last()+"",digit3,digit4)
                    if (it.isNotEmpty()) {

                        focusRequester3.requestFocus()
                    }
                }
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester2)
                .padding(4.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F6F7),
                unfocusedBorderColor = Color(0xFF8B97A8),
                focusedBorderColor = Color(0xFF495ECA),
                focusedContainerColor = Color(0xFFC8DBF8),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color(0xFF495ECA),
                errorBorderColor = Color(0xFFF44336),
                errorCursorColor = Color(0xFFF44336),
                errorTextColor = Color(0xFFF44336),
                errorContainerColor = Color(0xFFF44336)
            ),
            isError = !isCodeCorrect
        )

        OutlinedTextField(
            value = digit3,
            onValueChange = {
                if (it.length <= 1) {
                    viewModel.onFourCodeChanged(digit1,digit2,it,digit4)
                    if (it.isNotEmpty()) {
                        focusRequester4.requestFocus()
                    }
                }
                else{
                    viewModel.onFourCodeChanged(digit1,digit2,it.last()+"",digit4)
                    if (it.isNotEmpty()) {
                        focusRequester4.requestFocus()
                    }
                }
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester3)
                .padding(4.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = poppins,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F6F7),
                unfocusedBorderColor = Color(0xFF8B97A8),
                focusedBorderColor = Color(0xFF495ECA),
                focusedContainerColor = Color(0xFFC8DBF8),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color(0xFF495ECA),
                errorBorderColor = Color(0xFFF44336),
                errorCursorColor = Color(0xFFF44336),
                errorTextColor = Color(0xFFF44336),
                errorContainerColor = Color(0xFFF44336)
            ),
            isError = !isCodeCorrect
        )

        OutlinedTextField(
            value = digit4,
            onValueChange = {
                if (it.length <= 1) {
                    viewModel.onFourCodeChanged(digit1,digit2,digit3,it)
                }
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester4)
                .padding(4.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = poppins,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F6F7),
                unfocusedBorderColor = Color(0xFF8B97A8),
                focusedBorderColor = Color(0xFF495ECA),
                focusedContainerColor = Color(0xFFC8DBF8),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color(0xFF495ECA),
                errorBorderColor = Color(0xFFF44336),
                errorCursorColor = Color(0xFFF44336),
                errorTextColor = Color(0xFFF44336),
                errorContainerColor = Color(0xFFF44336)
            ),
            isError = !isCodeCorrect
        )
        if(!isCodeCorrect)
            Text(text = "**Codigo Incorrecto", color = Color(0xFFF44336),fontSize = 13.sp, fontFamily = poppins)
        else
            Text(text = " ", color = Color(0xFFF44336),fontSize = 13.sp, fontFamily = poppins)
    }
}
@Preview(showBackground = true)
@Composable
fun FourDigitCodeScreen() {
    //FourDigitCodeField({})
}