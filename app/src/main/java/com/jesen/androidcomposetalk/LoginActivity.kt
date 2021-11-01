package com.jesen.androidcomposetalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jesen.androidcomposetalk.ui.theme.AndroidComposeTalkTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComposeTalkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val (name, setName) = remember { mutableStateOf("") }
        val (pwd, setPwd) = remember { mutableStateOf("") }

        InputTextField(
            label = "用户名",
            value = name,
            onValueChanged = setName
        )
        InputTextField(
            label = "请输入密码",
            value = pwd,
            onValueChanged = setPwd,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = Password)
        )
        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = name.isNotBlank() && pwd.isNotBlank(),
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
