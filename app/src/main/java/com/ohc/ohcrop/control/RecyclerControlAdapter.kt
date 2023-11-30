package com.ohc.ohcrop.control

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ohc.ohcrop.R
import com.ohc.ohcrop.howto.appnavigation.AppNavigation
import com.ohc.ohcrop.howto.monitorandcontrol.MonitoringAndControl
import com.ohc.ohcrop.howto.monitoringsensors.MonitoringSensors
import com.ohc.ohcrop.utils.FirebaseUtils

@Suppress("DEPRECATION")
class RecyclerControlAdapter: RecyclerView.Adapter<RecyclerControlAdapter.ViewHolder>() {
    private lateinit var userID: String
    private var title = arrayOf("Water Level", "Water Temp", "Ph Level", "Temperature", "Humidity", "TDS Level")
    private var details = arrayOf("item1", "item2", "item3", "item4", "item5", "item6")
    private var images = intArrayOf(R.drawable.water, R.drawable.watertemp, R.drawable.ph, R.drawable.airtemp, R.drawable.humidity, R.drawable.tds)
    private var switch = booleanArrayOf(true,true,true,true,true,true)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.control_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])
        holder.itemswitch.isChecked = switch[position]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        lateinit var itemswitch: Switch

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
            itemswitch = itemView.findViewById(R.id.item_switch)
            userID = FirebaseUtils.firebaseAuth.currentUser!!.uid

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "you clicked ${title[position] + position.toString()}", Toast.LENGTH_SHORT).show()
            }

            itemswitch.setOnClickListener {
                val position: Int = absoluteAdapterPosition


                if (position != RecyclerView.NO_POSITION){
                    when(position){
                        0 -> {
                            val ischecked: Boolean = itemswitch.isChecked
                            FirebaseUtils.firestore.collection("user").document(userID).collection("setting").document("default").update("water", ischecked)
                                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document",e) }
                        }
                        1 -> {
                            val ischecked: Boolean = itemswitch.isChecked
                            FirebaseUtils.firestore.collection("user").document(userID).collection("setting").document("default").update("watertemp", ischecked)
                                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document",e) }
                        }
                        2 -> {
                            val ischecked: Boolean = itemswitch.isChecked
                            FirebaseUtils.firestore.collection("user").document(userID).collection("setting").document("default").update("ph", ischecked)
                                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document",e) }
                        }
                        3 -> {
                            val ischecked: Boolean = itemswitch.isChecked
                            FirebaseUtils.firestore.collection("user").document(userID).collection("setting").document("default").update("airtemp", ischecked)
                                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document",e) }
                        }
                        4 -> {
                            val ischecked: Boolean = itemswitch.isChecked
                            FirebaseUtils.firestore.collection("user").document(userID).collection("setting").document("default").update("humidity", ischecked)
                                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document",e) }
                        }
                        5 -> {
                            val ischecked: Boolean = itemswitch.isChecked
                            FirebaseUtils.firestore.collection("user").document(userID).collection("setting").document("default").update("tds", ischecked)
                                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document",e) }
                        }
                        else -> {

                        }
                    }
                }
            }

        }

    }
}