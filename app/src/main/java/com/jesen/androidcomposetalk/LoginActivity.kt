package com.jesen.androidcomposetalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jesen.androidcomposetalk.ui.theme.AndroidComposeTalkTheme
import com.jesen.androidcomposetalk.viewmodel.InputViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    private val inputViewModel by viewModels<InputViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComposeTalkTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(scaffoldState = scaffoldState) {
                        MyScreen(inputViewModel,scaffoldState,scope)
                    }
                }
            }
        }
    }
}

@Composable
fun MyScreen(viewModel: InputViewModel, scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputTextField(
            label = "用户名",
            value = viewModel.name,
            onValueChanged = { viewModel.onNameChange(it) }
        )
        InputTextField(
            label = "请输入密码",
            value = viewModel.pwd,
            onValueChanged = {viewModel.onPwdChange(it)},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = Password)
        )
        Button(
            onClick = {scope.launch { scaffoldState.snackbarHostState.showSnackbar("${viewModel.name}登录成功") }},
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = viewModel.name.isNotBlank() && viewModel.pwd.isNotBlank()
            ) {
            Text(text = "登录")
        }
    }

}

@Composable
fun InputTextField(
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value :String,
    onValueChanged:(String) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = LocalTextStyle.current,
        label = { Text(text = label) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        shape = MaterialTheme.shapes.medium,
    )
}
