package com.jjosft.android.lottovillage.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import com.jjosft.android.lottovillage.base.BaseApplication
import com.jjosft.android.lottovillage.model.Model
import com.yarolegovich.lovelydialog.LovelyStandardDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.android.synthetic.main.content_information.*
import kotlinx.android.synthetic.main.content_register.*
import okhttp3.RequestBody
import org.json.JSONObject

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
                validateUpdate()
            }
            R.id.information_button_cancel ->startActivity(Intent(applicationContext,MainActivity::class.java))
        }
    }

    private fun validateUpdate() {
        if (isDeniedValueValidation(information_edit_name)) return
        if (isDeniedValueValidation(information_edit_password)) return
        if (isDeniedValueValidation(information_edit_new_password)) return
        if (isDeniedValueValidation(information_edit_password_confirm)) return
        //if (isDeniedValueValidation(register_edit_confirm_certified)) return

        val jsonObject = JSONObject()
        jsonObject.put("name", information_edit_name.text.toString())
        jsonObject.put("password", information_edit_new_password.text.toString())
        jsonObject.put("password_confirm", information_edit_password_confirm.text.toString())
        //jsonObject.put("phone_number", register_edit_phone_number.text.toString())

        BaseApplication.getInstance().getRetrofitMethod().postRegister(RequestBody.create(BaseApplication.MEDIA_TYPE_JSON, jsonObject.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.DefaultResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn(getString(R.string.send_to_request_update))
                    }

                    override fun onNext(t: Model.DefaultResponse) {
                        if (t.isSuccess) {
                            Toast.makeText(applicationContext, getString(R.string.complete_to_update), Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, LoginActivity::class.java)
                                    .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // 기존에 쌓여있던 스택을 모두 없앤다.
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) // 새로 생성한 엑티비티가 root 가 된다.
                        } else {
                            Toast.makeText(applicationContext, t.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(applicationContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        progressOff()
                    }

                    override fun onComplete() {
                        progressOff()
                    }
                })
    }

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
                } else if(information_edit_password.text.toString() == information_edit_new_password.text.toString()){
                    targetEditText.requestFocus()
                    targetEditText.error=getString(R.string.unmatched_last_password)
                    return true
                }
            }
        }
        return false
    }
}