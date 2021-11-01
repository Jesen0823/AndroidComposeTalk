package com.jesen.androidcomposetalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesen.androidcomposetalk.ui.theme.AndroidComposeTalkTheme
import com.jesen.androidcomposetalk.ui.theme.primaryColor
import com.jesen.androidcomposetalk.ui.theme.primaryDeepColor
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
                    Scaffold(
                        topBar = { TopBarView() },
                        scaffoldState = scaffoldState
                    ) {
                        Box(Modifier.fillMaxSize()){
                            val isHide = remember{ mutableStateOf(false)}

                            Row(modifier = Modifier
                                .fillMaxSize(),horizontalArrangement = Arrangement.SpaceBetween){
                                Image(modifier = Modifier.size(120.dp,120.dp),painter = painterResource(id = if(inputViewModel.isHide) R.drawable.head_left_protect else R.drawable.head_left) ,contentDescription = "left image")
                                Image(alignment = Alignment.BottomCenter,painter = painterResource(id = R.drawable.logo), contentDescription = "mid image")
                                Image(modifier = Modifier.size(120.dp,120.dp),painter = painterResource(id =  if(inputViewModel.isHide) R.drawable.head_right_protect else R.drawable.head_right), contentDescription = "right image")
                            }
                            Column(
                                modifier = Modifier
                                    .padding(5.dp, 120.dp, 5.dp, 0.dp)
                                    .fillMaxWidth()
                            ) {
                                Spacer(modifier = Modifier.height(2.dp))
                                MyScreen(inputViewModel,scaffoldState,scope,isHide)
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun MyScreen(
    viewModel: InputViewModel,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    isHide: MutableState<Boolean>
) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputTextField(
            type = "userName",
            label = "用户名",
            value = viewModel.name,
            onValueChanged = { viewModel.onNameChange(it) },
            viewModel = viewModel
        )
        InputTextField(
            type = "password",
            label = "请输入密码",
            keyboardOptions = KeyboardOptions(keyboardType = Password),
            value = viewModel.pwd,
            onValueChanged = {viewModel.onPwdChange(it)},
            viewModel = viewModel
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch { scaffoldState.snackbarHostState.showSnackbar("${viewModel.name}登录成功") }
            },
            enabled = viewModel.name.isNotBlank() && viewModel.pwd.isNotBlank(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(disabledBackgroundColor = primaryColor,backgroundColor = primaryDeepColor),
            contentPadding = PaddingValues(12.dp, 16.dp)
        ) {
            Text("登录", color = Color.White, fontSize = 18.sp)
        }
    }

}

@Composable
fun InputTextField(
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    onValueChanged: (String) -> Unit,
    type: String,
    viewModel: InputViewModel
) {
    var showPwd by remember {
        mutableStateOf(true)
    }

    val tint = if (type=="userName") "请输入用户名" else "请输入密码"
    val visualTransformation = if (showPwd ||type=="password") PasswordVisualTransformation() else VisualTransformation.None

    TextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = LocalTextStyle.current,
        //label = { Text(text = label) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth().onFocusChanged {
            viewModel.onFocusHide(it.isFocused && type == "password")
        },
        colors= TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            leadingIconColor = primaryColor,
            focusedIndicatorColor = primaryColor,
        ),
        placeholder = {Text(tint)},
        leadingIcon = {
            Icon(
                imageVector =if (type=="userName") Icons.Default.AccountBox else Icons.Default.Lock,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (type == "password") {
                if (showPwd) {
                    IconButton(onClick = { showPwd = !showPwd }) {
                        Icon(
                            painter = painterResource(id = R.drawable.design_ic_visibility_off),
                            contentDescription = null,
                            Modifier.size(30.dp)
                        )
                    }
                } else {
                    IconButton(onClick = { showPwd = !showPwd }) {
                        Icon(
                            painter = painterResource(id = R.drawable.design_ic_visibility),
                            contentDescription = null,
                            Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun TopBarView() {
        TopAppBar(
            title = {
                Text(
                    text = "密码登录",
                    color = Color.Black
                )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            },
            actions = {
                TextButton( onClick = { /*TODO*/ }) {
                    Text(text = "注册",color= Gray,fontSize = 18.sp)
                }
            },
            // below line is use to give background color
            backgroundColor = colorResource(id = R.color.white),
            contentColor = Color.Black,
            elevation = 12.dp
        )
}