package com.example.dicerolle

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.reflect.Type
import java.util.ArrayList
import kotlin.random.Random

  class MainActivity : AppCompatActivity() {
      public var HistoryList = ArrayList<ListItem>()
      var Rollnumber = 1
      var ImageList = listOf<Int>(R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6)
      var DiceRolld = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createChannel()
    }


   fun Incrementnumber(btn: View){
       var Counter = findViewById<TextView>(R.id.textView_rollnumber)
       var MinusBtn = findViewById<Button>(R.id.button_minus)
       Rollnumber++
       Counter.setText(Rollnumber.toString())

       if(Rollnumber >=4 ){
           btn.setEnabled(false)
       }
       else
           if(Rollnumber >1 ){
               MinusBtn.setEnabled(true)
           }
   }

    fun Decrementnumber(btn: View){
        var Counter = findViewById<TextView>(R.id.textView_rollnumber)
        var PlusBtn = findViewById<Button>(R.id.button_plus)
        Rollnumber--
        Counter.setText(Rollnumber.toString())

        if(Rollnumber <=1 ){
            btn.setEnabled(false)
        }
        else
            if(Rollnumber <4 ){
                PlusBtn.setEnabled(true)
            }

    }

    fun Runall(btn: View){
        var imagelist = listOf<ImageView>(findViewById(R.id.Dice_1),findViewById(R.id.Dice_2),findViewById(R.id.Dice_3),
            findViewById(R.id.Dice_4))
        var n =0
        var RalledNumbers = ArrayList<Int>()
        for(image in imagelist){
            image.visibility= View.INVISIBLE
        }
        while(n<Rollnumber){
            var number = Random.nextInt(1,7)
            RalledNumbers.add(number)
            imagelist[n].setImageResource(ImageList[number-1])
            imagelist[n].visibility= View.VISIBLE
            n++
        }

        HistoryList.add(ListItem("Roll all dice", RalledNumbers))
        Post(ListItem("Roll all dice", RalledNumbers))

    }

    fun Rallnext(btn: View){
        var imagelist = listOf<ImageView>(findViewById(R.id.Dice_1),findViewById(R.id.Dice_2),findViewById(R.id.Dice_3),
            findViewById(R.id.Dice_4))
        var RalledNumbers = ArrayList<Int>()
        var n=0
        if(DiceRolld == 0 || DiceRolld >= Rollnumber){
            for(image in imagelist){
                image.visibility= View.INVISIBLE
            }
        }
        var number = Random.nextInt(1,7)
        imagelist[DiceRolld].setImageResource(ImageList[number-1])
        imagelist[DiceRolld].visibility = View.VISIBLE
        DiceRolld++

        RalledNumbers.add(number)

        HistoryList.add(ListItem("Roll all dice", RalledNumbers))
        Post(ListItem("Roll all dice", RalledNumbers))
    }


      fun History(btn: View){
          val intent = Intent(this,History::class.java)
          intent.putExtra("List", HistoryList)
          startActivity(intent)
      }

      private fun Post(data:ListItem){

      // ...

      // Instantiate the RequestQueue.
      val queue = Volley.newRequestQueue(this)
      val url = "https://615edba6af365900172045eb.mockapi.io/List-Items"

      // Request a string response from the provided URL.
          val postRequest = JsonObjectRequest(Request.Method.POST, url,
                  JSONObject("{ numbers: ${data.numbers.toString()}}" ),
                  {
                      response -> Log.d("fouad", response.toString())
                  },
                  {
                      error -> Log.d("fouad", error.toString())
                  })

      // Add the request to the RequestQueue.
      queue.add(postRequest)
      }

      private fun createChannel()
      {
          if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
              val name = "super cool channel name"
              val descriptionText = "informative channel description"
              val importance = NotificationManager.IMPORTANCE_DEFAULT
              val channel = NotificationChannel(notification.CHANNEL_ID, name, importance).apply {
                  description = descriptionText
              }
// Register the channel with the system
              val notificationManager: NotificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
              notificationManager.createNotificationChannel(channel)
          }

      }



}