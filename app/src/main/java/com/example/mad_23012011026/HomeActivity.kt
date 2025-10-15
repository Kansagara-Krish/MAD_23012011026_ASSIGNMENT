package com.example.mad_23012011026 // Corrected package name to match your file's location

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mad_23012011026.AddReminderActivity


annotation class AddReminderActivity


class HomeActivity : AppCompatActivity() {

    private lateinit var greetingText: TextView
    private lateinit var addReminderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize the views from the layout
        greetingText = findViewById(R.id.greetingText)
        addReminderButton = findViewById(R.id.addReminderButton)

        // Set a welcome message
        greetingText.text = "Hello, Student 👋"

        // Set a click listener for the "Add Reminder" button
        addReminderButton.setOnClickListener {
            // This is the corrected logic:
            // It creates a new Intent to start AddReminderActivity.
            // You will need to create the 'AddReminderActivity.kt' file and its layout next.
            val intent = Intent(this, AddReminderActivity::class.java)
            startActivity(intent)
        }
    }
}
