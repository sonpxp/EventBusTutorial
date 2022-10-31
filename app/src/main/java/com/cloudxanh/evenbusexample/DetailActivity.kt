package com.cloudxanh.evenbusexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cloudxanh.evenbusexample.databinding.ActivityDetailBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            EventBus.getDefault().post(MessageEvent("ahihi"))
        }
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent?) {
        // Do something
        if (event != null) {
            Toast.makeText(this, "${event.name}", Toast.LENGTH_SHORT).show()
            Log.e("msg", event.name.toString())
            binding.tvShow.text = event.name
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent) {
        // Do something
        if (event != null) {
            Toast.makeText(this, "${event.name}", Toast.LENGTH_SHORT).show()
            Log.e("msg", event.name.toString())
            binding.tvShow.text = event.name
        }
    }
}