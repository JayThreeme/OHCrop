package com.ohc.ohcrop

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ReportFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ohc.ohcrop.databinding.ActivityMonitorBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round
import kotlin.math.roundToInt




class Monitor : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var binding: ActivityMonitorBinding
    private lateinit var database: DatabaseReference

    private lateinit var backButton: ImageButton
    private lateinit var profileButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)

        binding = ActivityMonitorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseListener()

        backButton = findViewById<ImageButton>(R.id.imageBtnBack)
        profileButton = findViewById<ImageButton>(R.id.imageBtnProfile)

        backButton.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Login Success!", Toast.LENGTH_SHORT).show()
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Login Success!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun databaseListener() {
        database = FirebaseDatabase.getInstance().getReference()
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val watertemp = snapshot.child("Monitor/water/temp/data").value
                val waterlevel = snapshot.child("Monitor/water/level/data").value
                val phlevel = snapshot.child("Monitor/ph/data").value
                val humidity = snapshot.child("Monitor/humidity/data").value
                val airtemp = snapshot.child("Monitor/airtemp/data").value

                //val ph_level = (phlevel.toString().toDouble() * 10000.0).roundToInt() / 10000.0
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.DOWN
                val ph_level = df.format(phlevel.toString().toDouble())
                println(ph_level)
                //val valueType = airtemp?.javaClass?.simpleName ?: "Unknown"
                //binding.textOtherResult.setText("String Value: $vdouble")


                if (watertemp.toString() != null && waterlevel.toString() != null){
                    binding.textWaterTemperature.setText(watertemp.toString().toDouble().toInt().toString()+"°C")
                    binding.progressbarWaterLevel.setProgress(waterlevel.toString().toDouble().toInt(), true)
                }else{
                    binding.textOtherResult.setText("water level & temp null")
                }

                if (phlevel.toString() != null){
                    binding.progressbarPhLevel.setProgress(phlevel.toString().toDouble().toInt(), true)
                    binding.textPhlevel.setText(ph_level.toString()+"")
                }else{
                    binding.textOtherResult.setText("ph level null")
                }

                if (humidity.toString() != null){
                    binding.progressbarHumidity.setProgress(Integer.parseInt(humidity.toString()), true)
                    binding.textHumidity.setText(humidity.toString()+"%")
                }else{
                    binding.textOtherResult.setText("humidity level null")
                }

                if (airtemp.toString() != null){
                    binding.progressbarAirTemp.setProgress(airtemp.toString().toDouble().toInt(), true)
                    binding.textAirtemp.setText(airtemp.toString()+"°C")
                }else{
                    binding.textOtherResult.setText("airtemp level null")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Monitor, "Failed to read data", Toast.LENGTH_SHORT).show()
            }
        }
        database.addValueEventListener(postListener)
    }
}