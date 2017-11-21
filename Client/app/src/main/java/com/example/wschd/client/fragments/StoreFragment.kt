package com.example.wschd.client.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wschd.client.R
import io.reactivex.disposables.CompositeDisposable


class StoreFragment : Fragment() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onStop() {
        mCompositeDisposable.clear()
        super.onStop()
    }
}