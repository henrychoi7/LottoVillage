package com.jjosft.android.lottovillage.activities

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.telephony.SmsManager
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
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.regex.Pattern


class RegisterActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mSharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(BaseApplication.LOTTO_VILLAGE_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(register_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val certifiedNumber = intent.getStringExtra("certified_number")
        register_edit_confirm_certified.setText(certifiedNumber)
    }

    /**
     * 문자 인증 서비스 보내기 및 SharedPreferences 에 저장 해 놓은 인증번호를 바탕으로 매칭되는지 확인 하는 기능이 있음
     * SmsBroadcastReceiver 를 통해 문자를 받았을시 자동으로 화면에 뿌려주는 서비스 구현
     */
    fun customOnClick(view: View) {
        when (view.id) {
            R.id.register_button_certified -> {
                LovelyStandardDialog(this)
                        .setTopColorRes(R.color.colorPrimary)
                        .setButtonsColorRes(R.color.colorPrimaryDark)
                        .setIcon(R.drawable.ic_warning_24dp)
                        .setTitle(getString(R.string.send_certified_number))
                        .setMessage(getString(R.string.request_permission_certified_number))
                        .setPositiveButton(R.string.okay, {
                            if (super.checkPermissionAndSetDisplayData(Manifest.permission.SEND_SMS)) {
                                if (super.checkPermissionAndSetDisplayData(Manifest.permission.READ_PHONE_STATE)) {
                                    if (super.checkPermissionAndSetDisplayData(Manifest.permission.RECEIVE_SMS)) {
                                        sendSms()
                                    }
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show()
            }
            R.id.register_button_confirm_certified -> {
                validateCertified()
            }
            R.id.register_checked_text_service -> {
                register_checked_text_service.isChecked = !register_checked_text_service.isChecked
            }
            R.id.register_checked_text_personal_information -> {
                register_checked_text_personal_information.isChecked = !register_checked_text_personal_information.isChecked
            }
            R.id.register_button_register -> {
                validateRegister()
            }
        }
    }

    /**
     * 인증 번호 검토
     */
    private fun validateCertified() {
        val certifiedNumber = mSharedPreferences.getString(BaseApplication.CERTIFIED_NUMBER, null)
        if (certifiedNumber == null || register_edit_confirm_certified.text.toString() != certifiedNumber) {
            register_edit_confirm_certified.requestFocus()
            register_edit_confirm_certified.error = getString(R.string.unmatched_certified_number)
        } else {
            register_edit_phone_number.isEnabled = false
            register_button_certified.isEnabled = false
            register_edit_confirm_certified.isEnabled = false
            register_button_confirm_certified.isEnabled = false
            register_button_register.isEnabled = true
            Toast.makeText(applicationContext, getString(R.string.matched_certified_number), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 문자 보내기
     */
    private fun sendSms() {
        val smsManager = SmsManager.getDefault()
        val sentIntent = PendingIntent.getBroadcast(this, 0, Intent("SMS_SENT_ACTION"), 0)
        val deliveredIntent = PendingIntent.getBroadcast(this, 0, Intent("SMS_DELIVERED_ACTION"), 0)

        val editTextPhoneNumberString = register_edit_phone_number.text.toString()
        val isValidate = validatePhoneNumber(editTextPhoneNumberString)

        if (isValidate) {
            val certifiedNumber = randomRange(10000, 99999).toString()
            val sharedPreferencesEditor = mSharedPreferences.edit()
            sharedPreferencesEditor.putString(BaseApplication.CERTIFIED_NUMBER, certifiedNumber)
            sharedPreferencesEditor.apply()
            smsManager.sendTextMessage(editTextPhoneNumberString.replace("-", ""), null, getString(R.string.message_front) + certifiedNumber + getString(R.string.message_back), sentIntent, deliveredIntent)
        } else {
            register_edit_phone_number.requestFocus()
            register_edit_phone_number.error = getString(R.string.unmatched_phone_number)
        }
    }

    /**
     * 문자 인증번호에 쓰일 번호 랜덤으로 생성
     */
    private fun randomRange(from: Int, to: Int): Int {
        return ((Math.random() * (to - from + 1)) + from).toInt()
    }

    /**
     * 핸드폰 번호가 올바른지 확인하는 정규식 validation 함수
     */
    private fun validatePhoneNumber(phoneNumberString: String): Boolean = Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}\$", phoneNumberString)

    /**
     * 회원가입을 위한 validation 및 네트워크 통신 함수
     */
    private fun validateRegister() {
        if (isDeniedValueValidation(register_edit_name)) return
        if (isDeniedValueValidation(register_edit_password)) return
        if (isDeniedValueValidation(register_edit_password_confirm)) return
        if (isDeniedValueValidation(register_edit_phone_number)) return
        if (isDeniedValueValidation(register_edit_confirm_certified)) return
        if (!register_checked_text_service.isChecked) {
            register_checked_text_service.error = getString(R.string.service_terms_conditions)
            Toast.makeText(applicationContext, getString(R.string.service_terms_conditions), Toast.LENGTH_SHORT).show()
            return
        }
        if (!register_checked_text_personal_information.isChecked) {
            register_checked_text_personal_information.error = getString(R.string.personal_information_terms_conditions)
            Toast.makeText(applicationContext, getString(R.string.personal_information_terms_conditions), Toast.LENGTH_SHORT).show()
            return
        }

        val jsonObject = JSONObject()
        jsonObject.put("name", register_edit_name.text.toString())
        jsonObject.put("password", register_edit_password.text.toString())
        jsonObject.put("password_confirm", register_edit_password_confirm.text.toString())
        jsonObject.put("phone_number", register_edit_phone_number.text.toString())

        BaseApplication.getInstance().getRetrofitMethod().postRegister(RequestBody.create(BaseApplication.MEDIA_TYPE_JSON, jsonObject.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.DefaultResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn(getString(R.string.send_to_request_register))
                    }

                    override fun onNext(t: Model.DefaultResponse) {
                        if (t.isSuccess) {
                            Toast.makeText(applicationContext, getString(R.string.complete_to_register), Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
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

    /**
     * 기본 validation 함수
     */
    private fun isDeniedValueValidation(targetEditText: EditText): Boolean {
        val targetValueString = targetEditText.text.toString()
        if (targetValueString.isEmpty()) {
            targetEditText.requestFocus()
            targetEditText.error = getString(R.string.empty_value)
            return true
        }

        when (targetEditText.id) {
            R.id.register_edit_name -> {
                if (targetValueString.length !in 3..10) {
                    targetEditText.requestFocus()
                    targetEditText.error = "3" + getString(R.string.deny_value_length)
                    return true
                }
            }
            R.id.register_edit_phone_number -> {
                if (!validatePhoneNumber(targetValueString)) {
                    targetEditText.requestFocus()
                    targetEditText.error = getString(R.string.unmatched_phone_number)
                    return true
                }
            }
            R.id.register_edit_confirm_certified -> {
                if (targetValueString.length != 5) {
                    targetEditText.requestFocus()
                    targetEditText.error = getString(R.string.unmatched_certified_number)
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
                } else if (register_edit_password.text.toString() != register_edit_password_confirm.text.toString()) {
                    targetEditText.requestFocus()
                    targetEditText.error = getString(R.string.unmatched_password)
                    return true
                }
            }
        }

        return false
    }
}
