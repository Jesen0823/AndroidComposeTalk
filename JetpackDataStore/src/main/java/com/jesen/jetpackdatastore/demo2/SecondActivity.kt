package com.jesen.jetpackdatastore.demo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.jesen.jetpackdatastore.R
import com.jesen.jetpackdatastore.databinding.ActivitySecondBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    lateinit var binding:ActivitySecondBinding
    lateinit var userManager: UserManager
    var name =""
    var age =0
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        userManager = UserManager(this)

        buttonSave()

        observeData()
    }

    private fun observeData(){
        userManager.userNameFlow.asLiveData().observe(this,{
            name = it
            binding.tvName.text = it.toString()
        })

        userManager.userAgeFlow.asLiveData().observe(this,{
            age = it
            binding.tvAge.text = it.toString()
        })
        userManager.userGenderFlow.asLiveData().observe(this,{
            gender = if (it) "男" else "女"
            binding.tvGender.text = it.toString()
        })
    }

    private fun buttonSave() {

        // 获取输入信息并保存
        binding.btnSave.setOnClickListener {
            name = binding.etName.text.toString()
            age = binding.etAge.text.toString().toInt()
            val isMale = binding.switchGender.isChecked

            //Stores the values
            GlobalScope.launch {
                userManager.saveUser(age, name, isMale)
            }
        }
    }

}