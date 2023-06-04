package org.whocare.carespoon.ui.statistics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.whocare.carespoon.databinding.ActivityStatisticsBinding

class StatisticsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatisticsBinding
    private lateinit var statisticsViewPagerAdapter: StatisticsViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)

        initAdapter()
        initTabLayout()

        setContentView(binding.root)
    }

    private fun initAdapter() {
        val fragmentList = listOf(DailyFragment(), MonthlyFragment())

        statisticsViewPagerAdapter = StatisticsViewPagerAdapter(this)
        statisticsViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpStatistics.adapter = statisticsViewPagerAdapter
    }

    private fun initTabLayout(){
        val tabLabel = listOf("Daily", "Monthly")

        TabLayoutMediator(binding.tlMenu, binding.vpStatistics) {
            tab, position -> tab.text = tabLabel[position]
        }.attach()
    }
}