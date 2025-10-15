package com.example.studentreminderapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mad_23012011026.R
import java.text.SimpleDateFormat
import java.util.*

class AddReminderActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var saveButton: Button

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        // View bindings
        titleEditText = findViewById(R.id.titleEditText)
        dateEditText = findViewById(R.id.dateEditText)
        timeEditText = findViewById(R.id.timeEditText)
        categorySpinner = findViewById(R.id.categorySpinner)
        saveButton = findViewById(R.id.saveButton)

        setupCategorySpinner()
        setupDatePicker()
        setupTimePicker()

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val date = dateEditText.text.toString()
            val time = timeEditText.text.toString()
            val category = categorySpinner.selectedItem.toString()

            if (title.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Reminder saved: $title", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun setupCategorySpinner() {
        val categories = arrayOf("Assignment", "Exam", "Class", "Personal")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter
    }

    private fun setupDatePicker() {
        dateEditText.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(Calendar.YEAR, selectedYear)
                calendar.set(Calendar.MONTH, selectedMonth)
                calendar.set(Calendar.DAY_OF_MONTH, selectedDay)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                dateEditText.setText(sdf.format(calendar.time))
            }, year, month, day)

            datePickerDialog.show()
        }
    }

    private fun setupTimePicker() {
        timeEditText.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                calendar.set(Calendar.MINUTE, selectedMinute)

                val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                timeEditText.setText(sdf.format(calendar.time))
            }, hour, minute, true) // 'true' = 24-hour format

            timePickerDialog.show()
        }
    }
}
