package com.jesen.copsnavigation.lazycolumn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesen.copsnavigation.R

@Composable
fun LazyItem(person: Person) {

    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxHeight(),
            painter = painterResource(id = person.picRes),
            contentDescription = null
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "年龄：${person.age}",
                color = Color.Yellow,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "名称：${person.firstName}",
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}