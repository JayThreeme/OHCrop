package com.ohc.ohcrop

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.ohc.ohcrop.databinding.ActivityDashboardBinding
import com.ohc.ohcrop.utils.Extensions.toast
import com.ohc.ohcrop.utils.FirebaseUtils
import com.ohc.ohcrop.utils.FirebaseUtils.firebaseAuth

class Dashboard : AppCompatActivity() {

    private lateinit var ProfileImgButton : ImageButton
    private lateinit var backButton : ImageButton

    private lateinit var monitorButton : Button
    private lateinit var cropTrack : Button
    private lateinit var controlButton : Button
    private lateinit var reportButton : Button
    private lateinit var liveButton : Button
    private lateinit var howToButton : Button
    private lateinit var logOutButton : Button

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    private lateinit var userID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        ProfileImgButton = findViewById(R.id.imageBtnProfile)
        backButton = findViewById(R.id.imageBtnBack)

        monitorButton = findViewById(R.id.dashboardMonitorBtn)
        cropTrack = findViewById(R.id.dashboardCropTrack)
        controlButton = findViewById(R.id.dashboardControlBtn)
        reportButton = findViewById(R.id.dashboardReportBtn)
        liveButton = findViewById(R.id.dashboardLiveBtn)
        howToButton = findViewById(R.id.dashboardHowToBtn)
        logOutButton = findViewById(R.id.dashboardLogoutBtn)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        userID = FirebaseUtils.firebaseAuth.currentUser!!.uid


        monitorButton.setOnClickListener {
            startActivity(Intent(this, Monitor::class.java))
            //toast("Monitor System")
            finish()
        }
        controlButton.setOnClickListener {
            startActivity(Intent(this, Control::class.java))
            //toast("Crop Track")
            finish()
        }
        cropTrack.setOnClickListener {
            startActivity(Intent(this, CropTrack::class.java))
            //toast("Crop Track")
            finish()
        }
        reportButton.setOnClickListener {
            startActivity(Intent(this, Reports::class.java))
            //toast("Crop Track")
            finish()
        }
        liveButton.setOnClickListener {
            startActivity(Intent(this, Live::class.java))
            //toast("Crop Track")
            finish()
        }

        howToButton.setOnClickListener {
            startActivity(Intent(this, HowTo::class.java))
            //toast("How To")
            finish()
        }


        ProfileImgButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            finish()
        }

        logOutButton.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, Login::class.java))
            toast("signed out")
            finish()
        }

       // valueoff()

    }

//    private fun valueoff() {
//        val userMap = hashMapOf(
//            "devicestatus" to false,
//            "deviceip" to "",
//            "devicessid" to "",
//            "ph" to false,
//            "tds" to false,
//            "water" to false,
//            "watertemp" to false,
//            "humidity" to false,
//            "airtemp" to false
//        )
//
//        FirebaseUtils.firestore.collection("user").document(userID).collection("setting").document("default").set(userMap)
//            .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document",e) }
//    }
}