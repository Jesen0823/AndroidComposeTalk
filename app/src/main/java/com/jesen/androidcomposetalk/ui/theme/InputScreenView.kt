package com.jesen.androidcomposetalk.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesen.androidcomposetalk.viewmodel.InputViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 顶部TopBar
 * */
@Composable
fun TopBarView(
    backClick: () -> Unit,
    actionsClick: () -> Unit,
    actionsText: String,
    titleText: String,
) {
    TopAppBar(
        title = {
            Text(
                text = titleText,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = { backClick }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        actions = {
            TextButton(onClick = { actionsClick }) {
                Text(text = actionsText, color = Color.Gray, fontSize = 18.sp)
            }
        },
        // below line is use to give background color
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 12.dp
    )
}

/**
 * 登录注册输入框
 * */
@Composable
fun InputTextField(
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    type: String? = null,
    viewModel: InputViewModel,
    leadingIcon: ImageVector,
) {

    // 密码输入类型设置
    val visualTransformation =
        if (!viewModel.showPwd &&(type == "password" || type == "rePassword")) PasswordVisualTransformation() else VisualTransformation.None

    TextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = LocalTextStyle.current,
        label = { Text(text = label) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                viewModel.onFocusHide(it.isFocused && (type == "password" || type == "rePassword"))
            },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            leadingIconColor = primaryColor,
            focusedIndicatorColor = primaryColor,
        ),
        placeholder = { Text(hint) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (type == "password") {
                if (viewModel.showPwd) {
                    IconButton(onClick = { viewModel.onShowPwdChange(false) }) {
                        Icon(
                            Icons.Outlined.Visibility,
                            contentDescription = null,
                            Modifier.size(30.dp)
                        )
                    }
                } else {
                    IconButton(onClick = { viewModel.onShowPwdChange(true)  }) {
                        Icon(
                            Icons.Outlined.VisibilityOff,
                            contentDescription = null,
                            Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    )
}

/**
 * 登录注册按钮
 * */
@Composable
fun inputTogButton(
    text: String,
    scope: CoroutineScope,
    viewModel: InputViewModel,
    scaffoldState: ScaffoldState,
    isLogin: Boolean = false,
) {
    val isEnabled = if (isLogin) {
        viewModel.name.isNotBlank() && viewModel.pwd.isNotBlank()
    } else {
        viewModel.name.isNotBlank() && viewModel.pwd.isNotBlank() &&
                viewModel.rePassword.isNotBlank() && viewModel.mocId.isNotBlank() &&
                viewModel.orderId.isNotBlank()
    }
    Button(
        modifier = Modifier.fillMaxWidth().padding(10.dp,0.dp,10.dp,0.dp),
        onClick = {
            scope.launch { scaffoldState.snackbarHostState.showSnackbar("${viewModel.name}登录成功") }
        },
        enabled = isEnabled,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = primaryColor,
            backgroundColor = primaryDeepColor
        ),
        contentPadding = PaddingValues(12.dp, 16.dp)
    ) {
        Text(text, color = Color.White, fontSize = 18.sp)
    }
}

/**
 * 绘制分割线
 * */
@Composable
fun drawDiverLine() {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(2.dp), onDraw = {
        drawLine(
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height),
            color = Color.Gray,
            strokeWidth = 2f
        )
    })
}