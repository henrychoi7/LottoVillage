package com.jjosft.android.lottovillage.fragments

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.activities.PredictNumberActivity
import com.jjosft.android.lottovillage.activities.SelectAlgorithmActivity
import com.jjosft.android.lottovillage.adapters.LottoAdapter
import com.jjosft.android.lottovillage.base.BaseApplication
import com.jjosft.android.lottovillage.model.Model
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_lotto.view.*

/**
 * Created by JJSOFT-DESKTOP on 2017-09-03.
 */
class LottoFragment : Fragment() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_lotto, container, false)
        view.lotto_button_retrieve.setOnClickListener({ retrieveRealLotto(view, view.lotto_spinner_rounds.selectedItem.toString()) })
        view.prediction_number.setOnClickListener({startActivity(Intent(activity, PredictNumberActivity::class.java))})
        prepareLottoRounds(view)
        return view
    }

    override fun onStop() {
        mCompositeDisposable.clear()
        super.onStop()
    }

    private fun prepareLottoRounds(view: View) {
        BaseApplication.getInstance().getRetrofitMethod().getLottoRounds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.SingleIntResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(activity, getString(R.string.request_lotto_rounds))
                    }

                    override fun onNext(t: Model.SingleIntResponse) {
                        if (t.isSuccess) {
                            val maxRound: Int = t.results
                            val roundArrayList: ArrayList<String> = ArrayList()
                            (maxRound downTo 1).mapTo(roundArrayList) { it.toString() }
                            val lottoRoundsAdapter = ArrayAdapter(activity, R.layout.spinner_lotto, roundArrayList)
                            view.lotto_spinner_rounds.adapter = lottoRoundsAdapter
                            view.lotto_spinner_rounds.setSelection(0)
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(activity, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        retrieveRealLotto(view, view.lotto_spinner_rounds.selectedItem.toString())
                    }
                })
    }

    private fun retrieveRealLotto(view: View, lottoRounds: String) {
        BaseApplication.getInstance().getRetrofitMethod().getDetailsOfLotto(lottoRounds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.LottoResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(activity, getString(R.string.request_detail_of_lotto))
                    }

                    override fun onNext(t: Model.LottoResponse) {
                        if (t.isSuccess) {
                            val lottoData: Model.DetailsOfLotto = t.detailsOfLotto[0]
                            view.lotto_text_winning_date.text = lottoData.winningDate
                            view.lotto_text_real_lotto_rounds.text = lottoRounds
                            BaseApplication.getInstance().setLottoNumberBackground(view.lotto_text_real_lotto_1, lottoData.winningNumber1)
                            BaseApplication.getInstance().setLottoNumberBackground(view.lotto_text_real_lotto_2, lottoData.winningNumber2)
                            BaseApplication.getInstance().setLottoNumberBackground(view.lotto_text_real_lotto_3, lottoData.winningNumber3)
                            BaseApplication.getInstance().setLottoNumberBackground(view.lotto_text_real_lotto_4, lottoData.winningNumber4)
                            BaseApplication.getInstance().setLottoNumberBackground(view.lotto_text_real_lotto_5, lottoData.winningNumber5)
                            BaseApplication.getInstance().setLottoNumberBackground(view.lotto_text_real_lotto_6, lottoData.winningNumber6)
                            BaseApplication.getInstance().setLottoNumberBackground(view.lotto_text_real_lotto_bonus, lottoData.bonusNumber)

                            view.lotto_recycler_view_prize.layoutManager = LinearLayoutManager(activity)
                            view.lotto_recycler_view_prize.adapter = LottoAdapter(lottoData)
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