package com.jjosft.android.lottovillage.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjosft.android.lottovillage.R
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by JJSOFT-DESKTOP on 2017-09-03.
 */
class StoreFragment : Fragment() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //val view: View = inflater.inflate(R.layout.fragment_store, container, false)

        return inflater.inflate(R.layout.fragment_store, container, false)
    }
    //리사이클 뷰 써서 리스트 불러오기

    override fun onStop() {
        mCompositeDisposable.clear()
        super.onStop()
    }

    private fun retrieveProduct(view: View) {

    }
}