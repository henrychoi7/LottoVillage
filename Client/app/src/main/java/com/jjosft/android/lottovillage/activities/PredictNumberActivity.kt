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

/**
 * Created by dahyun on 2017-12-08.
 */
class PredictNumberActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithms)
        setSupportActionBar(algorithms_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        retrievePredictionAlgorithm()
    }
    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }

    private fun retrievePredictionAlgorithm() {
        BaseApplication.getInstance().getRetrofitMethod().getPredictionAlgorithm("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.PredictionResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn("로또 참여 내역을 불러오는 중 입니다")
                    }

                    override fun onNext(t: Model.PredictionResponse) {
                        if (t.isSuccess) {
                            /*String abc = "asdf";
                            val abc : String = "asdf";*/
                            val detailsOfPrediction : Model.DetailsOfPrediction = t.detailsOfPrediction
                            lotto_prediction_1.text = detailsOfPrediction.predictionNumber1.toString()
                            lotto_prediction_2.text = detailsOfPrediction.predictionNumber2.toString()
                            lotto_prediction_3.text = detailsOfPrediction.predictionNumber3.toString()
                            lotto_prediction_4.text = detailsOfPrediction.predictionNumber4.toString()
                            lotto_prediction_5.text = detailsOfPrediction.predictionNumber5.toString()
                            lotto_prediction_6.text = detailsOfPrediction.predictionNumber6.toString()
                            lotto_prediction_bonus.text = detailsOfPrediction.bonusNumber.toString()
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