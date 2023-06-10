package org.whocare.carespoon.ui.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import org.whocare.carespoon.R
import org.whocare.carespoon.data.CareSpoonSharedPreferences
import org.whocare.carespoon.databinding.FragmentMonthlyBinding
import org.whocare.carespoon.ui.viewModel.StatisticsViewModel
import java.time.LocalDate
import kotlin.properties.Delegates


class MonthlyFragment : Fragment() {
    private lateinit var binding: FragmentMonthlyBinding
    private val viewModel: StatisticsViewModel by activityViewModels()
    private var carbon by Delegates.notNull<Double>()
    private var fat by Delegates.notNull<Double>()
    private var protein by Delegates.notNull<Double>()
    private var kcal by Delegates.notNull<Double>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMonthlyBinding.inflate(inflater, container, false)

        setDefault()
        setStatisticData()

        return binding.root
    }

    private fun setDefault() { // 추후에 < > 버튼으로 날짜 바꾸는 것 까지 적용하기
        var date = LocalDate.now().toString().slice(0..6)
        binding.tvDate.text = date
        CareSpoonSharedPreferences.getUUID()?.let { viewModel.requestMonthList(it, date) }

        binding.tvNumber.text = String.format("%.2f", CareSpoonSharedPreferences.getUserKcal()!!.toFloat() * 30)
    }

    private fun setStatisticData(){
        viewModel.monthlyStatisticData.observe(viewLifecycleOwner, Observer { statisticData ->
            carbon = statisticData.meal_Carbon
            fat = statisticData.meal_Fat
            protein = statisticData.meal_Protein
            kcal = statisticData.meal_Kcal
        })

        setChart()
    }

    private fun setChart(){
        viewModel.withMonthData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                setLineChart(String.format("%.2f", kcal).toFloat(), (String.format("%.2f", carbon).toFloat()), (String.format("%.2f", protein).toFloat()), (String.format("%.2f", fat).toFloat()))
            }
        })
    }

    private fun setLineChart(total: Float, carbon: Float, protein: Float, fat: Float) {
        configureBarChartAppearance()

        val entries = ArrayList<Entry>()
        entries.add(BarEntry(0f, fat))
        entries.add(BarEntry(1f, protein))
        entries.add(BarEntry(2f, carbon))
        entries.add(BarEntry(3f, fat))

        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.apply {
            setDrawIcons(false)
            setDrawValues(true)
            valueTextSize = 15f
            lineWidth = 5f
            color = Color.rgb(32, 201, 80);
        }

        // Create the labels for the bars
        val xVals : ArrayList<String> = arrayListOf("지방", "단백질", "탄수화물", "총량")

        val lineData = LineData(lineDataSet)
        binding.chLineChart.apply {
            data = lineData
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(false)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
            xAxis.textSize = 18f
            xAxis.labelCount = xVals.size
            xAxis.valueFormatter = IndexAxisValueFormatter(xVals)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
        }
    }

    private fun configureBarChartAppearance() {
        val maxKcal = CareSpoonSharedPreferences.getUserKcal()!!.toFloat()*30
        binding.chLineChart.extraBottomOffset = 15f // 간격
        binding.chLineChart.description.isEnabled = false // chart 밑에 description 표시 유무

        // Legend는 차트의 범례
        val legend: Legend = binding.chLineChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.form = Legend.LegendForm.CIRCLE
        legend.formSize = 10f
        legend.textSize = 13f
        legend.textColor = Color.parseColor("#A3A3A3")
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.yEntrySpace = 5f
        legend.isWordWrapEnabled = true
        legend.xOffset = 80f
        legend.yOffset = 20f
        legend.calculatedLineSizes

        // XAxis (아래쪽) - 선 유무, 사이즈, 색상, 축 위치 설정
        val xAxis: XAxis = binding.chLineChart.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM // x축 데이터 표시 위치

        xAxis.granularity = 1f
        xAxis.textSize = 14f
        xAxis.textColor = Color.rgb(118, 118, 118)
        xAxis.spaceMin = 0.1f // Chart 맨 왼쪽 간격 띄우기

        // YAxis(Right) (왼쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
        val yAxisLeft: YAxis = binding.chLineChart.axisLeft
        yAxisLeft.textSize = 14f
        yAxisLeft.textColor = Color.rgb(163, 163, 163)
        yAxisLeft.setDrawAxisLine(false)
        yAxisLeft.axisLineWidth = 2f
        yAxisLeft.axisMinimum = 0f // 최솟값
        yAxisLeft.axisMaximum = maxKcal

        // YAxis(Left) (오른쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
        val yAxis: YAxis = binding.chLineChart.axisRight
        yAxis.setDrawLabels(false) // label 삭제
        yAxis.textColor = Color.rgb(163, 163, 163)
        yAxis.setDrawAxisLine(false)
        yAxis.axisLineWidth = 2f
        yAxis.axisMinimum = 0f // 최솟값
        yAxis.axisMaximum = maxKcal
    }

    companion object {
    }
}