package com.jjosft.android.lottovillage.activities

import android.annotation.SuppressLint
import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.base.BaseActivity
import com.jjosft.android.lottovillage.base.BaseApplication
import com.jjosft.android.lottovillage.fragments.HomeFragment
import com.jjosft.android.lottovillage.fragments.LottoFragment
import com.jjosft.android.lottovillage.fragments.StoreFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                main_text_message.setText(R.string.title_home)
                changeFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_lotto -> {
                main_text_message.setText(R.string.title_lotto)
                changeFragment(LottoFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_store -> {
                main_text_message.setText(R.string.title_store)
                changeFragment(StoreFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragment(targetFragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_fragment, targetFragment)
        fragmentTransaction.commit()
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)
        main_bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu_white, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
        //설정리스트로 넘어가는 이벤트
            R.id.setting_white -> {
                startActivity(Intent(applicationContext, SettingActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}