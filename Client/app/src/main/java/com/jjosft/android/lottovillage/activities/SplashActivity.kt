package com.jjosft.android.lottovillage.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.os.Handler
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import com.jjosft.android.lottovillage.base.BaseApplication


class SplashActivity : BaseActivity() {

    private val Splash_screen = 2000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, Splash_screen.toLong())
   }


    fun customOnClick(view: View) {
        when (view.id) {
            R.id.splash_button_go_to_register -> startActivity(Intent(applicationContext, RegisterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
        }
    }
}
