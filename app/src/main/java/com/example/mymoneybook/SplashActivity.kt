package com.example.mymoneybook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemClock.sleep(2000)
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}