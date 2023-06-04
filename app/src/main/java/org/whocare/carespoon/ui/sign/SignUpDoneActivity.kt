package org.whocare.carespoon.ui.sign

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.whocare.carespoon.R
import org.whocare.carespoon.data.CareSpoonSharedPreferences
import org.whocare.carespoon.databinding.ActivitySignUpDoneBinding
import org.whocare.carespoon.ui.MainActivity
import org.whocare.carespoon.ui.viewModel.SignViewModel

class SignUpDoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpDoneBinding
    private val viewModel: SignViewModel by viewModels()

    private var sex = ""
    private var userBirth = ""
    private var userHeight = 160.0
    private var userWeight = 50.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserSex()
        setDoneButton()
    }

    private fun getUserSex() {
        binding.clFemale.setOnClickListener {
            binding.clFemale.isSelected = true
            binding.clMale.isSelected = false
            sex = "1"
        }
        binding.clMale.setOnClickListener {
            binding.clFemale.isSelected = false
            binding.clMale.isSelected = true
            sex = "0"
        }
    }

    private fun setDoneButton() {
        binding.clSignup.setOnClickListener {
            if (binding.etBirth.text.length >= 4 && binding.etHeight.text.isNotEmpty() && binding.etWeight.text.isNotEmpty() && sex.isNotEmpty()) {
                getUserInfo()
                initInternet()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else Toast.makeText(this, R.string.fill_all, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getUserInfo() {
        userBirth = binding.etBirth.text.toString().slice(0..3)
        userHeight = binding.etHeight.text.toString().toDouble()
        userWeight = binding.etWeight.text.toString().toDouble()
    }

    private fun initInternet() {
        val uuid = CareSpoonSharedPreferences.getUUID()
        if (uuid != null) {
            viewModel.postUserInfo(uuid, userBirth, sex.toInt(), userHeight, userWeight)
        }

    }
}