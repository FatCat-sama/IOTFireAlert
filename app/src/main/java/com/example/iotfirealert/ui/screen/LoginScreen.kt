package com.example.iotfirealert.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iotfirealert.R
import com.example.iotfirealert.common.clickable
import com.example.iotfirealert.common.selectable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier,
    onLogin: (email: String, password: String, isRememberLogin: Boolean) -> Unit = { _, _, _ -> }
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isRememberLogin by remember { mutableStateOf(false) }
    var isShowPassword by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFE30012))
    ) {
        //decor
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.TopCenter),
            imageVector = ImageVector.vectorResource(id = R.drawable.login_decor),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )

        //Login form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
                .align(alignment = Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Fire Alert",
                modifier = Modifier,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )

            Text(
                text = "Login",
                modifier = Modifier,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            )

            Spacer(modifier = Modifier.size(height = 16.dp, width = 0.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                placeholder = {
                    Text(text = "Email")
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.size(height = 16.dp, width = 0.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                placeholder = {
                    Text(text = "Password")
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .clickable(
                                role = Role.RadioButton,
                                indication = rememberRipple(bounded = true, radius = 24.dp),
                            ) {
                                isShowPassword = !isShowPassword
                            },
                        imageVector = ImageVector.vectorResource(
                            id = if (isShowPassword)
                                R.drawable.ic_eye_open
                            else
                                R.drawable.ic_eye_close
                        ),
                        contentDescription = null,
                    )
                },
                keyboardActions = KeyboardActions {
                    onLogin(email, password, isRememberLogin)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Go
                ),
                visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
            )

            Spacer(modifier = Modifier.size(height = 12.dp, width = 0.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(
                        id = if (isRememberLogin)
                            R.drawable.ic_circle_checked
                        else
                            R.drawable.ic_circle
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .selectable(
                            selected = isRememberLogin,
                            role = Role.Checkbox,
                            indication = rememberRipple(bounded = true, radius = 24.dp),
                        ) {
                            isRememberLogin = !isRememberLogin
                        }
                )

                Spacer(modifier = Modifier.size(height = 0.dp, width = 8.dp))

                Text(
                    text = "Remember me",
                    modifier = Modifier,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                )

                Spacer(modifier = Modifier.size(height = 0.dp, width = 8.dp))

                Text(
                    text = "Forgot password?",
                    modifier = Modifier.weight(1f),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        textAlign = TextAlign.End
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true", name = "LoginPreview",
    apiLevel = 33
)
@Composable
private fun LoginPreview() {
    LoginScreen(modifier = Modifier)
}