package org.joyroom.carespoon.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.joyroom.carespoon.R
import org.joyroom.carespoon.data.CareSpoonSharedPreferences
import org.joyroom.carespoon.data.remote.response.meal.ResponseDayMealList
import org.joyroom.carespoon.data.remote.response.meal.ResponseDayMealListItem
import org.joyroom.carespoon.databinding.ActivityRecordBinding
import org.joyroom.carespoon.ui.viewModel.MealViewModel
import java.time.LocalDate

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    private val viewModel: MealViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMealRecord()
        setIntent()
        setDate()

    }

    private fun setDate() { // 추후에 < > 버튼으로 날짜 바꾸는 것 까지 적용하기
        var date = LocalDate.now().toString()
        binding.tvDate.text = date
        CareSpoonSharedPreferences.getUUID()?.let { viewModel.requestMealList(it, date) }
    }

    private fun setMealRecord() {
        viewModel.mealList.observe(this, Observer { mealList ->
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
        binding.tvBfProteinValue.text = String.format("%.2f", it.meal_Protein)
        binding.tvBfCarbohydrateValue.text = String.format("%.2f", it.meal_Carbon)
        binding.tvBfFatValue.text = String.format("%.2f", it.meal_Fat)
        binding.tvBreakfastTotal.text = String.format("%.2f", it.meal_Kcal)
        Glide.with(this)
            .load(it.imageUrl)
            .into(binding.ivBreakfast)
    }

    private fun setLunchRecord(it: ResponseDayMealListItem) {
        binding.clLunchNull.isVisible = false
        binding.tvLunchTime.text = it.eatTime
        binding.tvLcProteinValue.text = String.format("%.2f", it.meal_Protein)
        binding.tvLcCarbohydrateValue.text = String.format("%.2f", it.meal_Carbon)
        binding.tvLcFatValue.text = String.format("%.2f", it.meal_Fat)
        binding.tvLunchTotal.text = String.format("%.2f", it.meal_Kcal)
        Glide.with(this)
            .load(it.imageUrl)
            .into(binding.ivLunch)
    }

    private fun setDinnerRecord(it: ResponseDayMealListItem) {
        binding.clDinnerNull.isVisible = false
        binding.tvDinnerTime.text = it.eatTime
        binding.tvDnProteinValue.text = String.format("%.2f", it.meal_Protein)
        binding.tvDnCarbohydrateValue.text = String.format("%.2f", it.meal_Carbon)
        binding.tvDnFatValue.text = String.format("%.2f", it.meal_Fat)
        binding.tvDinnerTotal.text = String.format("%.2f", it.meal_Kcal)
        Glide.with(this)
            .load(it.imageUrl)
            .into(binding.ivDinner)
    }

    private fun setIntent() {
        binding.clBreakfastNull.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
        binding.clLunchNull.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
        binding.clDinnerNull.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
}


//if(!binding.clBreakfastNull.isVisible) Toast.makeText(this, R.string.already_posted, Toast.LENGTH_SHORT).show()
//        else{