package com.storm.kotlindemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.storm.kotlindemo.R



class AboutActivity : AppCompatActivity() {


    companion object {
        val TAG : String = "AboutActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }
}
