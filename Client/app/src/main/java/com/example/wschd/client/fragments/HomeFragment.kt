package com.example.wschd.client.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wschd.client.R
import com.example.wschd.client.base.BaseApplication
import com.example.wschd.client.adapters.ParticipationAdapter
import com.example.wschd.client.adapters.WinningInfoAdapter
import com.example.wschd.client.model.Model
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private fun makeEmptyParticipationData(eventType: String): Model.DetailsOfParticipation = Model.DetailsOfParticipation(
            eventType, 1, 2, 3,
            4, 5, 6, "")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        retrieveWinningInfo(view)
        return view
    }

    override fun onStop() {
        mCompositeDisposable.clear()
        super.onStop()
    }

    private fun retrieveWinningInfo(view: View) {
        BaseApplication.getInstance().getRetrofitMethod().getDetailsOfAllWinningInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.WinningInfoResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(activity, getString(R.string.request_detail_of_participation))
                    }

                    override fun onNext(t: Model.WinningInfoResponse) {
                        val winningInfoAdapter = WinningInfoAdapter(t.detailsOfWinningInfo)
                        view.home_recycler_view_winning_info.layoutManager = LinearLayoutManager(activity)
                        view.home_recycler_view_winning_info.adapter = winningInfoAdapter
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(activity, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        retrieveMyPoint(view)
                    }
                })
    }

    private fun retrieveMyPoint(view: View) {
        BaseApplication.getInstance().getRetrofitMethod().getMyPoint()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.SingleIntResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(activity, getString(R.string.request_my_point))
                    }

                    override fun onNext(t: Model.SingleIntResponse) {
                        view.home_text_my_point.text = t.results.toString()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(activity, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        retrieveDetailsOfAllParticipation(view)
                    }
                })
    }

    private fun retrieveDetailsOfAllParticipation(view: View) {
        BaseApplication.getInstance().getRetrofitMethod().getDetailsOfAllParticipation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.ParticipationResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(activity, getString(R.string.request_detail_of_participation))
                    }

                    override fun onNext(t: Model.ParticipationResponse) {
                        if (t.isSuccess) {
                            val participationDataArrayList: ArrayList<Model.DetailsOfParticipation> = t.detailsOfParticipation
                            when (participationDataArrayList.size) {
                                0 -> {
                                    participationDataArrayList.add(makeEmptyParticipationData("1"))
                                    participationDataArrayList.add(makeEmptyParticipationData("2"))
                                    participationDataArrayList.add(makeEmptyParticipationData("3"))
                                }
                                1 -> {
                                    when (participationDataArrayList[0].eventType) {
                                        "1" -> {
                                            participationDataArrayList.add(1, makeEmptyParticipationData("2"))
                                            participationDataArrayList.add(2, makeEmptyParticipationData("3"))
                                        }
                                        "2" -> {
                                            participationDataArrayList.add(0, makeEmptyParticipationData("1"))
                                            participationDataArrayList.add(2, makeEmptyParticipationData("3"))
                                        }
                                        "3" -> {
                                            participationDataArrayList.add(0, makeEmptyParticipationData("1"))
                                            participationDataArrayList.add(1, makeEmptyParticipationData("2"))
                                        }
                                    }
                                }
                                2 -> {
                                    when (participationDataArrayList[0].eventType) {
                                        "1" -> {
                                            if (participationDataArrayList[1].eventType == "2") {
                                                participationDataArrayList.add(2, makeEmptyParticipationData("3"))
                                            } else {
                                                participationDataArrayList.add(1, makeEmptyParticipationData("2"))
                                            }
                                        }
                                        "2" -> {
                                            participationDataArrayList.add(0, makeEmptyParticipationData("1"))
                                        }
                                    }
                                }
                            }
                            val participationAdapter = ParticipationAdapter(activity, participationDataArrayList)
                            view.home_recycler_view_participation_hour.layoutManager = LinearLayoutManager(activity)
                            view.home_recycler_view_participation_hour.adapter = participationAdapter
                        } else {
                            if (t.errorMessage == getString(R.string.unmatched_token_value)) Toast.makeText(activity, getString(R.string.request_login), Toast.LENGTH_SHORT).show()
                            else {
                                val participationDataArrayList: ArrayList<Model.DetailsOfParticipation> = ArrayList()
                                participationDataArrayList.add(makeEmptyParticipationData("1"))
                                participationDataArrayList.add(makeEmptyParticipationData("2"))
                                participationDataArrayList.add(makeEmptyParticipationData("3"))
                                val participationAdapter = ParticipationAdapter(activity, participationDataArrayList)
                                view.home_recycler_view_participation_hour.layoutManager = LinearLayoutManager(activity)
                                view.home_recycler_view_participation_hour.adapter = participationAdapter
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(activity, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }
                })
    }
}