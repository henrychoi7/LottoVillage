package com.jjosft.android.lottovillage.activities

import android.os.Bundle
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import io.reactivex.disposables.CompositeDisposable

class SecurityInformationActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_information)
    }
    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }
}