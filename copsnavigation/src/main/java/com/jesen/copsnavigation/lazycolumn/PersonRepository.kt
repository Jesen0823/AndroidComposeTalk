package com.jesen.copsnavigation.lazycolumn

import com.jesen.copsnavigation.R

object PersonRepository {
    fun getAllDatas():List<Person>{
        return listOf(
            Person(
                id = 0,
                firstName = "初出",
                lastName ="卡丽达",
                age = 88,
                picRes = R.drawable.aratar_31
            ),
            Person(
                id = 1,
                firstName = "骗了我",
                lastName ="课题",
                age = 25,
                picRes = R.drawable.aratar_33
            ),
            Person(
                id = 2,
                firstName = "V型和",
                lastName ="数据库",
                age = 22,
                picRes = R.drawable.aratar_38
            ),
            Person(
                id = 3,
                firstName = "火锅店",
                lastName ="可能",
                age = 19,
                picRes = R.drawable.aratar_34
            ),
            Person(
                id = 4,
                firstName = "完",
                lastName ="天然",
                age = 22,
                picRes = R.drawable.aratar_39
            ),
            Person(
                id = 5,
                firstName = "梨藕汁",
                lastName ="能很好地",
                age = 23,
                picRes = R.drawable.aratar_38
            ),
            Person(
                id = 6,
                firstName = "后花园",
                lastName ="囖件",
                age = 21,
                picRes = R.drawable.aratar_40
            ),
            Person(
                id = 7,
                firstName = "孔欧鸥",
                lastName ="Uuu",
                age = 20,
                picRes = R.drawable.aratar_37
            ),
            Person(
                id = 8,
                firstName = "Yui",
                lastName ="火狐",
                age = 18,
                picRes = R.drawable.aratar_34
            ),
            Person(
                id = 9,
                firstName = "胡",
                lastName ="玉玉",
                age = 8,
                picRes = R.drawable.aratar_31
            ),
            Person(
                id = 10,
                firstName = "回收",
                lastName ="嘤嘤嘤",
                age = 88,
                picRes = R.drawable.aratar_39
            ),
            Person(
                id = 11,
                firstName = "啰嗦",
                lastName ="及卡特",
                age = 19,
                picRes = R.drawable.aratar_33
            ),
        )
    }
}