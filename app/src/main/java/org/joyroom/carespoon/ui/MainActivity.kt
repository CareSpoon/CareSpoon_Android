package org.joyroom.carespoon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import org.joyroom.carespoon.data.CareSpoonSharedPreferences
import org.joyroom.carespoon.databinding.ActivityMainBinding
import org.joyroom.carespoon.ui.friends.FriendsActivity
import org.joyroom.carespoon.ui.setting.SettingActivity
import org.joyroom.carespoon.ui.statistics.StatisticsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initName()
        setIntent()
    }

    private fun initName(){
        binding.tvName.text = CareSpoonSharedPreferences.getUserName()
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