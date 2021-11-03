package com.jesen.jetpackdatastore.demo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.lifecycleScope
import com.jesen.jetpackdatastore.R
import com.jesen.jetpackdatastore.databinding.ActivityThreeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ThreeActivity : AppCompatActivity() {

    var _binding: ActivityThreeBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = createDataStore(name = "settings")

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
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}