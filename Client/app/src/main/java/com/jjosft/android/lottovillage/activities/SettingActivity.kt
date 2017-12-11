package com.jjosft.android.lottovillage.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import com.jjosft.android.lottovillage.base.BaseApplication
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_setting.*
import com.jjosft.android.lottovillage.R.layout.activity_information
import com.jjosft.android.lottovillage.model.Model
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_login.*
import kotlinx.android.synthetic.main.content_register.*
import okhttp3.RequestBody
import org.json.JSONObject

class SettingActivity : BaseActivity() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mSharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(BaseApplication.LOTTO_VILLAGE_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(setting_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStop() {
        super.onStop()
        mCompositeDisposable.clear()
    }

    fun customOnClick(view: View) {
        when (view.id) {
            R.id.setting_logout -> logout()
            R.id.setting_modified_information -> startActivity(Intent(applicationContext, InformationActivity::class.java))
            R.id.setting_drop_out -> dropout()
            R.id.setting_question -> {
                val uri = Uri.parse("mailto:jwh1269@naver.com")
                val mailIntent = Intent(Intent.ACTION_SENDTO, uri)
                startActivity(mailIntent)
            }
            R.id.setting_security_information_role -> startActivity(Intent(applicationContext, SecurityInformationActivity::class.java))
            R.id.setting_opensource_license -> startActivity(Intent(applicationContext, OpenLicenseActivity::class.java))
        }
    }

    /**
     * SharedPreference 에 자동로그인 및 토큰 정보를 삭제하는 로그아웃 함수
     */
    private fun logout() {
        progressOn(getString(R.string.send_to_request_logout))

        val sharedPreferencesEditor = mSharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(BaseApplication.AUTO_LOGIN, false)
        sharedPreferencesEditor.putStringSet(BaseApplication.X_ACCESS_TOKEN, null)
        sharedPreferencesEditor.apply()

        progressOff()

        //Toast.makeText(applicationContext, getString(R.string.complete_to_logout), Toast.LENGTH_SHORT).show()
        startActivity(Intent(applicationContext, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        finish()
    }

    /**
     * SharedPreferences 에 저장되어있는 계정을 바탕으로
     * 계정 탈퇴하는 네트워크 통신 함수
     */
    private fun dropout() {
        BaseApplication.getInstance().getRetrofitMethod().postDeleteUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.DefaultResponse> {
                    private var isSuccess: Boolean = false

                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn(getString(R.string.delete_user))
                    }

                    override fun onNext(t: Model.DefaultResponse) {
                        isSuccess = t.isSuccess
                        if (!isSuccess) {
                            Toast.makeText(applicationContext, t.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(applicationContext, "실패 ${e.message}", Toast.LENGTH_SHORT).show()
                        progressOff()
                    }

                    override fun onComplete() {
                        progressOff()
                        if (isSuccess) {
                            Toast.makeText(applicationContext, getString(R.string.delete_user), Toast.LENGTH_SHORT).show()
                            logout()
                        }
                    }
                })

    }
}