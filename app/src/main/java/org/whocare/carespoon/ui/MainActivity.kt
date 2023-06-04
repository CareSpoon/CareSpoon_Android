package org.whocare.carespoon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import org.whocare.carespoon.data.CareSpoonSharedPreferences
import org.whocare.carespoon.databinding.ActivityMainBinding
import org.whocare.carespoon.ui.friends.FriendsActivity
import org.whocare.carespoon.ui.setting.SettingActivity
import org.whocare.carespoon.ui.statistics.StatisticsActivity
import org.whocare.carespoon.ui.viewModel.UserInfoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserInfoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initInternet()
        setUserInfo()
        setIntent()
    }

    private fun initInternet(){
        val uuid = CareSpoonSharedPreferences.getUUID()
        if (uuid != null) {
            viewModel.requestUserInfo(uuid)
        }

        viewModel.userInfo.observe(this, Observer { userInfo ->
            CareSpoonSharedPreferences.setUserKcal(userInfo.metabolicRate.toString())
        })
    }
    private fun setUserInfo(){
        binding.tvName.text = CareSpoonSharedPreferences.getUserName()
        viewModel.userInfo.observe(this, Observer { userInfo ->
            binding.tvUserBirth.text = userInfo.age.toString()
            binding.tvUserHeight.text = userInfo.height.toString()
            binding.tvUserWeight.text = userInfo.weight.toString()
            if(userInfo.sex == 1) binding.tvUserSex.text = "여"
            else binding.tvUserSex.text = "여"
        })
    }

    private fun setIntent(){
        binding.ivSettings.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        binding.clStatistics.setOnClickListener {
            startActivity(Intent(this, StatisticsActivity::class.java))
        }

        binding.clRecord.setOnClickListener {
            startActivity(Intent(this, RecordActivity::class.java))
        }

        binding.clShare.setOnClickListener {
            startActivity(Intent(this, FriendsActivity::class.java))
        }
    }
}