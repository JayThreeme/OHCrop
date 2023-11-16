package com.ohc.ohcrop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.ohc.ohcrop.reports.AirTemp
import com.ohc.ohcrop.reports.Humidity
import com.ohc.ohcrop.reports.Ph
import com.ohc.ohcrop.reports.Tds
import com.ohc.ohcrop.reports.Water
import com.ohc.ohcrop.reports.WaterTemp


class Reports : AppCompatActivity() {

    private lateinit var ProfileImgButton : ImageButton
    private lateinit var backButton : ImageButton

    private lateinit var phlevel_btn : Button
    private lateinit var tds_btn : Button
    private lateinit var watertemp_btn : Button
    private lateinit var waterlvel_btn : Button
    private lateinit var humidity_btn : Button
    private lateinit var airtemp_btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        ProfileImgButton = findViewById(R.id.imageBtnProfile)
        backButton = findViewById(R.id.imageBtnBack)

        phlevel_btn = findViewById(R.id.rBtn_PH)
        tds_btn = findViewById(R.id.rBtn_TDS)
        watertemp_btn = findViewById(R.id.rBtn_WaterTemp)
        waterlvel_btn = findViewById(R.id.rBtn_Water)
        humidity_btn = findViewById(R.id.rBtn_Humidity)
        airtemp_btn = findViewById(R.id.rBtn_AirTemp)

        phlevel_btn.setOnClickListener {
            startActivity(Intent(this, Ph::class.java))
            finish()
        }
        tds_btn.setOnClickListener {
            startActivity(Intent(this, Tds::class.java))
            finish()
        }
        waterlvel_btn.setOnClickListener {
            startActivity(Intent(this, Water::class.java))
            finish()
        }
        watertemp_btn.setOnClickListener {
            startActivity(Intent(this, WaterTemp::class.java))
            finish()
        }
        humidity_btn.setOnClickListener {
            startActivity(Intent(this, Humidity::class.java))
            finish()
        }
        airtemp_btn.setOnClickListener {
            startActivity(Intent(this, AirTemp::class.java))
            finish()
        }




        ProfileImgButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }
        backButton.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }

    }
}