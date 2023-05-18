package org.joyroom.carespoon.ui.statistics

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
import org.joyroom.carespoon.R
import org.joyroom.carespoon.databinding.FragmentMonthlyBinding

class MonthlyFragment : Fragment() {
    private lateinit var binding: FragmentMonthlyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMonthlyBinding.inflate(inflater, container, false)
        setPieChart()
        setBarChart()

        return binding.root
    }

    private fun setPieChart() { // 숫자 없애거나 안되면 246색으로라도 바꿔야 함
        // 1. [PieEntry] Chart에 표시될 데이터 값 생성
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(50f, ""))
        entries.add(PieEntry(50f, ""))

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
            centerText = "465Kcal"
            setTouchEnabled(false)
            setCenterTextColor(R.color.black)
            setCenterTextSize(24F)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
    }

    private fun setBarChart() {
        configureBarChartAppearance()

        // 1. [BarEntry] BarChart에 표시될 데이터 값 생성
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 27f))
        entries.add(BarEntry(1f, 45f))
        entries.add(BarEntry(2f, 65f))

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
        yLeft.axisMaximum = 100f
        yLeft.axisMinimum = 0f
        yLeft.isEnabled = false

        val yRight = skillRatingChart.axisRight
        yRight.setDrawAxisLine(true)
        yRight.setDrawGridLines(false)
        yRight.isEnabled = false
    }
    companion object {
    }
}