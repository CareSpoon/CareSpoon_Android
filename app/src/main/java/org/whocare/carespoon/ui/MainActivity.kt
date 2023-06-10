package org.whocare.carespoon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.whocare.carespoon.data.CareSpoonSharedPreferences
import org.whocare.carespoon.data.remote.response.meal.ResponseDayMealListItem
import org.whocare.carespoon.databinding.ActivityMainBinding
import org.whocare.carespoon.ui.friends.FriendsActivity
import org.whocare.carespoon.ui.record.RecordActivity
import org.whocare.carespoon.ui.record.RecordDetailActivity
import org.whocare.carespoon.ui.setting.SettingActivity
import org.whocare.carespoon.ui.statistics.StatisticsActivity
import org.whocare.carespoon.ui.viewModel.MealViewModel
import org.whocare.carespoon.ui.viewModel.UserInfoViewModel
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserInfoViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserInfo()
        setDate()
        setMealRecord()
        setIntent()

        Log.d("metabolic", CareSpoonSharedPreferences.getUserKcal().toString())
    }

    override fun onResume() {
        super.onResume()

        setUserInfo()
        setDate()
        setMealRecord()
        setIntent()
    }

    private fun setUserInfo(){
        val uuid = CareSpoonSharedPreferences.getUUID()
        if (uuid != null) {
            viewModel.requestUserInfo(uuid)
        }

        viewModel.userInfo.observe(this, Observer { userInfo ->
            CareSpoonSharedPreferences.setUserKcal(userInfo.metabolicRate.toString())
        })

        binding.tvName.text = CareSpoonSharedPreferences.getUserName()
    }

    private fun setDate() { // 추후에 < > 버튼으로 날짜 바꾸는 것 까지 적용하기
        var date = LocalDate.now().toString()
        CareSpoonSharedPreferences.getUUID()?.let { mealViewModel.requestMealList(it, date) }
    }

    private fun setMealRecord() {
        mealViewModel.mealList.observe(this, Observer { mealList ->
            mealList.forEach {
                when (it.tag) {
                    "breakfast" -> {
                        setBreakfastRecord(it)
                    }

                    "lunch" -> {
                        setLunchRecord(it)
                    }

                    "dinner" -> {
                        setDinnerRecord(it)
                    }
                }
            }
        })
    }

    private fun setBreakfastRecord(it: ResponseDayMealListItem) {
        binding.clBreakfastNull.isVisible = false
        binding.tvBreakfastTime.text = it.eatTime
        Glide.with(this)
            .load(it.imageUrl)
            .into(binding.ivBreakfast)
    }

    private fun setLunchRecord(it: ResponseDayMealListItem) {
        binding.clLunchNull.isVisible = false
        binding.tvLunchTime.text = it.eatTime
        Glide.with(this)
            .load(it.imageUrl)
            .into(binding.ivLunch)
    }

    private fun setDinnerRecord(it: ResponseDayMealListItem) {
        binding.clDinnerNull.isVisible = false
        binding.tvDinnerTime.text = it.eatTime
        Glide.with(this)
            .load(it.imageUrl)
            .into(binding.ivDinner)
    }

    private fun setIntent(){
        binding.ivSettings.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        binding.clBreakfast.setOnClickListener {
            val intent = Intent(this, RecordDetailActivity::class.java)
            intent.putExtra("tag", "breakfast")
            startActivity(intent)
        }
        binding.clLunch.setOnClickListener {
            val intent = Intent(this, RecordDetailActivity::class.java)
            intent.putExtra("tag", "lunch")
            startActivity(intent)
        }
        binding.clDinner.setOnClickListener {
            val intent = Intent(this, RecordDetailActivity::class.java)
            intent.putExtra("tag", "dinner")
            startActivity(intent)
        }

        binding.clBreakfastNull.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            intent.putExtra("tag", "breakfast")
            startActivity(intent)
        }
        binding.clLunchNull.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            intent.putExtra("tag", "lunch")
            startActivity(intent)
        }
        binding.clDinnerNull.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            intent.putExtra("tag", "dinner")
            startActivity(intent)
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