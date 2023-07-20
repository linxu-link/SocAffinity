package com.wj.socaffinity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wj.socaffinity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start1()
        start2()
    }

    private fun start1() {
        ThreadAffinity.threadToCore(0) {
            Thread {
                var time = System.currentTimeMillis()
                var sum = 0L
                for (i in 0..1000000000L) {
                    sum += i
                }
                time = System.currentTimeMillis() - time
                Log.e("SOC_", "start1: $time")
                runOnUiThread {
                    binding.sampleText.text = time.toString()
                }
            }.start()
        }
    }

    private fun start2() {
        ThreadAffinity.threadToCore(1) {
            Thread {
                var time = System.currentTimeMillis()
                var sum = 0L
                for (i in 0..1000000000L) {
                    sum += i
                }
                time = System.currentTimeMillis() - time
                Log.e("SOC_", "start2: $time")
                runOnUiThread {
                    binding.sampleText.text = time.toString()
                }
            }.start()
        }
    }
}