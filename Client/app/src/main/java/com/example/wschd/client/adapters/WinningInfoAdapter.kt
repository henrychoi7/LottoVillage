package com.example.wschd.client.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wschd.client.R
import com.example.wschd.client.base.BaseApplication
import com.example.wschd.client.model.Model


class WinningInfoAdapter(private val mWinningInfoArrayList: ArrayList<Model.DetailsOfWinningInfo>) : RecyclerView.Adapter<WinningInfoAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val homeTextViewWinningEventType: TextView = view.findViewById(R.id.home_text_winning_event_type)
        val homeTextViewWinningDateHour: TextView = view.findViewById(R.id.home_text_winning_date_hour)

        val homeTextViewWinning1: TextView = view.findViewById(R.id.home_text_winning_1)
        val homeTextViewWinning2: TextView = view.findViewById(R.id.home_text_winning_2)
        val homeTextViewWinning3: TextView = view.findViewById(R.id.home_text_winning_3)
        val homeTextViewWinning4: TextView = view.findViewById(R.id.home_text_winning_4)
        val homeTextViewWinning5: TextView = view.findViewById(R.id.home_text_winning_5)
        val homeTextViewWinning6: TextView = view.findViewById(R.id.home_text_winning_6)
        val homeTextViewWinningBonus: TextView = view.findViewById(R.id.home_text_winning_bonus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinningInfoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_winning_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WinningInfoAdapter.ViewHolder, position: Int) {
        val winningInfoData = mWinningInfoArrayList[position]
        when (winningInfoData.eventType) {
            "1" -> holder.homeTextViewWinningEventType.text = "1"
            "2" -> holder.homeTextViewWinningEventType.text = "6"
            "3" -> holder.homeTextViewWinningEventType.text = "12"
        }
        holder.homeTextViewWinningDateHour.text = winningInfoData.eventDateHour
        BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewWinning1, winningInfoData.winningNumber1)
        BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewWinning2, winningInfoData.winningNumber2)
        BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewWinning3, winningInfoData.winningNumber3)
        BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewWinning4, winningInfoData.winningNumber4)
        BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewWinning5, winningInfoData.winningNumber5)
        BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewWinning6, winningInfoData.winningNumber6)
        BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewWinningBonus, winningInfoData.bonusNumber)

    }

    override fun getItemCount(): Int {
        return mWinningInfoArrayList.size
    }
}