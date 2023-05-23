package org.joyroom.carespoon.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import org.joyroom.carespoon.R
import org.joyroom.carespoon.data.CareSpoonSharedPreferences
import org.joyroom.carespoon.databinding.ActivitySignUpBinding
import org.joyroom.carespoon.ui.statistics.StatisticsActivity
import org.joyroom.carespoon.ui.viewModel.SignViewModel

class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignViewModel by viewModels()
    private lateinit var binding: ActivitySignUpBinding
    private var role = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserRole()
        setNextButton()
    }

    private fun setNextButton(){
        binding.clNext.setOnClickListener {
            if (role.isNotEmpty()) {
                initInternet()
                startActivity(Intent(this, SignUpDoneActivity::class.java))
                finish()
            } else Toast.makeText(this, R.string.fill_all, Toast.LENGTH_SHORT).show()
        }
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

    private fun initInternet(){
        val name = CareSpoonSharedPreferences.getUserName()
        val email = CareSpoonSharedPreferences.getUserEmail()
        CareSpoonSharedPreferences.setUserRole(role)

        viewModel.uuid.observe(this, Observer { uuid ->
            CareSpoonSharedPreferences.setUUID(uuid.userId)
        })

        if (name != null && email != null) {
            viewModel.requestSign(name, email, role)
        }
    }
}