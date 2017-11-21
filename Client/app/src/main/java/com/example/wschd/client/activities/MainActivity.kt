package com.example.wschd.client.activities

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
import com.example.wschd.client.R
import com.example.wschd.client.base.BaseActivity
import com.example.wschd.client.base.BaseApplication
import com.example.wschd.client.fragments.HomeFragment
import com.example.wschd.client.fragments.LottoFragment
import com.example.wschd.client.fragments.StoreFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : BaseActivity() {
    private val mSharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(BaseApplication.LOTTO_VILLAGE_PREFERENCES, Context.MODE_PRIVATE)
    }

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

    private fun changeFragment(targetFragment : Fragment) {
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_fragment, targetFragment)
        fragmentTransaction.commit()
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)
        main_bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //main_swipe_refresh_layout.setOnRefreshListener(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_menu_information -> {
                startActivity(Intent(applicationContext, InformationActivity::class.java))
                true
            }
            R.id.main_menu_point_usage_history -> {
                startActivity(Intent(applicationContext, PointHistoryActivity::class.java))
                true
            }
            R.id.main_menu_lotto_participation_history -> {
                startActivity(Intent(applicationContext, ParticipationHistoryActivity::class.java))
                true
            }
            R.id.main_menu_product_purchase_details -> {
                startActivity(Intent(applicationContext, PurchaseHistoryActivity::class.java))
                true
            }
            R.id.main_menu_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
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
}