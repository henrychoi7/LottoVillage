package com.example.wschd.client.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.wschd.client.R
import com.example.wschd.client.base.BaseApplication
import com.example.wschd.client.model.Model
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import org.json.JSONObject


class ParticipationAdapter(private val mActivity: Activity, private var mParticipationDataArrayList: ArrayList<Model.DetailsOfParticipation>)
    : RecyclerView.Adapter<ParticipationAdapter.ViewHolder>() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val homeTextViewTitle: TextView = view.findViewById(R.id.home_text_participation_title)
        val homeTextViewHelp: TextView = view.findViewById(R.id.home_text_participation_help)
        val homeLinearLayoutParticipation: LinearLayout = view.findViewById(R.id.home_linear_layout_participation)
        val homeTextViewParticipation1: TextView = view.findViewById(R.id.home_text_participation_1)
        val homeTextViewParticipation2: TextView = view.findViewById(R.id.home_text_participation_2)
        val homeTextViewParticipation3: TextView = view.findViewById(R.id.home_text_participation_3)
        val homeTextViewParticipation4: TextView = view.findViewById(R.id.home_text_participation_4)
        val homeTextViewParticipation5: TextView = view.findViewById(R.id.home_text_participation_5)
        val homeTextViewParticipation6: TextView = view.findViewById(R.id.home_text_participation_6)
        val homeTextViewParticipatingTime: TextView = view.findViewById(R.id.home_text_participating_time)
        val homeButtonParticipation: Button = view.findViewById(R.id.home_button_participation_lotto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipationAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_participation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParticipationAdapter.ViewHolder, position: Int) {
        val participationData: Model.DetailsOfParticipation = mParticipationDataArrayList[position]
        when (position) {
            0 -> {
                holder.homeTextViewTitle.text = mActivity.getString(R.string.one_hour_lotto_number)
            }
            1 -> {
                holder.homeTextViewTitle.text = mActivity.getString(R.string.six_hour_lotto_number)
            }
            2 -> {
                holder.homeTextViewTitle.text = mActivity.getString(R.string.twelve_hour_lotto_number)
            }
        }
        holder.homeButtonParticipation.setOnClickListener({ participation(participationData.eventType, position) })
        if (participationData.participatingTime == "") {
            holder.homeTextViewHelp.visibility = View.VISIBLE
            holder.homeLinearLayoutParticipation.visibility = View.INVISIBLE
        } else {
            holder.homeTextViewHelp.visibility = View.INVISIBLE
            holder.homeLinearLayoutParticipation.visibility = View.VISIBLE
            holder.homeButtonParticipation.isEnabled = false
            BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewParticipation1, participationData.winningNumber1)
            BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewParticipation2, participationData.winningNumber2)
            BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewParticipation3, participationData.winningNumber3)
            BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewParticipation4, participationData.winningNumber4)
            BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewParticipation5, participationData.winningNumber5)
            BaseApplication.getInstance().setLottoNumberBackground(holder.homeTextViewParticipation6, participationData.winningNumber6)
        }
        holder.homeTextViewParticipatingTime.text = participationData.participatingTime
    }

    override fun getItemCount(): Int {
        return mParticipationDataArrayList.size
    }

    private fun detailsOfParticipation(eventType: String, eventDate: String = "", eventNumber: String = "", isConfirmedStatus: Boolean = false, isContinued: Boolean = false, index: Int) {
        BaseApplication.getInstance().getRetrofitMethod().getDetailsOfParticipation(eventType, eventDate, eventNumber, isConfirmedStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.ParticipationResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(mActivity, "$eventType 회차 ${mActivity.getString(R.string.request_detail_of_participation)}")
                    }

                    override fun onNext(t: Model.ParticipationResponse) {
                        if (t.isSuccess) {
                            mParticipationDataArrayList[index] = t.detailsOfParticipation[0]
                        } else {
                            if (t.errorMessage == mActivity.getString(R.string.unmatched_token_value)) Toast.makeText(mActivity, mActivity.getString(R.string.request_login), Toast.LENGTH_SHORT).show()
                            else {
                                mParticipationDataArrayList[index] = Model.DetailsOfParticipation("", 1,
                                        12, 23, 34, 45,
                                        16, "")
                                Toast.makeText(mActivity, t.errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        }

                        notifyItemChanged(index)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(mActivity, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        if (!isContinued) BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }
                })
    }

    private fun participation(eventType: String, index: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("event_type", eventType)

        BaseApplication.getInstance().getRetrofitMethod().postParticipation(RequestBody.create(BaseApplication.MEDIA_TYPE_JSON, jsonObject.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.DefaultResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(mActivity, "$eventType 회차 ${mActivity.getString(R.string.participating_lotto)}")
                    }

                    override fun onNext(t: Model.DefaultResponse) {
                        if (t.isSuccess) {
                            Toast.makeText(mActivity, "로또참여 성공", Toast.LENGTH_SHORT).show()
                        } else {
                            if (t.errorMessage == mActivity.getString(R.string.unmatched_token_value)) {
                                Toast.makeText(mActivity, mActivity.getString(R.string.request_login), Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(mActivity, t.errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(mActivity, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        //BaseApplication.getInstance().progressOff()
                        detailsOfParticipation(eventType, isContinued = false, index = index)
                    }
                })
    }
}