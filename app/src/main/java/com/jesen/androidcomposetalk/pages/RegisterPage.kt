package com.jesen.androidcomposetalk.pages

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import com.jesen.androidcomposetalk.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesen.androidcomposetalk.nav.PageRoute
import com.jesen.androidcomposetalk.nav.doPageNavBack
import com.jesen.androidcomposetalk.nav.doPageNavigationTo
import com.jesen.androidcomposetalk.ui.InputTextField
import com.jesen.androidcomposetalk.ui.TopBarView
import com.jesen.androidcomposetalk.ui.inputTogButton
import com.jesen.androidcomposetalk.viewmodel.InputViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegisterPage(activity: ComponentActivity) {

    val inputViewModel by activity.viewModels<InputViewModel>()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { RegisterTopBarView(scope) },
        scaffoldState = scaffoldState
    ) {
        Box(Modifier.fillMaxSize()) {

            headPicEffect(inputViewModel)

            Column(
                modifier = Modifier
                    .padding(5.dp, 120.dp, 5.dp, 0.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier
                    .height(2.dp)
                    .background(color = Color.Gray))
                InputRegisterScreen(inputViewModel, scaffoldState, scope)
            }
        }
    }
}

@Composable
fun InputRegisterScreen(
    viewModel: InputViewModel,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ??????????????????
        val showPwd by remember {
            mutableStateOf(true)
        }

        InputTextField(
            label = "?????????",
            value = viewModel.name,
            hint = "??????????????????",
            onValueChanged = { viewModel.onNameChange(it) },
            type = "userName",
            viewModel = viewModel,
            leadingIcon = Icons.Default.AccountBox,
        )
        InputTextField(
            label = "??????",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = viewModel.pwd,
            hint = "???????????????",
            onValueChanged = { viewModel.onPwdChange(it) },
            type = "password",
            viewModel = viewModel,
            leadingIcon = Icons.Default.Lock,
        )
        InputTextField(
            label = "????????????",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = viewModel.rePassword,
            hint = "?????????????????????",
            onValueChanged = { viewModel.onRePwdChange(it) },
            type = "rePassword",
            viewModel = viewModel,
            leadingIcon = Icons.Default.LockOpen,
        )
        InputTextField(
            label = "??????",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = viewModel.mocId,
            hint = "?????????????????????",
            onValueChanged = { viewModel.onMocIdChange(it) },
            type = "mocId",
            viewModel = viewModel,
            leadingIcon = Icons.Default.VpnKey,
        )
        InputTextField(
            label = "??????id",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = viewModel.orderId,
            hint = "???????????????ID?????????",
            onValueChanged = { viewModel.onOrderIdChange(it) },
            type = "orderId",
            viewModel = viewModel,
            leadingIcon = Icons.Default.AdminPanelSettings,
        )

        inputTogButton("??????", scope, viewModel, scaffoldState)
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
fun RegisterTopBarView(scope: CoroutineScope) {
    TopBarView(
        iconEvent = {
            IconButton(onClick = {
                doPageNavBack(route = null)
            }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        actionEvent = {
            TextButton(onClick = {
                scope.launch { doPageNavigationTo(PageRoute.LOGIN_ROUTE) }

            }) {
                Text(text = "??????", color = Color.Gray, fontSize = 18.sp)
            }
        },
        titleText = "????????????"
    )
}