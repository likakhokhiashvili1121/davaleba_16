package com.example.davaleba_16.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.davaleba_16.databinding.ActivityMainBinding
import com.example.davaleba_16.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val singleUserAdapter by lazy { SingleUserAdapter() }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        observers()

    }

    private fun init() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = singleUserAdapter.withLoadStateFooter(
                footer = SingleUserLoadAdapter()
            )
        }
    }

    private fun observers() {
        lifecycleScope.launch {
            viewModel.getUserInfo().collect() {
                singleUserAdapter.submitData(it)
            }
        }
    }

}