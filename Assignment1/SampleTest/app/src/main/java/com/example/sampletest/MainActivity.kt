package com.example.sampletest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvFeed: RecyclerView = findViewById(R.id.rvFeed)

        rvFeed.layoutManager = LinearLayoutManager(this)

        val postsList = mutableListOf(
            Post("Abzal", R.drawable.cat, 20, "Very cute coffe shop"),
            Post("Abzal", R.drawable.cat, 20, "Very cute coffe shop"),
            Post("Abzal", R.drawable.cat, 20, "Very cute coffe shop"),
            Post("Abzal", R.drawable.cat, 20, "Very cute coffe shop"),
            Post("Abzal", R.drawable.cat, 20, "Very cute coffe shop"),
            Post("Abzal", R.drawable.cat, 20, "Very cute coffe shop")
        )

        val adapter = PostAdapter(postsList)
        rvFeed.adapter = adapter
    }
}