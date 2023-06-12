package org.whocare.carespoon.ui.record

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.whocare.carespoon.data.CareSpoonSharedPreferences
import org.whocare.carespoon.data.remote.response.meal.ResponseDayMealListItem
import org.whocare.carespoon.databinding.ActivityRecordBinding
import org.whocare.carespoon.ui.CameraActivity
import org.whocare.carespoon.ui.viewModel.MealViewModel
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

    override fun onResume() {
        super.onResume()

        setMealRecord()
        setIntent()
        setDate()
    }

    private fun setDate() {
        val date = LocalDate.now().toString() // "2023-06-10"
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
    }
}