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
/**
 * Created by sugon on 2017-12-03.
 */
class SettingActivity :BaseActivity() {
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

    fun customOnClick(view : View){
        when(view.id){
            R.id.setting_algorithm_select->startActivity(Intent(applicationContext, SelectAlgorithmActivity::class.java))
            R.id.setting_logout->{
                logout()
                true
            }
            R.id.setting_modified_information->startActivity(Intent(applicationContext,InformationActivity::class.java))
            R.id.setting_drop_out->{
                dropout()
                true
            }
            R.id.setting_question->startActivity(Intent(applicationContext,QuestionActivity::class.java))
            R.id.setting_security_information_role->startActivity(Intent(applicationContext, SecurityInformationActivity::class.java))
            R.id.setting_opensource_license->startActivity(Intent(applicationContext, OpenLicenseActivity::class.java))
        }
    }

    private fun logout() {
        progressOn(getString(R.string.send_to_request_logout))

        val sharedPreferencesEditor = mSharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(BaseApplication.AUTO_LOGIN, false)
        sharedPreferencesEditor.putStringSet(BaseApplication.X_ACCESS_TOKEN, null)
        sharedPreferencesEditor.apply()

        progressOff()

        Toast.makeText(applicationContext, getString(R.string.complete_to_logout), Toast.LENGTH_SHORT).show()
        startActivity(Intent(applicationContext, SplashActivity::class.java))
        finish()
    }

    private fun dropout(){
        val jsonObject = JSONObject()
        jsonObject.put("phone_number", "010-1234-5619")

        BaseApplication.getInstance().getRetrofitMethod().postDeleteUser(RequestBody.create(BaseApplication.MEDIA_TYPE_JSON, jsonObject.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Model.DefaultResponse> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                        progressOn(getString(R.string.delete_user))
                    }

                    override fun onNext(t: Model.DefaultResponse) {
                        if (t.isSuccess) {
//                            logout()
                            startActivity(Intent(applicationContext, SplashActivity::class.java))
                            finish()

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