package com.ohc.ohcrop.calculator

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ohc.ohcrop.R
import com.ohc.ohcrop.calculator.nutrientsolution.NutrientSolutionMixing
import com.ohc.ohcrop.calculator.watervolume.WaterVolume
import com.ohc.ohcrop.control.RecyclerControlAdapter
import com.ohc.ohcrop.control.fan.FanControl
import com.ohc.ohcrop.control.irrigation.IrrigationControl
import com.ohc.ohcrop.control.light.LightControl
import com.ohc.ohcrop.control.misting.MistingControl
import com.ohc.ohcrop.control.watertank.WaterTankControl
import com.ohc.ohcrop.utils.FirebaseUtils
@Suppress("DEPRECATION")
class RecyclerCalcAdapter: RecyclerView.Adapter<RecyclerCalcAdapter.ViewHolder>() {

    private lateinit var userID: String
    private var title = arrayOf("Nutrient Solution Mixing","Water Volume Calculation", "Yield Estimation")
    private var details = arrayOf("Volume of nutrient solution with the desired concentration","Volume of water in tank size", "Estimate the potential yield of a crop")
    private var images = intArrayOf(R.drawable.nutrientsolutionmixing, R.drawable.watervolume, R.drawable.yieldestimation)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.calculator_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        //lateinit var itemswitch: Switch

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
            //itemswitch = itemView.findViewById(R.id.item_switch)
            userID = FirebaseUtils.firebaseAuth.currentUser!!.uid

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "you clicked ${title[position] + position.toString()}", Toast.LENGTH_SHORT).show()
            }

            itemView.setOnClickListener {
                val position: Int = absoluteAdapterPosition

                if (position != RecyclerView.NO_POSITION){
                    val context = itemView.context
                    when(position){
                        0 -> {
                            val intent = Intent(context, NutrientSolutionMixing::class.java)
                            context.startActivity(intent)
                        }
                        1 -> {
                            val intent = Intent(context, WaterVolume::class.java)
                            context.startActivity(intent)
                        }
                        2 -> {
//                            val intent = Intent(context, IrrigationControl::class.java)
//                            context.startActivity(intent)
                        }
                        3 -> {
//                            val intent = Intent(context, FanControl::class.java)
//                            context.startActivity(intent)
                        }
                        4 -> {
//                            val intent = Intent(context, LightControl::class.java)
//                            context.startActivity(intent)
                        }
                        else -> {

                        }
                    }
                }
                //Toast.makeText(itemView.context, "you clicked " + "${position}", Toast.LENGTH_SHORT).show()
            }

        }

    }
}