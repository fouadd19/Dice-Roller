package com.example.dicerolle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

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
        Get(List)
        NewRecycleView.adapter = Adapter(List)
    }

    private fun Get(List : ArrayList<ListItem>){
        // ...

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://615edba6af365900172045eb.mockapi.io/List-Items"

        // Request a string response from the provided URL.
        val postRequest = JsonObjectRequest(Request.Method.GET, url,
                null,
                {
                    response -> Log.d("fouad", response.toString())
                    val response2 = JSONArray(response)
                    Log.d("fouad",response2.toString())
                    for (i in 0 until response2.length())
                    {
                        val json_object = response2.getJSONObject(i)
                        val dice = json_object.getJSONArray("numbers")

                        val temp = mutableListOf<Int>()
                        for (j in 0 until dice.length())
                        {
                            temp.add(dice.get(j) as Int)
                        }
                        List.add(ListItem("",temp))
                    }
                },
                {
                    error -> Log.d("fouad", error.toString())
                })

        // Add the request to the RequestQueue.
        queue.add(postRequest)
    }
}