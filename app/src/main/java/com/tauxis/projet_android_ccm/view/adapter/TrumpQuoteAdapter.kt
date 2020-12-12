package com.tauxis.projet_android_ccm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.tauxis.projet_android_ccm.R
import com.tauxis.projet_android_ccm.model.TrumpQuote

class TrumpQuoteAdapter(private val context : Context) : RecyclerView.Adapter<TrumpQuoteAdapter.TrumpQuoteViewHolder>() {

    private val mTrumpQuote = ArrayList<TrumpQuote>()

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrumpQuoteViewHolder {
        // This line perform the matching with our ViewHolder and the item from layout
        return TrumpQuoteViewHolder(mInflater.inflate(R.layout.item_custom_recycler, parent, false))
    }

    override fun getItemCount(): Int  = mTrumpQuote.size

    override fun onBindViewHolder(holder: TrumpQuoteViewHolder, position: Int) {
        // onBindViewHolder is called for each item we want to display so we need to get each object
        val currentItem = mTrumpQuote[position]

        holder.itemRecyclerViewQuote.text = currentItem.quote
        holder.itemRecyclerViewDate.text = currentItem.date.toString()
        Glide.with(context)
            .load(R.drawable.tronalddump)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.itemRecyclerViewImage)
    }


    fun rebuild(trumpQuote : ArrayList<TrumpQuote>) {
        // This is the simplest way to update the list
        mTrumpQuote.clear()
        mTrumpQuote.addAll(trumpQuote)
        // Needed to said to recycler view we have new data
        this.notifyDataSetChanged()
    }

    inner class TrumpQuoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemRecyclerViewQuote: TextView = itemView.findViewById(R.id.itemRecyclerViewQuote)
        val itemRecyclerViewDate: TextView = itemView.findViewById(R.id.itemRecyclerViewDate)
        val itemRecyclerViewImage: ImageView = itemView.findViewById(R.id.itemRecyclerViewImage)
    }


}