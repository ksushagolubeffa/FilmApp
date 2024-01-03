package com.example.filmapp

import android.widget.Toast
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseMessageService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Handle the received message and show a notification
        showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
    }

    private fun showNotification(title: String?, body: String?) {

        // Example using Toast:
        Toast.makeText(applicationContext, body ?: "", Toast.LENGTH_SHORT).show()
    }
}