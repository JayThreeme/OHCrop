package com.ohc.ohcrop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerbtn = findViewById<TextView>(R.id.RegisterBtn)
        registerbtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Register!", Toast.LENGTH_SHORT).show()
        }
    }
}