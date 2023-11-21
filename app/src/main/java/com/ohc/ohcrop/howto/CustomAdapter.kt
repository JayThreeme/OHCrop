package com.ohc.ohcrop.howto

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ohc.ohcrop.Profile
import com.ohc.ohcrop.R
import com.ohc.ohcrop.howto.appnavigation.AppNavigation
import com.ohc.ohcrop.howto.monitorandcontrol.MonitoringAndControl
import com.ohc.ohcrop.howto.monitoringsensors.MonitoringSensors

class CustomAdapter(private val mList: List<ItemsViewModel>, private val context: Context): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.howto_card_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text

//        holder.itemView.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//                val activity=v!!.context as AppCompatActivity
//                val ohCropDevice = OhCropDevice()
//                activity.supportFragmentManager.beginTransaction().replace(R.id.howmains, ohCropDevice).addToBackStack(null).commit()
//                //activity.findViewById<RelativeLayout>(R.id.howmains).bringToFront()
//            }
//        })

    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener{
                val position: Int = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    when(position){
                        0 -> {
                            val intent = Intent(context, MonitoringAndControl::class.java)
                            intent.putExtra("selectedItem", position)
                            context.startActivity(intent)
                        }
                        1 -> {
                            val intent = Intent(context, MonitoringSensors::class.java)
                            intent.putExtra("selectedItem", position)
                            context.startActivity(intent)
                        }
                        2 -> {
                            val intent = Intent(context, AppNavigation::class.java)
                            intent.putExtra("selectedItem", position)
                            context.startActivity(intent)
                        }
                        3 -> {

                        }
                        else -> {

                        }
                    }




//                    Toast.makeText(itemView.context, "you clicked " +
//                            "${position}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }



}