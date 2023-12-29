package com.hanaahany.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.transition.Visibility
import com.airbnb.lottie.LottieAnimationView
import com.hanaahany.task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var controller: NavController
    private lateinit var navHostFragment: NavHostFragment
    private val SPLASH_TIME_OUT: Long = 3000
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.hide()
        Handler().postDelayed({
            navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragment_container) as NavHostFragment
            controller = navHostFragment.navController
            binding.fragmentContainer.visibility=View.VISIBLE
            NavigationUI.setupActionBarWithNavController(this,controller)
            binding.splash.visibility=View.GONE
            supportActionBar?.show()

        }, SPLASH_TIME_OUT)
    }
    override fun onSupportNavigateUp(): Boolean {
//        navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.fragment_container) as NavHostFragment
//        controller = navHostFragment.navController
        return controller.navigateUp() || super.onSupportNavigateUp()
    }
}