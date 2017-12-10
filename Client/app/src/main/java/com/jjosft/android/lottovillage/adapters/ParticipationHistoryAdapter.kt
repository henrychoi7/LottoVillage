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
class ParticipationHistoryAdapter(private val mContext: Context, private val mParticipationHistoryArrayList: ArrayList<Model.DetailsOfParticipationHistory>) : RecyclerView.Adapter<ParticipationHistoryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val participationHistoryTextViewContents: TextView = view.findViewById(R.id.participation_history_contents)
        val participationHistoryTextViewDateTime: TextView = view.findViewById(R.id.participation_history_date_time)
        val participationHistoryTextViewParticipationPoint: TextView = view.findViewById(R.id.participation_history_point)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipationHistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_participation_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParticipationHistoryAdapter.ViewHolder, position: Int) {
        val participationHistoryData: Model.DetailsOfParticipationHistory = mParticipationHistoryArrayList[position]
        holder.participationHistoryTextViewContents.text=participationHistoryData.contents
        holder.participationHistoryTextViewDateTime.text = participationHistoryData.dateTime
        holder.participationHistoryTextViewParticipationPoint.text = participationHistoryData.point
    }

    override fun getItemCount(): Int {
        return mParticipationHistoryArrayList.size
    }
}