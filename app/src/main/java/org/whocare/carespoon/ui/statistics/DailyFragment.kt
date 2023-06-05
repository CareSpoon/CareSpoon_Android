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
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import org.whocare.carespoon.R
import org.whocare.carespoon.data.CareSpoonSharedPreferences
import org.whocare.carespoon.databinding.FragmentDailyBinding
import org.whocare.carespoon.ui.viewModel.StatisticsViewModel
import java.time.LocalDate
import kotlin.properties.Delegates


class DailyFragment : Fragment() {
    private lateinit var binding: FragmentDailyBinding
    private val viewModel: StatisticsViewModel by activityViewModels()
    private var carbon by Delegates.notNull<Double>()
    private var fat by Delegates.notNull<Double>()
    private var protein by Delegates.notNull<Double>()
    private var kcal by Delegates.notNull<Double>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyBinding.inflate(inflater, container, false)

        setDefault()
        setStatisticData()

        return binding.root
    }

    private fun setDefault() { // 추후에 < > 버튼으로 날짜 바꾸는 것 까지 적용하기
        var date = LocalDate.now().toString()
        CareSpoonSharedPreferences.getUUID()?.let { viewModel.requestDayList(it, date) }

        binding.tvNumber.text = String.format("%.2f", CareSpoonSharedPreferences.getUserKcal()!!.toFloat())
    }

    private fun setStatisticData(){
        viewModel.dailyStatisticData.observe(viewLifecycleOwner, Observer { statisticData ->
            carbon = statisticData.meal_Carbon
            fat = statisticData.meal_Fat
            protein = statisticData.meal_Protein
            kcal = statisticData.meal_Kcal
        })

        setChart()
    }

    private fun setChart(){
        viewModel.noDayData.observe(viewLifecycleOwner, Observer{
            it.getContentIfNotHandled()?.let {
                setPieChart(0f)
                setBarChart(0f, 0f, 0f)
            }
        })

        viewModel.withDayData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                setPieChart(String.format("%.2f", kcal).toFloat())
                setBarChart((String.format("%.2f", carbon).toFloat()), (String.format("%.2f", protein).toFloat()), (String.format("%.2f", fat).toFloat()))
            }
        })
    }

    private fun setPieChart(kcal: Float) {
        // 1. [PieEntry] Chart에 표시될 데이터 값 생성
        val entries = ArrayList<PieEntry>()
        val maximum = CareSpoonSharedPreferences.getUserKcal()!!.toFloat()

        entries.add(PieEntry(kcal, ""))
        entries.add(PieEntry(maximum - kcal, ""))

        val colorList = intArrayOf(Color.rgb(80, 189, 111), Color.rgb(246, 246, 246))
        val colorItems = ArrayList<Int>()
        for (c in colorList) colorItems.add(c)

        // 2. [PieDataSet] Chart 커스텀
        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.apply {
            colors = colorItems
        }

        // 3. [pieData] 보여질 데이터 구성
        val pieData = PieData(pieDataSet)
        binding.chPieChart.apply {
            data = pieData
            setUsePercentValues(true)
            description.isEnabled = false
            isRotationEnabled = false
            legend.isEnabled = false
            centerText = "${kcal}kcal"
            setTouchEnabled(false)
            setCenterTextColor(R.color.black)
            setCenterTextSize(24F)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
    }

    private fun setBarChart(carbon: Float, protein: Float, fat: Float) {
        configureBarChartAppearance()

        // 1. [BarEntry] BarChart에 표시될 데이터 값 생성
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, fat))
        entries.add(BarEntry(1f, protein))
        entries.add(BarEntry(2f, carbon))

        // 2. [BarDataSet] 단순 데이터를 막대 모양으로 표시, BarChart의 막대 커스텀
        val barDataSet = BarDataSet(entries, "")
        barDataSet.apply {
            setDrawIcons(false)
            setDrawValues(true)
            setColors(
                ContextCompat.getColor(binding.chBarChart.context, R.color.lime01),
                ContextCompat.getColor(binding.chBarChart.context, R.color.purple01),
                ContextCompat.getColor(binding.chBarChart.context, R.color.pink01)
            )
            valueTextSize = 15f
        }

        // Create the labels for the bars
        val xVals : ArrayList<String> = arrayListOf("지", "단", "탄")

        // 3. [BarData] 보여질 데이터 구성
        val barData = BarData(barDataSet)
        binding.chBarChart.apply {
            data = barData
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(false)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
            xAxis.textSize = 18f
            xAxis.labelCount = xVals.size
            xAxis.valueFormatter = IndexAxisValueFormatter(xVals)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            setDrawValueAboveBar(true)
        }
    }

    private fun configureBarChartAppearance() {
        val skillRatingChart = binding.chBarChart

        skillRatingChart.setDrawBarShadow(false)
        val description = Description()
        description.text = ""
        skillRatingChart.description = description
        skillRatingChart.legend.isEnabled = false
        skillRatingChart.setPinchZoom(false)
        skillRatingChart.setDrawValueAboveBar(false)

        //Display the axis on the left (contains the labels 1*, 2* and so on)
        val xAxis = skillRatingChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.isEnabled = true
        xAxis.setDrawAxisLine(false)

        val yLeft = skillRatingChart.axisLeft
        //Set the minimum and maximum bar lengths as per the values that they represent
        // yLeft.axisMaximum = CareSpoonSharedPreferences.getUserKcal()!!.toFloat()
        yLeft.axisMaximum = 100f
        yLeft.axisMinimum = 0f
        yLeft.spaceTop = 10f
        yLeft.isEnabled = false

        val yRight = skillRatingChart.axisRight
        yRight.setDrawAxisLine(true)
        yRight.setDrawGridLines(false)
        yRight.isEnabled = false
    }

    companion object {
    }
}