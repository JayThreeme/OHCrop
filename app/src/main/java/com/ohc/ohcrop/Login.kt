package com.ohc.ohcrop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var username = "admin"
        var password = "admin"

        val loginBtn = findViewById<Button>(R.id.LoginBtn)
        loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Login Success!", Toast.LENGTH_SHORT).show()
        }

        val register = findViewById<TextView>(R.id.LoginSignupText)
        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Register!", Toast.LENGTH_SHORT).show()
        }
    }
}