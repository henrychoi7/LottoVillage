package com.jjosft.android.lottovillage.activities

import android.os.Bundle
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * 사용한 라이브러리의 라이센스에 위반되지 않도록 하기위해 만든 엑티비티
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

