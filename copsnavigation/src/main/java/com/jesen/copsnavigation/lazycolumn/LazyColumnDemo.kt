package com.jesen.copsnavigation.lazycolumn

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@ExperimentalFoundationApi
@Composable
fun LazyColumnDemo() {
    val sections = listOf("少女", "儿童", "大妇")

    val personList = PersonRepository.getAllDatas()

    LazyColumn(
        contentPadding = PaddingValues(all = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // test 1:
        /*items(items = personList){ person->
            LazyItem(person = person)
        }*/

        // test 2:
        /* itemsIndexed(items = personList){index, item ->
             Log.d("TEST","item index: $index")
             LazyItem(person = item)
         }*/

        // test 3:
        /*itemsIndexed(
            items = personList,
            key = {index, item ->
                item.id
            }
        ){index, item ->
            Log.d("TEST","item index: $index")
            LazyItem(person = item)
        }*/

        // test4:
        /*sections.forEach { section ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Gray)
                        .padding(12.dp),
                    text = "Section:$section"
                )
            }
            items(10) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Item $it from the section $section"
                )

            }
        }*/

        // test5:
        var headerType = ""
        items(items = personList) { person ->
            when (person.age) {
                in 1..12 -> headerType = sections[1]
                in 13..23 -> headerType = sections[0]
                else -> headerType = sections[2]
            }

            if (headerType.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Gray)
                        .padding(12.dp),
                    text = "Section:$headerType"
                )
            }

            LazyItem(person = person)
        }
    }
}