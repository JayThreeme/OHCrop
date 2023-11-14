package com.ohc.ohcrop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.ohc.ohcrop.utils.Extensions.toast
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


//        val monitor = findViewById<Button>(R.id.dashboardMonitorBtn)
//        monitorButton.setOnClickListener {
//            startActivity(Intent(this, Monitor::class.java))
//            Toast.makeText(applicationContext,"Monitor System", Toast.LENGTH_SHORT).show()
//            finish()
//        }

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

        howToButton.setOnClickListener {
            startActivity(Intent(this, HowTo::class.java))
            //toast("How To")
            finish()
        }

        logOutButton.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, Login::class.java))
            toast("signed out")
            finish()
        }

    }
}