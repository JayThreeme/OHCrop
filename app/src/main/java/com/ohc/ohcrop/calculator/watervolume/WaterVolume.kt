package com.ohc.ohcrop.calculator.watervolume

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.ohc.ohcrop.Calculator
import com.ohc.ohcrop.Profile
import com.ohc.ohcrop.R

class WaterVolume : AppCompatActivity() {

    private lateinit var ProfileImgButton : ImageButton
    private lateinit var backButton : ImageButton

    private lateinit var lengthtext : TextView
    private lateinit var widthtext : TextView
    private lateinit var depthtext : TextView
    private lateinit var resulttext : TextView

    private lateinit var resultcard : LinearLayout

    private lateinit var calculate : Button
    private lateinit var reset : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_volume)

        ProfileImgButton = findViewById(R.id.imageBtnProfile)
        backButton = findViewById(R.id.imageBtnBack)


        lengthtext = findViewById(R.id.textlength)
        widthtext = findViewById(R.id.textwidth)
        depthtext = findViewById(R.id.textdepth)
        resulttext = findViewById(R.id.textResult)

        calculate = findViewById(R.id.calculate_button)
        reset = findViewById(R.id.calc_reset_btn)

        resultcard = findViewById(R.id.result_linearlayout)
        resultcard.visibility = View.GONE

        lengthtext.setOnClickListener {
            lengthtext.setText("")
        }
        widthtext.setOnClickListener {
            widthtext.setText("")
        }
        depthtext.setOnClickListener {
            depthtext.setText("")
        }


        calculate.setOnClickListener {
            calculatefun()
        }

        reset.setOnClickListener {
            resetfun()
        }

        ProfileImgButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }

        backButton.setOnClickListener {
            startActivity(Intent(this, Calculator::class.java))
            finish()
        }
    }

    private fun resetfun() {
        lengthtext.setText("0")
        widthtext.setText("0")
        depthtext.setText("0")
        resulttext.setText("0")
        reset.visibility = View.GONE
        resultcard.visibility = View.GONE
        calculate.visibility = View.VISIBLE
    }

    private fun calculatefun() {
        var length = lengthtext.text.toString().toInt()
        var width = widthtext.text.toString().toInt()
        var depth = depthtext.text.toString().toInt()

        var result = length * width * depth

        var volume = (result * 0.0163871)

        // Round the volume to two decimal places
        val formattedVolume = String.format("%.2f", volume)

        resulttext.text = formattedVolume

        resultcard.visibility = View.VISIBLE
        reset.visibility = View.VISIBLE
        calculate.visibility = View.GONE
    }
}