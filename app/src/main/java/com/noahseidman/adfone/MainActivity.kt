package com.noahseidman.adfone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.noahseidman.adfone.fragments.AuthFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            AuthFragment.show(this, getString(R.string.client_id), getString(R.string.redirect_url))
        }
    }
}
