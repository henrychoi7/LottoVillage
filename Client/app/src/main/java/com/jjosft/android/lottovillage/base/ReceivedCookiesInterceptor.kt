package com.jjosft.android.lottovillage.base

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by JJSOFT-DESKTOP on 2017-08-17.
 */
class ReceivedCookiesInterceptor(context: Context) : Interceptor {
    private val mSharedPreferences: SharedPreferences = context.getSharedPreferences(BaseApplication.LOTTO_VILLAGE_PREFERENCES, Context.MODE_PRIVATE)

    override fun intercept(chain: Interceptor.Chain?): Response {
        val originalResponse: Response = chain!!.proceed(chain.request())

        if (!originalResponse.headers("x-access-token").isEmpty()) {
            val tokens : HashSet<String> = HashSet()
            tokens += originalResponse.headers("x-access-token")

            val sharedPreferencesEditor: SharedPreferences.Editor = mSharedPreferences.edit()
            sharedPreferencesEditor.putStringSet(BaseApplication.X_ACCESS_TOKEN, tokens)
            sharedPreferencesEditor.apply()
        }

        return originalResponse
    }
}