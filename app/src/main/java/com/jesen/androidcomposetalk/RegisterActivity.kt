package com.jesen.androidcomposetalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.unit.dp
import com.jesen.androidcomposetalk.ui.theme.*
import com.jesen.androidcomposetalk.viewmodel.InputViewModel
import kotlinx.coroutines.CoroutineScope

class RegisterActivity : ComponentActivity() {

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
                        topBar = { RegisterTopBarView() },
                        scaffoldState = scaffoldState
                    ) {
                        Box(Modifier.fillMaxSize()) {

                            val isHide = remember { mutableStateOf(false) }
                            headPicEffect(inputViewModel)

                            Column(
                                modifier = Modifier
                                    .padding(5.dp, 120.dp, 5.dp, 0.dp)
                                    .fillMaxWidth()
                            ) {
                                Spacer(modifier = Modifier.height(2.dp).background(color = Color.Gray))
                                InputRegisterScreen(inputViewModel, scaffoldState, scope, isHide)
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun InputRegisterScreen(
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
        // 密码是否明文
        val showPwd by remember {
            mutableStateOf(true)
        }

        InputTextField(
            label = "用户名",
            value = viewModel.name,
            hint = "请输入用户名",
            onValueChanged = { viewModel.onNameChange(it) },
            type = "userName",
            viewModel = viewModel,
            leadingIcon = Icons.Default.AccountBox,
        )
        InputTextField(
            label = "密码",
            keyboardOptions = KeyboardOptions(keyboardType = Password),
            value = viewModel.pwd,
            hint = "请输入密码",
            onValueChanged = { viewModel.onPwdChange(it) },
            type = "password",
            viewModel = viewModel,
            leadingIcon = Icons.Default.Lock,
        )
        InputTextField(
            label = "密码确认",
            keyboardOptions = KeyboardOptions(keyboardType = Password),
            value = viewModel.rePassword,
            hint = "请再次输入密码",
            onValueChanged = { viewModel.onRePwdChange(it) },
            type = "rePassword",
            viewModel = viewModel,
            leadingIcon = Icons.Default.LockOpen,
        )
        InputTextField(
            label = "身份",
            keyboardOptions = KeyboardOptions(keyboardType = Password),
            value = viewModel.mocId,
            hint = "请输入身份凭证",
            onValueChanged = { viewModel.onMocIdChange(it) },
            type = "mocId",
            viewModel = viewModel,
            leadingIcon = Icons.Default.VpnKey,
        )
        InputTextField(
            label = "服务id",
            keyboardOptions = KeyboardOptions(keyboardType = Password),
            value = viewModel.orderId,
            hint = "请输入服务ID后四位",
            onValueChanged = { viewModel.onOrderIdChange(it) },
            type = "orderId",
            viewModel = viewModel,
            leadingIcon = Icons.Default.AdminPanelSettings,
        )

        inputTogButton("注册", scope, viewModel, scaffoldState)
    }

}


@Composable
fun headPicEffect(viewModel: InputViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(120.dp, 120.dp),
            painter = painterResource(id = if (viewModel.isHide) R.drawable.head_left_protect else R.drawable.head_left),
            contentDescription = "left image"
        )
        Image(
            alignment = Alignment.BottomCenter,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "mid image"
        )
        Image(
            modifier = Modifier.size(120.dp, 120.dp),
            painter = painterResource(id = if (viewModel.isHide) R.drawable.head_right_protect else R.drawable.head_right),
            contentDescription = "right image"
        )
    }
}


@Composable
fun RegisterTopBarView() {
    TopBarView(
        backClick = { },
        actionsClick = { },
        actionsText = "登录",
        titleText = "账号注册"
    )
}