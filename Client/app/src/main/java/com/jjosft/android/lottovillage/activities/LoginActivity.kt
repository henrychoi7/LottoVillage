package com.jjosft.android.lottovillage.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.regex.Pattern


class LoginActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mSharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(BaseApplication.LOTTO_VILLAGE_PREFERENCES, Context.MODE_PRIVATE)
    }
    private var mIsCheckedAutoLogin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /**
         * SharedPreferences 를 통하여 자동로그인 여부 확인
         */
        mIsCheckedAutoLogin = mSharedPreferences.getBoolean(BaseApplication.AUTO_LOGIN, false)
        login_check_auto_login.isChecked = mIsCheckedAutoLogin

        if (mIsCheckedAutoLogin) {
            validateLogin()
        }
    }

    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }

    fun customOnClick(view: View) {
        when (view.id) {
            R.id.login_button_login -> validateLogin()
            R.id.splash_button_go_to_register -> startActivity(Intent(applicationContext, RegisterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
        }
    }

    /**
     * 입력된 전화번호와 비밀번호를 jsonObject 에 담아서 네트워크 통신하는 함수
     * 테스트를 용이하게 하기위해 validation 소스는 짜고 주석처리를 해놓음
     */
    private fun validateLogin() {
        //val tokensStringSet: HashSet<String> = mSharedPreferences.getStringSet(BaseApplication.X_ACCESS_TOKEN, HashSet()) as HashSet<String>
        val jsonObject = JSONObject()
        val phoneNumberString = login_edit_phone_number.text.toString()
        val passwordString = login_edit_password.text.toString()

        /*if (phoneNumberString.isEmpty().or(!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}\$", phoneNumberString))) {
            login_edit_phone_number.requestFocus()
            login_edit_phone_number.error = getString(R.string.unmatched_phone_number)
            return
        }

        if (passwordString.isEmpty().or(passwordString.length !in 6..10)) {
            login_edit_password.requestFocus()
            login_edit_password.error = "6" + getString(R.string.deny_value_length)
            return
        }*/

        jsonObject.put("phone_number", phoneNumberString)
        jsonObject.put("password", passwordString)

        BaseApplication.getInstance().getRetrofitMethod().postLogin(RequestBody.create(BaseApplication.MEDIA_TYPE_JSON, jsonObject.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.DefaultResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn(getString(R.string.send_to_request_login))
                    }

                    override fun onNext(t: Model.DefaultResponse) {
                        if (t.isSuccess) {
                            if (!mIsCheckedAutoLogin.and(login_check_auto_login.isChecked)) {
                                val sharedPreferencesEditor = mSharedPreferences.edit()
                                sharedPreferencesEditor.putBoolean(BaseApplication.AUTO_LOGIN, login_check_auto_login.isChecked)
                                sharedPreferencesEditor.apply()
                            }
                            startActivity(Intent(applicationContext, MainActivity::class.java)
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
}
