package com.cloudxanh.evenbusexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cloudxanh.evenbusexample.databinding.ActivityMainBinding
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.frame.id, BlankFragment())
            .commit()

        var i = 1

        binding.btnStart.setOnClickListener {
            i++
            EventBus.getDefault().post(MessageEvent("Hello everyone! -> $i"))
        }

        binding.btnDetail.setOnClickListener {
            // EventBus.getDefault().post(MessageEvent("Hello Kitty! -> $i"))
            // To send data between 2 activities need to use postSticky
            EventBus.getDefault().postSticky(MessageEvent("Hello Kitty! -> $i"))
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }
}