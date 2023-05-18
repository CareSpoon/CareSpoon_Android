package org.joyroom.carespoon.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.joyroom.carespoon.R
import org.joyroom.carespoon.databinding.ActivitySignUpBinding
import org.joyroom.carespoon.ui.statistics.StatisticsActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private var isGeneral = true // 서버 통신할 때 넘기기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickListener()
        setIntent()
    }

    private fun initClickListener() {
        binding.clGeneral.setOnClickListener{
            binding.clGeneral.isSelected = true
            binding.clViewer.isSelected = false
            isGeneral = true
        }
        binding.clViewer.setOnClickListener{
            binding.clGeneral.isSelected = false
            binding.clViewer.isSelected = true
            isGeneral = false
        }
    }

    private fun setIntent(){
        binding.clNext.setOnClickListener {
            startActivity(Intent(this, SignUpDoneActivity::class.java))
            finish()
        }
    }
}