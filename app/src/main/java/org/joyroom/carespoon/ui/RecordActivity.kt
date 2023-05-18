package org.joyroom.carespoon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import org.joyroom.carespoon.databinding.ActivityRecordBinding
import org.joyroom.carespoon.ui.friends.FriendsActivity

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    private var breakfastTime = ""
    private var lunchTime = ""
    private var dinnerTime = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMealRecord()
        setIntent()
    }

    private fun setMealRecord(){
        if(breakfastTime.isNotEmpty()) binding.clBreakfastNull.isVisible = false
        if(lunchTime.isNotEmpty()) binding.clLunchNull.isVisible = false
        if(dinnerTime.isNotEmpty()) binding.clDinnerNull.isVisible = false
    }

    private fun setIntent(){
        binding.clBreakfastNull.setOnClickListener{
            startActivity(Intent(this, CameraActivity::class.java))
        }
        binding.clLunchNull.setOnClickListener{
            startActivity(Intent(this, CameraActivity::class.java))
        }
        binding.clDinnerNull.setOnClickListener{
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
}