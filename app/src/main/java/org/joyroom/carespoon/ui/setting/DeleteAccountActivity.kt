package org.joyroom.carespoon.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.joyroom.carespoon.R
import org.joyroom.carespoon.databinding.ActivityDeleteAccountBinding
import org.joyroom.carespoon.databinding.ActivitySettingBinding

class DeleteAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}