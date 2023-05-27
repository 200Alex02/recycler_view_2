package com.example.recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlantAdapter {
            deleteItem(it)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(plants)

    }

    private fun deleteItem(position: Int) {
        val currentList = adapter.currentList.toMutableList()
        currentList.removeAt(position)
        adapter.submitList(currentList)
    }

}
