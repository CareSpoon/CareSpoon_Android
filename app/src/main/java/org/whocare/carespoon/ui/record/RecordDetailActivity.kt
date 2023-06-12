package org.whocare.carespoon.ui.record

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.whocare.carespoon.data.CareSpoonSharedPreferences
import org.whocare.carespoon.databinding.ActivityRecordDetailBinding
import org.whocare.carespoon.ui.viewModel.MealViewModel
import java.time.LocalDate

class RecordDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordDetailBinding
    private val viewModel: MealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMeal()
        setDate()
        setRecord()
    }

    private fun setDate() {
        val date = LocalDate.now().toString() // "2023-06-10"
        CareSpoonSharedPreferences.getUUID()?.let { viewModel.requestMealList(it, date) }
        binding.tvDate.text = date
    }

    private fun setMeal() {
        when (intent.getStringExtra("tag").toString()) {
            "breakfast" -> {
                binding.tvMealType.text = "아침"
            }

            "lunch" -> {
                binding.tvMealType.text = "점심"
            }

            "dinner" -> {
                binding.tvMealType.text = "저녁"
            }
        }
    }


    private fun setRecord() {
        val tag = intent.getStringExtra("tag").toString()

        viewModel.mealList.observe(this, Observer { mealList ->
            mealList.forEach {
                if (it.tag == tag) {
                    binding.tvProteinValue.text = String.format("%.2f", it.meal_Protein)
                    binding.tvCarbohydrateValue.text = String.format("%.2f", it.meal_Carbon)
                    binding.tvFatValue.text = String.format("%.2f", it.meal_Fat)
                    binding.tvCaValue.text = String.format("%.2f", it.meal_cal)
                    binding.tvFeValue.text = String.format("%.2f", it.meal_fe)
                    binding.tvNaValue.text = String.format("%.2f", it.meal_na)
                    binding.tvTotalValue.text = String.format("%.2f", it.meal_Kcal)
                    Glide.with(this)
                        .load(it.imageUrl)
                        .into(binding.ivMeal)
                }
            }
        })
    }
}