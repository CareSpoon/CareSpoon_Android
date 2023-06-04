package org.whocare.carespoon.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.whocare.carespoon.databinding.ActivityChangeInfoBinding

class ChangeInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeInfoBinding
    // 아래 4개 서버 통신할 때 넘기기
    private var isFemale = true
    private lateinit var userBirth: String
    private lateinit var userHeight: String
    private lateinit var userWeight: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setIntent()
        getUserInfo()
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
        binding.clChange.setOnClickListener {
            finish()
        }
    }
}