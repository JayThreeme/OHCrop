package com.ohc.ohcrop.reports

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.ohc.ohcrop.R
import com.github.mikephil.charting.utils.ColorTemplate.MATERIAL_COLORS
import com.ohc.ohcrop.Dashboard
import com.ohc.ohcrop.Profile
import com.ohc.ohcrop.Reports

class Ph : AppCompatActivity() {

    private lateinit var ProfileImgButton : ImageButton
    private lateinit var backButton : ImageButton

    lateinit var phBarChart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ph)

        ProfileImgButton = findViewById(R.id.imageBtnProfile)
        backButton = findViewById(R.id.imageBtnBack)

        phBarChart = findViewById(R.id.barChart_PH)

        val  list:ArrayList<BarEntry> = ArrayList()

        list.add(BarEntry(1f, 10f))
        list.add(BarEntry(2f, 20f))
        list.add(BarEntry(3f, 30f))
        list.add(BarEntry(4f, 40f))
        list.add(BarEntry(5f, 80f))

        val barDataSet = BarDataSet(list, "List")

        barDataSet.setColor(MATERIAL_COLORS[3], 255)
        barDataSet.valueTextColor = Color.BLACK

        val barData = BarData(barDataSet)

        phBarChart.setFitBars(true)
        phBarChart.data=barData
        phBarChart.description.text="Ph Chart"
        phBarChart.animateY(1000)




        ProfileImgButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }
        backButton.setOnClickListener {
            startActivity(Intent(this, Reports::class.java))
            finish()
        }




    }
}