package com.example.mydoctor

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import changeColorOfTheStatusBar
import com.example.mydoctor.data.AppDatabase
import com.example.mydoctor.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(navDestinationChangedListener)
    }

    private val navDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homePage) {changeColorOfTheStatusBar(this, Color.LTGRAY)}
            if(destination.id == R.id.loginPage) changeColorOfTheStatusBar(this,Color.rgb(120,81,169))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        }catch (e:NullPointerException){
            Log.d(TAG, "onCreate: e")
        }
        setContentView(binding.root)

//        WindowCompat.setDecorFitsSystemWindows(window, false)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    override fun onStop() {
        super.onStop()
        navController.removeOnDestinationChangedListener(navDestinationChangedListener)
    }
    companion object{
        private const val TAG = "MainActivity"
    }
}