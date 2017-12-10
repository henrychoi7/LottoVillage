package com.jjosft.android.lottovillage.fragments

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.activities.*
import com.jjosft.android.lottovillage.base.BaseApplication
import com.jjosft.android.lottovillage.model.Model
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.fragment_store_cafe.view.*
import kotlinx.android.synthetic.main.fragment_store_meal.view.*

/**
 * Created by JJSOFT-DESKTOP on 2017-09-03.
 */
class StoreFragment : Fragment() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_store, container, false)
        view.store_button_go_to_purchase.setOnClickListener({startActivity(Intent(activity ,PurchaseHistoryActivity::class.java))})
        view.store_button_go_to_point.setOnClickListener({startActivity(Intent(activity,PointHistoryActivity::class.java))})
        view.store_meal.setOnClickListener({startActivity(Intent(activity, StoreMealActivity::class.java))})
        view.store_cafe.setOnClickListener({startActivity(Intent(activity, StoreCafeActivity::class.java))})

        retrieveStoreMyPoint(view)
        return view
    }

    override fun onStop() {
        mCompositeDisposable.clear()
        super.onStop()
    }

    /* private fun retrieveStoreMyPoint(view: View) {
        BaseApplication.getInstance().getRetrofitMethod().getMyPoint()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.SingleIntResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(activity, getString(R.string.request_my_point))
                    }

                    override fun onNext(t: Model.SingleIntResponse) {
                        view.store_text_my_point.text = t.results.toString()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(activity, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {

                    }
                })
    } */

    private fun retrieveStoreMyPoint(view: View) {
        BaseApplication.getInstance().getRetrofitMethod().getMyPoint()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.SingleIntResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        BaseApplication.getInstance().progressOn(activity, getString(R.string.request_my_point))
                    }

                    override fun onNext(t: Model.SingleIntResponse) {
                        view.store_text_my_point.text = t.results.toString()
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