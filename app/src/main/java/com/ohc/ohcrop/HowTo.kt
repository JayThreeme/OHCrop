package com.ohc.ohcrop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ohc.ohcrop.utils.CustomAdapter
import com.ohc.ohcrop.utils.Extensions.toast
import com.ohc.ohcrop.utils.ItemsViewModel

class HowTo : AppCompatActivity() {

    private lateinit var ProfileImgButton : ImageButton
    private lateinit var backButton : ImageButton

    private lateinit var recyclerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_howto)

        ProfileImgButton = findViewById(R.id.imageBtnProfile)
        backButton = findViewById(R.id.imageBtnBack)

        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.cropTrackRecyclerView)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()
        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..10) {
            data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, "Item " + i))
        }
        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


        ProfileImgButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            toast("Profile")
            finish()
        }

        backButton.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
            toast("Crop yield")
            finish()
        }
    }
}