package com.jjosft.android.lottovillage.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.adapters.ProductListAdapter
import com.jjosft.android.lottovillage.base.BaseActivity
import com.jjosft.android.lottovillage.base.BaseApplication
import com.jjosft.android.lottovillage.model.Model
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_store_detail.*
import kotlinx.android.synthetic.main.content_store_detail.*

class StoreDetailActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.fragment_store_detail)
        setContentView(R.layout.activity_store_detail)

        setSupportActionBar(store_detail_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val intent: Intent = intent

        if (intent.getStringExtra("productCategory") != null) {
            retrieveProductList(intent.getStringExtra("productCategory"))
        } else {
            Toast.makeText(applicationContext, "상품이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }

    /**
     * 상품의 내역을 Intent 로 받은 카테고리에 따라 불러오는 네트워크 통신 함수
     */
    private fun retrieveProductList(productCategory: String) {
        BaseApplication.getInstance().getRetrofitMethod().getRetrieveProductList(productCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.ProductListResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn("포인트 내역을 불러오는 중 입니다")
                    }

                    override fun onNext(t: Model.ProductListResponse) {
                        if (t.isSuccess) {
                            val productListAdapter = ProductListAdapter(this@StoreDetailActivity, t.detailsOfProductList)
                            store_detail_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
                            store_detail_recycler_view.adapter = productListAdapter
                        } else {
                            Toast.makeText(applicationContext, "상품이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(applicationContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                        //progressOff()
                        mCompositeDisposable.clear()
                        retrieveStoreMyPoint()
                    }

                })
    }

    /**
     * 유저의 포인트를 가져오기 위한 네트워크 통신 함수
     */
    private fun retrieveStoreMyPoint() {
        BaseApplication.getInstance().getRetrofitMethod().getMyPoint()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.SingleIntResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn(getString(R.string.request_my_point))
                    }

                    override fun onNext(t: Model.SingleIntResponse) {
                        store_detail_tv_my_point.text = t.results.toString()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(applicationContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
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