package com.henryjhavierdev.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.henryjhavierdev.rickandmorty.databinding.ActivitySplashScreenBinding
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpBindings(savedInstanceState)

        val animation = AnimationUtils.loadAnimation(this,R.anim.animation_splash_screem)
        val intent = Intent(this, MainActivity::class.java)

        iv_logo.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e(localClassName, "Error ${e.message}")
                }
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
    }

    private fun setUpBindings(savedInstanceState: Bundle?){
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}