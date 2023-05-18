package org.joyroom.carespoon.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import org.joyroom.carespoon.R
import org.joyroom.carespoon.data.CareSpoonSharedPreferences
import org.joyroom.carespoon.databinding.ActivitySignUpBinding
import org.joyroom.carespoon.ui.statistics.StatisticsActivity
import org.joyroom.carespoon.ui.viewModel.SignViewModel

class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignViewModel by viewModels()
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var role: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserRole()
        setNextButton()
    }

    private fun setUserRole() {
        binding.clGeneral.setOnClickListener{
            binding.clGeneral.isSelected = true
            binding.clViewer.isSelected = false
            role = "senior"
        }
        binding.clViewer.setOnClickListener{
            binding.clGeneral.isSelected = false
            binding.clViewer.isSelected = true
            role = "viewer"
        }
    }

    private fun setNextButton(){
        binding.clNext.setOnClickListener {
            initInternet()
            startActivity(Intent(this, SignUpDoneActivity::class.java))
            finish()
        }
    }

    private fun initInternet(){
        val name = CareSpoonSharedPreferences.getUserName()
        val email = CareSpoonSharedPreferences.getUserEmail()
        if (name != null && email != null) {
            viewModel.requestSign(name, email, role)
        }
    }
}