package com.jjosft.android.lottovillage.activities

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_store_meal.*

/**
 * Created by dahyun on 2017-12-05.
 */
class StoreMealActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_store_meal)
    }
    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }
    fun Activity.replaceContentFragment(@IdRes frameId: Int, fragment: android.app.Fragment) {
        fragmentManager.beginTransaction().replace(frameId, fragment).commit()
    }
}