package com.jesen.jetpackdatastore.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.jesen.jetpackdatastore.R
import com.jesen.jetpackdatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var flowPerfaces: DataStorePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 使用1
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.readFromDataStore.observe(this, { str ->
            binding.content.text = str
        })


        // 使用2
        flowPerfaces = DataStorePreferences(this)
        lifecycleScope.launch {
            flowPerfaces.saveAuthToken("hhh")
        }

        binding.button.setOnClickListener {
            val myName = binding.content.text
            viewModel.saveToDataStore("liveData->$myName")

            // 使用2
            flowPerfaces.authToken.asLiveData().observe(this, Observer {
                Toast.makeText(this,"|$it|",Toast.LENGTH_SHORT).show()
            })
        }
    }
}