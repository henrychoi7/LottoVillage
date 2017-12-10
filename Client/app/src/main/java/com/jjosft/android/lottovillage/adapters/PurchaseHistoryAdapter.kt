package com.jjosft.android.lottovillage.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.model.Model

/**
 * Created by JJSOFT-DESKTOP on 2017-09-04.
 */
class PurchaseHistoryAdapter(private val mContext:Context,private val mDetailOfPurchaseHistory:ArrayList<Model.DetailsOfAllProduct>) : RecyclerView.Adapter<PurchaseHistoryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val purchaseHistoryTextViewDateTime:TextView=view.findViewById(R.id.purchase_history_date_time)
        val purchaseHistoryTextViewContents:TextView=view.findViewById(R.id.purchase_history_contents)
        val purchaseHistoryTextViewPoint:TextView=view.findViewById(R.id.purchase_history_point)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseHistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_purchase_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseHistoryAdapter.ViewHolder, position: Int) {
        val detailsOfPurchaseHistory=mDetailOfPurchaseHistory[position]
        holder.purchaseHistoryTextViewDateTime.text=detailsOfPurchaseHistory.dateTime
        holder.purchaseHistoryTextViewContents.text=detailsOfPurchaseHistory.contents
        holder.purchaseHistoryTextViewPoint.text=detailsOfPurchaseHistory.point
    }

    override fun getItemCount(): Int {
        return mDetailOfPurchaseHistory.size
    }
}