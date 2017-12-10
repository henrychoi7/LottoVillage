package com.jjosft.android.lottovillage.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
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
class  PointHistoryAdapter(private val mContext: Context, private val mDetailsOfPointHistory: ArrayList<Model.DetailsOfPointHistory>) : RecyclerView.Adapter<PointHistoryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pointHistoryTextViewDateTime: TextView = view.findViewById(R.id.point_history_date_time)
        val pointHistoryTextViewPoint: TextView = view.findViewById(R.id.point_history_point)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointHistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_point_history, parent, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PointHistoryAdapter.ViewHolder, position: Int) {
        val detailsOfPointHistory = mDetailsOfPointHistory[position]
        holder.pointHistoryTextViewDateTime.text = detailsOfPointHistory.dateTime
        when (detailsOfPointHistory.contents) {
            "+" -> {
                holder.pointHistoryTextViewPoint.setTextColor(ContextCompat.getColor(mContext, R.color.blue))
                holder.pointHistoryTextViewPoint.text = "+ ${detailsOfPointHistory.point}P"
            }
            "-" -> {
                holder.pointHistoryTextViewPoint.setTextColor(ContextCompat.getColor(mContext, R.color.red))
                holder.pointHistoryTextViewPoint.text = "- ${detailsOfPointHistory.point}P"
            }
        }
    }
    override fun getItemCount(): Int {
        return mDetailsOfPointHistory.size
    }
}