package com.example.androidpoc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpoc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val isTablet = resources.getBoolean(R.bool.is_tablet)
        val spanCount = if (isTablet) 3 else 1

        val items = generateSampleItems()
        val adapter = ItemAdapter(items)

        binding.recyclerView.apply {
            layoutManager = if (isTablet) {
                GridLayoutManager(this@MainActivity, spanCount)
            } else {
                LinearLayoutManager(this@MainActivity)
            }
            this.adapter = adapter
        }
    }

    private fun generateSampleItems(): List<Item> {
        return listOf(
            Item("Getting Started", "Welcome to AndroidPoc — a sample app with tablet support.", "\uD83D\uDE80"),
            Item("Adaptive Layout", "This app uses resource qualifiers to adapt to different screen sizes.", "\uD83D\uDCF1"),
            Item("Material Design", "Built with Material Design components for a modern look and feel.", "\uD83C\uDFA8"),
            Item("RecyclerView", "Displays items in a list on phones and a grid on tablets.", "\uD83D\uDCCB"),
            Item("View Binding", "Type-safe access to views without findViewById.", "\uD83D\uDD17"),
            Item("GitHub Actions", "CI/CD pipeline with aggressive caching for fast builds.", "\u2699\uFE0F"),
            Item("Kotlin", "Written entirely in Kotlin with modern Android practices.", "\uD83D\uDCDD"),
            Item("Gradle Cache", "Build configuration optimized with Gradle caching enabled.", "\u26A1"),
            Item("Screen Support", "Supports small, normal, large, and xlarge screens.", "\uD83D\uDDA5\uFE0F"),
        )
    }
}

data class Item(
    val title: String,
    val description: String,
    val emoji: String
)
