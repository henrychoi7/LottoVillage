package com.jjosft.android.lottovillage.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import com.jjosft.android.lottovillage.base.BaseApplication
import com.jjosft.android.lottovillage.model.Model
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.android.synthetic.main.content_information.*
import okhttp3.RequestBody
import org.json.JSONObject

class InformationActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mSharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(BaseApplication.LOTTO_VILLAGE_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        setSupportActionBar(information_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        retrieveUserInfo()
    }

    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }

    fun customOnClick(view: View) {
        when (view.id) {
            R.id.information_button_update -> {
                validateUpdate()
            }
            R.id.information_button_cancel -> startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    /**
     * 입력된 유저정보를 업데이트하기 전 validation 을 거친 후 네트워크 통신하는 함수
     */
    private fun validateUpdate() {
        if (isDeniedValueValidation(information_edit_name)) return
        if (isDeniedValueValidation(information_edit_password)) return
        if (isDeniedValueValidation(information_edit_new_password)) return
        if (isDeniedValueValidation(information_edit_password_confirm)) return

        val jsonObject = JSONObject()
        jsonObject.put("name", information_edit_name.text.toString())
        jsonObject.put("password", information_edit_new_password.text.toString())
        jsonObject.put("password_confirm", information_edit_password_confirm.text.toString())

        BaseApplication.getInstance().getRetrofitMethod().postUpdate(RequestBody.create(BaseApplication.MEDIA_TYPE_JSON, jsonObject.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.DefaultResponse> {
                    private var isSuccess = false

                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn(getString(R.string.send_to_request_update))
                    }

                    override fun onNext(t: Model.DefaultResponse) {
                        isSuccess = t.isSuccess
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(applicationContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        progressOff()
                    }

                    override fun onComplete() {
                        progressOff()
                        if (isSuccess) {
                            val sharedPreferencesEditor = mSharedPreferences.edit()
                            sharedPreferencesEditor.putBoolean(BaseApplication.AUTO_LOGIN, false)
                            sharedPreferencesEditor.putStringSet(BaseApplication.X_ACCESS_TOKEN, null)
                            sharedPreferencesEditor.apply()

                            Toast.makeText(applicationContext, getString(R.string.complete_to_update), Toast.LENGTH_SHORT).show()
                            finish()
                        }

                    }
                })
    }

    /**
     * 기본으로 만든 기초 validation 함수
     */
    private fun isDeniedValueValidation(targetEditText: EditText): Boolean {
        val targetValueString = targetEditText.text.toString()
        if (targetValueString.isEmpty()) {
            targetEditText.requestFocus()
            targetEditText.error = getString(R.string.empty_value)
            return true
        }

        when (targetEditText.id) {
            R.id.information_edit_name -> {
                if (targetValueString.length !in 3..10) {
                    targetEditText.requestFocus()
                    targetEditText.error = "3" + getString(R.string.deny_value_length)
                    return true
                }
            }
        //or 로 둘중 하나일때 체크하려고했는데 안먹혀서 else 로 빠지도록 함.
        //R.id.register_edit_password.or(R.id.register_edit_confirm_password)
            else -> {
                if (targetValueString.length !in 6..10) {
                    targetEditText.requestFocus()
                    targetEditText.error = "6" + getString(R.string.deny_value_length)
                    return true
                } else if (information_edit_new_password.text.toString() != information_edit_password_confirm.text.toString()) {
                    targetEditText.requestFocus()
                    targetEditText.error = getString(R.string.unmatched_password)
                    return true
                } else if (information_edit_password.text.toString() == information_edit_new_password.text.toString()) {
                    targetEditText.requestFocus()
                    targetEditText.error = getString(R.string.unmatched_last_password)
                    return true
                }
            }
        }
        return false
    }

    /**
     * 유저의 이름을 불러오기위해 만든 함수
     */
    private fun retrieveUserInfo() {
        BaseApplication.getInstance().getRetrofitMethod().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.UserInfoResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                    }

                    override fun onNext(t: Model.UserInfoResponse) {
                        if (t.isSuccess) {
                            information_edit_name.setText(t.name)
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(applicationContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        BaseApplication.getInstance().progressOff()
                        mCompositeDisposable.clear()
                    }

                    override fun onComplete() {
                    }
                })
    }
}