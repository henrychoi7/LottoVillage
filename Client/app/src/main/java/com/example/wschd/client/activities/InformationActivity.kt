package com.example.wschd.client.activities

import android.os.Bundle
import android.view.View
import com.example.wschd.client.R
import com.example.wschd.client.base.BaseActivity
import com.yarolegovich.lovelydialog.LovelyStandardDialog
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_information.*


class InformationActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        setSupportActionBar(information_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }

    fun customOnClick(view: View) {
        when (view.id) {
            R.id.information_button_update -> {
                LovelyStandardDialog(this)
                        .setTopColorRes(R.color.colorPrimary)
                        .setButtonsColorRes(R.color.colorPrimaryDark)
                        .setIcon(R.drawable.ic_warning_24dp)
                        .setTitle(getString(R.string.send_certified_number))
                        .setMessage(getString(R.string.request_permission_certified_number))
                        .setPositiveButton(R.string.okay, {})
                        .setNegativeButton(R.string.cancel, null)
                        .show()
            }
            R.id.information_button_delete -> {
                LovelyStandardDialog(this)
                        .setTopColorRes(R.color.colorPrimary)
                        .setButtonsColorRes(R.color.colorPrimaryDark)
                        .setIcon(R.drawable.ic_warning_24dp)
                        .setTitle(getString(R.string.send_certified_number))
                        .setMessage(getString(R.string.request_permission_certified_number))
                        .setPositiveButton(R.string.okay, {})
                        .setNegativeButton(R.string.cancel, null)
                        .show()
            }
        }
    }
}