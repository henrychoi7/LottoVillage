package com.jjosft.android.lottovillage.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.model.Model

class LottoAdapter(private val mLottoData: Model.DetailsOfLotto) : RecyclerView.Adapter<LottoAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lottoTextViewNumberPrize: TextView = view.findViewById(R.id.lotto_text_number_prize)
        val lottoTextViewTotalPrize: TextView = view.findViewById(R.id.lotto_text_total_prize)
        val lottoTextViewTotalNumber: TextView = view.findViewById(R.id.lotto_text_total_number)
        val lottoTextViewPerPrize: TextView = view.findViewById(R.id.lotto_text_per_prize)
        val lottoImageViewPerPrize: ImageView = view.findViewById(R.id.lotto_image_number_prize)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LottoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_lotto_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LottoAdapter.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                setLottoData(holder, "1등", mLottoData.totalPrize1, mLottoData.totalNumber1, mLottoData.perPrize1)
                holder.lottoImageViewPerPrize.setBackgroundResource(R.drawable.prize1)
            }
            1 -> {
                setLottoData(holder, "2등", mLottoData.totalPrize2, mLottoData.totalNumber2, mLottoData.perPrize2)
                holder.lottoImageViewPerPrize.setBackgroundResource(R.drawable.prize2)
            }
            2 -> {
                setLottoData(holder, "3등", mLottoData.totalPrize3, mLottoData.totalNumber3, mLottoData.perPrize3)
                holder.lottoImageViewPerPrize.setBackgroundResource(R.drawable.prize3)
            }
            3 -> {
                setLottoData(holder, "4등", mLottoData.totalPrize4, mLottoData.totalNumber4, mLottoData.perPrize4)
                holder.lottoImageViewPerPrize.setBackgroundResource(R.drawable.prize4)
            }
            4 -> {
                setLottoData(holder, "5등", mLottoData.totalPrize5, mLottoData.totalNumber5, mLottoData.perPrize5)
                holder.lottoImageViewPerPrize.setBackgroundResource(R.drawable.prize5)
            }
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    private fun setLottoData(holder: LottoAdapter.ViewHolder, numberPrize: String, totalPrize: String,
                             totalNumber: String, perPrize: String) {
        holder.lottoTextViewNumberPrize.text = numberPrize
        holder.lottoTextViewTotalPrize.text = totalPrize
        holder.lottoTextViewTotalNumber.text = totalNumber
        holder.lottoTextViewPerPrize.text = perPrize
    }
}