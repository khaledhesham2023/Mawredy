package com.udacity.mawardy.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.udacity.mawardy.R
import com.udacity.mawardy.base.BaseActivity
import com.udacity.mawardy.databinding.ActivitySplashBinding
import com.udacity.mawardy.authentication.AuthenticationActivity
import com.udacity.mawardy.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val layout: Int
        get() = R.layout.activity_splash

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler.createAsync(Looper.getMainLooper()).postDelayed(Runnable {
            Log.i("TAGG", sharedPreferencesRepo.isLoggedIn().toString())
            if (sharedPreferencesRepo.isLoggedIn()) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, AuthenticationActivity::class.java))
                finish()
            }
        }, 3000L)


        viewBinding.lifecycleOwner = this

    }

}