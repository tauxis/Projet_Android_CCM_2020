package com.tauxis.projet_android_ccm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tauxis.projet_android_ccm.R
import com.tauxis.projet_android_ccm.model.ObjectDataSample
import com.tauxis.projet_android_ccm.preferences.MyPreferences
import com.tauxis.projet_android_ccm.viewmodel.CustomViewModel

class CustomAdapter (private val context : Context): RecyclerView.Adapter<CustomAdapter.MyCustomViewHolder>(){

    private val myData : ArrayList<ObjectDataSample> = ArrayList()
    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var viewModel = CustomViewModel()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        return MyCustomViewHolder(inflater.inflate(R.layout.item_custom_recycler_2,parent,false))
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        val currentObjectDataSample = myData[position]

        holder.itemView.setOnClickListener{ clickAction(position,currentObjectDataSample) }

        Glide.with(context).load(currentObjectDataSample.url).transition(
            DrawableTransitionOptions.withCrossFade()).thumbnail(
            Glide.with(context)
            .load(currentObjectDataSample.url+"?blur")).into(holder.idImageView)
        holder.textViewValue.text=currentObjectDataSample.value.toString()


    }

    private fun clickAction(position: Int,item : ObjectDataSample){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Supprimer l'item ?")
        builder.setPositiveButton("Oui") { dialog, which ->
            myData.removeAt(position)
            viewModel.deleteItem(item)
            this.notifyDataSetChanged()
        }
        builder.setNegativeButton("Non") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    fun rebuild(myData:ArrayList<ObjectDataSample>){
        this.myData.clear()
        this.myData.addAll(myData)
        this.notifyDataSetChanged()
    }

    inner class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val idImageView : ImageView = itemView.findViewById(R.id.imageViewURL)
        val textViewValue : TextView = itemView.findViewById(R.id.textViewValue)
    }
}