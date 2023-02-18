package com.udacity.mawardy.main

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.firebase.ui.auth.AuthUI
import com.udacity.mawardy.R
import com.udacity.mawardy.authentication.AuthenticationActivity
import com.udacity.mawardy.base.BaseActivity
import com.udacity.mawardy.databinding.ActivityMainBinding
import com.udacity.mawardy.utils.DisplayManager

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layout: Int
        get() = R.layout.activity_main

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mediaPlayer: MediaPlayer

    private var isMusicEnabled = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("TAGG", sharedPreferencesRepo.isLoggedIn().toString())
        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).findNavController()

        appBarConfiguration = AppBarConfiguration.Builder(R.id.categoryFragment).build()

        setupActionBarWithNavController(navController, appBarConfiguration)

        mediaPlayer = MediaPlayer.create(this, R.raw.app_music)
        startMusic()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                sharedPreferencesRepo.setLoggedIn(false)
                sharedPreferencesRepo.saveBearerToken(null)
                DisplayManager.showConfirmationDialog(
                    this,
                    R.string.confirm_logout,
                    R.string.logout,
                    R.string.cancel
                ) { _, _ ->
                    startActivity(Intent(this@MainActivity, AuthenticationActivity::class.java))
                    finish()
                }
            }
    }

    private fun startMusic() {
        isMusicEnabled = true
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            it.start()
        }
    }

    private fun stopMusic() {
        isMusicEnabled = false
        mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(this, R.raw.app_music)
    }


    override fun onResume() {
        super.onResume()
        startMusic()
    }

    override fun onStop() {
        super.onStop()
        stopMusic()
    }
}


