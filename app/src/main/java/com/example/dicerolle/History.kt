package com.example.dicerolle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class History : AppCompatActivity() {
    private lateinit var List : ArrayList<ListItem>
    private lateinit var NewRecycleView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        NewRecycleView = findViewById(R.id.HistoryList)
        NewRecycleView.layoutManager = LinearLayoutManager(this)
        NewRecycleView.setHasFixedSize(true)
        List = intent.getSerializableExtra("List") as ArrayList<ListItem>
        NewRecycleView.adapter = Adapter(List)
    }
}