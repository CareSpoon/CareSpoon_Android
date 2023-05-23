package org.joyroom.carespoon.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.joyroom.carespoon.R
import org.joyroom.carespoon.databinding.ActivityMainBinding
import org.joyroom.carespoon.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setIntent()
        setToast()
    }

    private fun setIntent(){
        binding.clChange.setOnClickListener {
            startActivity(Intent(this, ChangeInfoActivity::class.java))
        }
    }

    private fun setToast(){
        val btnList = listOf(binding.clDevelopers, binding.clOpensource, binding.clDelete, binding.clPolicy)

        btnList.forEach{
            it.setOnClickListener {
                Toast.makeText(this, R.string.preparing, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

