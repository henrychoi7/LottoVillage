package com.jjosft.android.lottovillage.activities

import android.os.Bundle
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by dahyun on 2017-12-05.
 */
class OpenLicenseActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opensource_license)
    }
    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }
}

