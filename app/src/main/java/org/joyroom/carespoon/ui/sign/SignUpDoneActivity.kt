package org.joyroom.carespoon.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.joyroom.carespoon.R
import org.joyroom.carespoon.databinding.ActivitySignUpDoneBinding
import org.joyroom.carespoon.ui.MainActivity

class SignUpDoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpDoneBinding
    // 아래 4개 서버 통신할 때 넘기기
    private var isFemale = true
    private lateinit var userBirth: String
    private lateinit var userHeight: String
    private lateinit var userWeight: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserInfo()
        setIntent()
    }

    private fun getUserInfo(){
        userBirth = binding.etBirth.text.toString()
        userHeight = binding.etHeight.text.toString()
        userWeight = binding.etWeight.text.toString()
        initClickListener()
    }

    private fun initClickListener() {
        binding.clFemale.setOnClickListener{
            binding.clFemale.isSelected = true
            binding.clMale.isSelected = false
            isFemale = true
        }
        binding.clMale.setOnClickListener{
            binding.clFemale.isSelected = false
            binding.clMale.isSelected = true
            isFemale = false
        }
    }

    private fun setIntent(){
        binding.clSignup.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}