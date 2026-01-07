package com.jesen.constraint.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import androidx.constraintlayout.compose.Dimension.Companion.preferredWrapContent

@Composable
fun ConstraintLayoutDemo1() {
    ConstraintLayout() {
        val (loginBtn, userName) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(loginBtn) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                }) {
            Text(text = "登录")
        }

        Text(
            text = "xxx@162.com",
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(userName) {
                    top.linkTo(loginBtn.bottom, margin = 30.dp)

                    // 下面两行等价于：  centerHorizontallyTo(parent)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}


@Composable
fun ConstraintLayoutDemo2() {
    ConstraintLayout() {
        val (loginBtn, userName, password) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(loginBtn) {
                    top.linkTo(userName.bottom, margin = 10.dp)
                    centerAround(userName.end)
                }) {
            Text(text = "登录")
        }

        Text(
            text = "xxx@162.com",
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(userName) {
                    top.linkTo(parent.top, margin = 30.dp)
                }
        )

        val barrier = createEndBarrier(userName, loginBtn)

        Text(
            text = "*********",
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(password) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(barrier)
                }
        )
    }
}


@Composable
fun LargeConstraintLayoutDemo3() {
    ConstraintLayout() {
        val text = createRef()

        val guideline = createGuidelineFromStart(0.5f)
        val guideline2 = createGuidelineFromEnd(0.2f)

        Text(
            "什么是JSON JSON的用法 阿里云双11最高立减1111 恒创科技_海外CN2服务器26元/月起",
            Modifier.constrainAs(text) {
                linkTo(guideline, guideline2, startMargin = 2.dp, endMargin = 2.dp)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
            maxLines = 50,
        )
    }
}

@Composable
fun DecoupledConstraintLayoutDemo4() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun DemoInlineDSL() {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color.Gray.copy(0.3f))
    ) {
        val (textA, textB, textC) = createRefs()

        Text("AAA",
            Modifier
                .wrapContentSize()
                .background(color = Color.Yellow.copy(alpha = 0.5f))
                .constrainAs(textA) {
                    start.linkTo(textB.end, margin = 10.dp)
                })
        Text("BBB",
            Modifier
                .wrapContentSize()
                .background(color = Color.Green.copy(alpha = 0.4f))
                .constrainAs(textB) {
                    centerTo(parent)
                })

        val barrier = createBottomBarrier(textA, textB)
        Text("This is a very long CCCC",
            Modifier
                .wrapContentSize()
                .background(color = Color.Blue.copy(alpha = 0.4f))
                .constrainAs(textC) {
                    top.linkTo(barrier, margin = 10.dp)
                    centerHorizontallyTo(parent)
                    width = Dimension.preferredWrapContent.atMost(20.dp)
                })
    }
}

@Composable
fun DemoConstraintSet() {
    ConstraintLayout(
        ConstraintSet {
        val textA = createRefFor("id_a")
        val textB = createRefFor("id_b")
        val textC = createRefFor("id_c")

        constrain(textA) {
            start.linkTo(textB.end, margin = 20.dp)
        }
        constrain(textB) {
            centerTo(parent)
        }

        val barrier = createBottomBarrier(textA, textB)
        constrain(textC) {
            top.linkTo(barrier, margin = 20.dp)
            centerHorizontallyTo(parent)
            width = Dimension.preferredWrapContent.atMost(20.dp)
        }
    },Modifier.background(color = Color.Gray.copy(0.5f))
    ) {
        Text("Text1", Modifier.layoutId("id_a"))
        Text("Text2", Modifier.layoutId("id_b"))
        Text("This is a very long CCCC", Modifier.layoutId("id_c"))
    }
}


@Composable
fun DemoTagConstraintSet() {
    ConstraintLayout(
        ConstraintSet {
            val textA = createRefFor("id_a")
            val textB = createRefFor("id_b")
            val textC = createRefFor("id_c")

            constrain(textA) {
                start.linkTo(textB.end, margin = 20.dp)
            }
            constrain(textB) {
                centerTo(parent)
            }

            val barrier = createBottomBarrier(textA, textB)
            constrain(textC) {
                top.linkTo(barrier, margin = 20.dp)
                centerHorizontallyTo(parent)
                width = Dimension.preferredWrapContent.atMost(20.dp)
            }
        },Modifier.background(color = Color.Gray.copy(0.5f))
    ) {
        Text("Text1", Modifier.layoutId("id_a"))
        Text("Text2", Modifier.layoutId("id_b"))
        Text("This is a very long CCCC", Modifier.layoutId("id_c"))
    }
}


@Composable
fun ChainExample() {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .background(color = Color.Gray.copy(0.5f))
    ) {
        val (button1, button2, button3) = createRefs()

        createHorizontalChain(button1, button2, button3, chainStyle = ChainStyle.Packed)

        Button(onClick = { }, modifier = Modifier.constrainAs(button1) {
        }) {
            Text(text = "Button 1")
        }

        Button(onClick = { }, modifier = Modifier.constrainAs(button2) {
        }) {
            Text(text = "Button 2")
        }

        Button(onClick = { }, modifier = Modifier.constrainAs(button3) {
        }) {
            Text(text = "Button 3")
        }
    }
}