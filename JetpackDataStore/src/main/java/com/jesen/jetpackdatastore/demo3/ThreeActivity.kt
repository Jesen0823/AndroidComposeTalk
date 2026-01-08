package com.jesen.jetpackdatastore.demo3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.jesen.jetpackdatastore.R
import com.jesen.jetpackdatastore.databinding.ActivityThreeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ThreeActivity : AppCompatActivity() {

    var _binding: ActivityThreeBinding? = null
    private val binding get() = _binding!!

    private val Context.dataStore by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                save(
                    binding.keyEdt.text.toString(),
                    binding.ValueEdt.text.toString()
                )
            }
        }

        binding.btnRead.setOnClickListener {
            lifecycleScope.launch {
                val value = read(binding.readkeyEdt.text.toString())
                binding.tvReadValue.text = value?:"not find"
            }
        }
    }


    private suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}