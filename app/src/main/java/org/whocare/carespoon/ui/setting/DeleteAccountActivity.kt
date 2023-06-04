package org.whocare.carespoon.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.whocare.carespoon.databinding.ActivityDeleteAccountBinding

class DeleteAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}