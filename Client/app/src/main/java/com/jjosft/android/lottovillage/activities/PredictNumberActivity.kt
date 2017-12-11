package com.jjosft.android.lottovillage.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.adapters.ParticipationHistoryAdapter
import com.jjosft.android.lottovillage.base.BaseActivity
import com.jjosft.android.lottovillage.base.BaseApplication
import com.jjosft.android.lottovillage.model.Model
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_algorithms.*
import kotlinx.android.synthetic.main.content_algorithms.*
import kotlinx.android.synthetic.main.content_participation_history.*

class PredictNumberActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithms)
        setSupportActionBar(algorithms_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        retrievePredictionAlgorithm1()
    }
    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }

    /**
     * 실제 로또 분석 기반 알고리즘 / 로또빌리지 내부 추천 기능
     * 위 두가지로 나뉘어져있기 때문에 알고리즘 타입을 1과 2로 두어 순서대로 뿌려주는 함수를 만듬
     */
    private fun retrievePredictionAlgorithm1() {
        BaseApplication.getInstance().getRetrofitMethod().getPredictionAlgorithm("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.PredictionResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn("실제 로또 분석 기반 예상 번호를 불러오는 중 입니다")
                    }

                    override fun onNext(t: Model.PredictionResponse) {
                        if (t.isSuccess) {
                            val detailsOfPrediction : Model.DetailsOfPrediction = t.detailsOfPrediction
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_1_1, detailsOfPrediction.predictionNumber1)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_2_1, detailsOfPrediction.predictionNumber2)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_3_1, detailsOfPrediction.predictionNumber3)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_4_1, detailsOfPrediction.predictionNumber4)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_5_1, detailsOfPrediction.predictionNumber5)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_6_1, detailsOfPrediction.predictionNumber6)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_bonus_1, detailsOfPrediction.bonusNumber)
                        } else {
                            Toast.makeText(applicationContext, t.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(applicationContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        progressOff()
                        mCompositeDisposable.clear()
                        retrievePredictionAlgorithm2()
                    }
                })
    }

    /**
     * 실제 로또 분석 기반 알고리즘 / 로또빌리지 내부 추천 기능
     * 위 두가지로 나뉘어져있기 때문에 알고리즘 타입을 1과 2로 두어 순서대로 뿌려주는 함수를 만듬
     */
    private fun retrievePredictionAlgorithm2() {
        BaseApplication.getInstance().getRetrofitMethod().getPredictionAlgorithm("2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.PredictionResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn("로또빌리지 추천 기능을 통한 예상 번호를 불러오는 중 입니다")
                    }

                    override fun onNext(t: Model.PredictionResponse) {
                        if (t.isSuccess) {
                            val detailsOfPrediction : Model.DetailsOfPrediction = t.detailsOfPrediction
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_1_2, detailsOfPrediction.predictionNumber1)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_2_2, detailsOfPrediction.predictionNumber2)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_3_2, detailsOfPrediction.predictionNumber3)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_4_2, detailsOfPrediction.predictionNumber4)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_5_2, detailsOfPrediction.predictionNumber5)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_6_2, detailsOfPrediction.predictionNumber6)
                            BaseApplication.getInstance().setLottoNumberBackground(lotto_prediction_bonus_2, detailsOfPrediction.bonusNumber)
                        } else {
                            Toast.makeText(applicationContext, t.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(applicationContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        progressOff()
                        mCompositeDisposable.clear()
                    }
                })
    }
}