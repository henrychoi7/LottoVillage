package com.jjosft.android.lottovillage.fragments

import android.app.Fragment
import android.content.Intent
import android.net.Uri
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

/**
 * Created by JJSOFT-DESKTOP on 2017-09-03.
 */
class StoreFragment : Fragment(), View.OnClickListener {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_store, container, false)
        view.store_button_go_to_purchase.setOnClickListener({ startActivity(Intent(activity, PurchaseHistoryActivity::class.java)) })
        view.store_button_go_to_point.setOnClickListener({ startActivity(Intent(activity, PointHistoryActivity::class.java)) })

        view.store_meal.setOnClickListener(this)
        view.store_cafe.setOnClickListener(this)

        view.store_convenience.setOnClickListener(this)
        view.store_beauty.setOnClickListener(this)

        view.store_culture.setOnClickListener(this)
        view.store_etc.setOnClickListener(this)

        retrieveStoreMyPoint(view)
        return view
    }

    override fun onClick(view: View) {
        val storeDetailActivity = Intent(activity, StoreDetailActivity::class.java)
        /**
         * 0 : store_meal           외식
         * 1 : store_coffee         커피/베이커리
         * 2 : store_convenience    편의점
         * 3 : store_beauty         뷰티
         * 4 : store_culture        문화생활
         * 5 : store_etc            기타
         */
        when (view.id) {
            R.id.store_meal -> storeDetailActivity.putExtra("productCategory", "0")
            R.id.store_cafe -> storeDetailActivity.putExtra("productCategory", "1")
            R.id.store_convenience -> storeDetailActivity.putExtra("productCategory", "2")
            R.id.store_beauty -> storeDetailActivity.putExtra("productCategory", "3")
            R.id.store_culture -> storeDetailActivity.putExtra("productCategory", "4")
            R.id.store_etc -> storeDetailActivity.putExtra("productCategory", "5")
        }

        startActivity(storeDetailActivity)
    }

    override fun onStop() {
        mCompositeDisposable.clear()
        super.onStop()
    }

    /**
     * 유저의 포인트를 가져오기 위한 네트워크 통신 함수
     */
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