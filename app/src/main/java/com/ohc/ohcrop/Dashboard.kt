package com.ohc.ohcrop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val monitor = findViewById<Button>(R.id.dashboardMonitorBtn)
        monitor.setOnClickListener {
            val intent = Intent(this, Monitor::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Register!", Toast.LENGTH_SHORT).show()
        }
    }
}