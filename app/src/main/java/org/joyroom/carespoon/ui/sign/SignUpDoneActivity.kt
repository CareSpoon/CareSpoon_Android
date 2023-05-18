package org.joyroom.carespoon.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import org.joyroom.carespoon.R
import org.joyroom.carespoon.data.CareSpoonSharedPreferences
import org.joyroom.carespoon.data.remote.response.userinfo.ResponseUserInfo
import org.joyroom.carespoon.databinding.ActivitySignUpDoneBinding
import org.joyroom.carespoon.ui.MainActivity
import org.joyroom.carespoon.ui.viewModel.SignViewModel
import kotlin.properties.Delegates

class SignUpDoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpDoneBinding
    private val viewModel: SignViewModel by viewModels()

    // 개선 사항: 아래 모두 값 처음에 넣어 놓는 대신 예외 처리해서 선택된 적 없으면 버튼 안 눌리도록 바꿀 것
    private var sex = 1
    private lateinit var userBirth: String
    private var userHeight  = 160.0
    private var userWeight = 50.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserSex()
        setDoneButton()
    }

    private fun getUserSex() {
        binding.clFemale.setOnClickListener{
            binding.clFemale.isSelected = true
            binding.clMale.isSelected = false
            sex = 1
        }
        binding.clMale.setOnClickListener{
            binding.clFemale.isSelected = false
            binding.clMale.isSelected = true
            sex = 0
        }
    }

    private fun setDoneButton(){
        binding.clSignup.setOnClickListener{
            getUserInfo()
            initInternet()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun getUserInfo(){
        userBirth = binding.etBirth.text.toString().slice(0..3)
        userHeight = binding.etHeight.text.toString().toDouble()
        userWeight = binding.etWeight.text.toString().toDouble()
    }

    private fun initInternet(){
        val uuid = CareSpoonSharedPreferences.getUUID()
        if (uuid != null) {
            viewModel.postUserInfo(uuid, userBirth, sex, userHeight, userWeight)
        }

    }
}