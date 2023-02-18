package com.udacity.mawardy.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.udacity.mawardy.R
import com.udacity.mawardy.base.BaseActivity
import com.udacity.mawardy.databinding.ActivityAuthenticationBinding
import com.udacity.mawardy.main.MainActivity
import com.udacity.mawardy.utils.Constants

class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {
    override val layout: Int
        get() = R.layout.activity_authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val providers = mutableListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build()
        )

        val signInIntent = AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false)
                .setTheme(R.style.Theme_Mawardy).setAvailableProviders(providers)
                .setAuthMethodPickerLayout(
                    AuthMethodPickerLayout.Builder(R.layout.auth_layout)
                        .setEmailButtonId(R.id.mawardy_button).setGoogleButtonId(R.id.google_button)
                        .setFacebookButtonId(R.id.facebook_button).build()
                ).build()

            startActivityForResult(signInIntent, Constants.REQUEST_CODE)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == RESULT_OK) {
                Toast.makeText(
                    this@AuthenticationActivity,
                    "Welcome, ${FirebaseAuth.getInstance().currentUser!!.displayName}",
                    Toast.LENGTH_SHORT
                ).show()
                sharedPreferencesRepo.setLoggedIn(true)
                startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    this@AuthenticationActivity,
                    "Error: ${response?.error?.errorCode}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}