package com.example.mad_23012011026 // Corrected package name

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
//import com.example.studentreminderapp.HomeActivity

// No need to import HomeActivity if it's in the same package
// import com.example.mad_23012011026.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupText: TextView
    private lateinit var logoImageView: ImageView
    private var isDayMode = true // You can toggle this based on user preference or system theme

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Assuming your layout file is activity_login, not activity_main
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        signupText = findViewById(R.id.signupText)
        logoImageView = findViewById(R.id.logoImageView)

        // Switch logo based on mode
        updateLogo()

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                if (email == "student@example.com" && password == "123456") {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signupText.setOnClickListener {
            // Uncomment this to enable navigation to your SignupActivity
            // startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun updateLogo() {
        // R.drawable.logo_1 and R.drawable.logo_2 must exist in your res/drawable folder
        val logoResId = if (isDayMode) R.drawable.logo_1 else R.drawable.logo_2
        logoImageView.setImageResource(logoResId)
    }
}
