package com.example.dicerolle

import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class notification: FirebaseMessagingService()
{

    companion object
    {
        @JvmStatic
        val CHANNEL_ID = "19"
    }

    override fun onNewToken(token:String)
    {
        super.onNewToken(token);
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage)
    {
        super.onMessageReceived(remoteMessage)
        Log.d("fouad","test")

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.dice_1)
            .setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this))
        {
            notify(19, builder.build())
        }

        if (remoteMessage.notification!!.title =="fouad")
        {
            val intent = Intent(this, History::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            startActivity(intent)
        }

    }
}