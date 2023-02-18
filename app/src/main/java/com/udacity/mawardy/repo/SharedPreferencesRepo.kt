package com.udacity.mawardy.repo

import android.content.Context
import com.udacity.mawardy.utils.Constants

class SharedPreferencesRepo(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE)

    fun setLoggedIn(isLoggedIn: Boolean) =
        sharedPreferences.edit().putBoolean(Constants.IS_LOGGED_IN, isLoggedIn).apply()

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(Constants.IS_LOGGED_IN, false)

    fun saveBearerToken(uId:String?) = sharedPreferences.edit().putString(Constants.BEARER_TOKEN,uId).apply()

    fun getBearerToken():String? = sharedPreferences.getString(Constants.BEARER_TOKEN,"")

}