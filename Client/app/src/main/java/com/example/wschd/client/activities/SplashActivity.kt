package com.example.wschd.client.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.example.wschd.client.R
import com.example.wschd.client.base.BaseActivity
import com.example.wschd.client.base.BaseApplication

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences: SharedPreferences = getSharedPreferences(BaseApplication.LOTTO_VILLAGE_PREFERENCES, Context.MODE_PRIVATE)
        // SharedPreferences 를 통하여 자동로그인 여부 확인
        if (sharedPreferences.getBoolean(BaseApplication.AUTO_LOGIN, false)) startActivity(Intent(applicationContext, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
    }

    fun customOnClick(view: View) {
        when (view.id) {
            R.id.splash_button_go_to_register -> startActivity(Intent(applicationContext, RegisterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
            R.id.splash_button_go_to_login -> startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }
}