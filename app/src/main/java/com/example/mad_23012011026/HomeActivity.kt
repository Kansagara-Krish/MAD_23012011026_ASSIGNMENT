package com.example.mad_23012011026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentreminderapp.AddReminderActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var greetingText: TextView
    private lateinit var addReminderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        greetingText = findViewById(R.id.greetingText)
        addReminderButton = findViewById(R.id.addReminderButton)

        greetingText.text = "Hello, Student 👋"

        addReminderButton.setOnClickListener {
            val intent = Intent(this, AddReminderActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadReminders()
    }

    private fun loadReminders() {
        val reminderContainer = findViewById<LinearLayout>(R.id.reminderContainer)
        reminderContainer.removeAllViews()

        val sharedPrefs = getSharedPreferences("reminders", MODE_PRIVATE)
        val reminders = sharedPrefs.getStringSet("reminderList", null)

        if (reminders.isNullOrEmpty()) {
            val noReminderText = TextView(this).apply {
                text = "You have no reminders today."
                setTextColor(resources.getColor(android.R.color.darker_gray))
                textSize = 16f
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }
            reminderContainer.addView(noReminderText)
        } else {
            for (reminder in reminders) {
                val parts = reminder.split("\n")
                val title = parts.getOrNull(0) ?: "Title"
                val dateTime = parts.getOrNull(1) ?: "Date/Time"
                val category = parts.getOrNull(2) ?: "Category"

                val rowLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.HORIZONTAL
                    setPadding(8, 8, 8, 8)
                }

                val rowText = TextView(this).apply {
                    text = "$title  |  $dateTime  |  $category"
                    textSize = 14f
                    setTextColor(resources.getColor(android.R.color.black))
                }

                rowLayout.addView(rowText)
                reminderContainer.addView(rowLayout)
            }
        }
    }
}
