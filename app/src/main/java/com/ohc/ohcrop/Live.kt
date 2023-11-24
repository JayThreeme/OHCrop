package com.ohc.ohcrop

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.ohc.ohcrop.utils.Extensions.toast
import com.ohc.ohcrop.utils.FirebaseUtils
import com.ohc.ohcrop.utils.FirebaseUtils.firebaseRTDB
import java.math.RoundingMode
import java.text.DecimalFormat

class Live : AppCompatActivity() {
    private lateinit var RTDBdatabase: DatabaseReference

    private lateinit var checkedTextView: TextView

    private lateinit var phBarChart: BarChart
    private lateinit var tdsBarChart: BarChart
    private lateinit var waterBarChart: BarChart
    private lateinit var watertempBarChart: BarChart
    private lateinit var humidityBarChart: BarChart
    private lateinit var tempBarChart: BarChart

    private val sensorValues: MutableList<SensorData> = mutableListOf()
    private val  phlistData:ArrayList<BarEntry> = ArrayList()
    private val  tdslistData:ArrayList<BarEntry> = ArrayList()
    private val  waterlistData:ArrayList<BarEntry> = ArrayList()
    private val  watertemplistData:ArrayList<BarEntry> = ArrayList()
    private val  humiditylistData:ArrayList<BarEntry> = ArrayList()
    private val  templistData:ArrayList<BarEntry> = ArrayList()

    private lateinit var userID: String
    private var counter:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)
        checkedTextView = findViewById(R.id.CheckingTextView)

        userID = FirebaseUtils.firebaseAuth.currentUser!!.uid

        phBarChart = findViewById(R.id.chart_1)
        tdsBarChart = findViewById(R.id.chart_2)
        waterBarChart = findViewById(R.id.chart_3)
        watertempBarChart = findViewById(R.id.chart_4)
        humidityBarChart = findViewById(R.id.chart_5)
        tempBarChart = findViewById(R.id.chart_6)

        livechatgenerate()
    }

    private fun livechatgenerate() {
        RTDBdatabase = firebaseRTDB.getReference()

        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val phlevel = snapshot.child("$userID/Monitor/ph").value
                val tdslevel = snapshot.child("$userID/Monitor/tds").value
                val watertemp = snapshot.child("$userID/Monitor/watertemp").value.toString().toDouble()
                val waterlevel = snapshot.child("$userID/Monitor/water").value.toString().toDouble()
                val humidity = snapshot.child("$userID/Monitor/humidity").value.toString().toDouble()
                val airtemp = snapshot.child("$userID/Monitor/airtemp").value.toString().toDouble()

                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.DOWN
                val ph_level:Double = df.format(phlevel).toString().toDouble()
                val tds_level:Double = df.format(tdslevel).toString().toDouble()
                //println(ph_level)

                sensorValues.add(SensorData(ph_level,tds_level,watertemp,waterlevel,humidity,airtemp))
                //checkedTextView.text = sensorValues.toString()
                putOnLists()
                putOnPhChart(phBarChart,phlistData)
                putOnPhChart(tdsBarChart,tdslistData)
                putOnPhChart(waterBarChart,waterlistData)
                putOnPhChart(watertempBarChart,watertemplistData)
                putOnPhChart(humidityBarChart,humiditylistData)
                putOnPhChart(tempBarChart,templistData)
            }

            override fun onCancelled(error: DatabaseError) {
                toast("Failed to read Data")
            }
        }
        RTDBdatabase.addValueEventListener(postListener)
        counter+=1
    }

    private fun putOnLists() {
        for ((index, sensorData) in sensorValues.withIndex()) {
            val barEntry = BarEntry(index.toFloat(), sensorData.ph.toFloat())
            phlistData.add(barEntry)
        }
        for ((index, sensorData) in sensorValues.withIndex()) {
            val barEntry = BarEntry(index.toFloat(), sensorData.tds.toFloat())
            tdslistData.add(barEntry)
        }
        for ((index, sensorData) in sensorValues.withIndex()) {
            val barEntry = BarEntry(index.toFloat(), sensorData.water.toFloat())
            waterlistData.add(barEntry)
        }
        for ((index, sensorData) in sensorValues.withIndex()) {
            val barEntry = BarEntry(index.toFloat(), sensorData.watertemp.toFloat())
            watertemplistData.add(barEntry)
        }
        for ((index, sensorData) in sensorValues.withIndex()) {
            val barEntry = BarEntry(index.toFloat(), sensorData.humidity.toFloat())
            humiditylistData.add(barEntry)
        }
        for ((index, sensorData) in sensorValues.withIndex()) {
            val barEntry = BarEntry(index.toFloat(), sensorData.temp.toFloat())
            templistData.add(barEntry)
        }
    }

    private fun putOnPhChart(sensorChart: BarChart, listOfData: ArrayList<BarEntry>) {//
        val barDataSet = BarDataSet(listOfData, "Values")
        barDataSet.setColor(ColorTemplate.MATERIAL_COLORS[3], 255)
        barDataSet.valueTextColor = Color.BLACK

        val barData = BarData(barDataSet)

        val legend = sensorChart.legend
        legend.setDrawInside(false)

        sensorChart.setFitBars(false)
        sensorChart.setDrawMarkers(false)
        sensorChart.setDrawGridBackground(false)
        sensorChart.setDrawValueAboveBar(false)
        sensorChart.setDrawBarShadow(false)
        sensorChart.setPinchZoom(false)


        val Xaxis: XAxis? = sensorChart.xAxis
        Xaxis?.setDrawGridLines(false)
        Xaxis?.setDrawLabels(false)

        val Yaxis: YAxis? = sensorChart.axisRight
        Yaxis?.setDrawLabels(false)

        sensorChart.data=barData
        sensorChart.description.text="MP Chart"
        //phBarChart.animateY(2000)
        sensorChart.invalidate()
    }
}

data class SensorData(
    var ph: Double,
    var tds: Double,
    var water: Double,
    var watertemp: Double,
    var humidity: Double,
    var temp: Double
)

