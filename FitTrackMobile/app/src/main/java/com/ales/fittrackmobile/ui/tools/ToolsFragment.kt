package com.ales.fittrackmobile.ui.tools

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ales.fittrackmobile.databinding.FragmentToolsBinding

class ToolsFragment : Fragment() {

    private var _binding: FragmentToolsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentToolsBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.rmCalculatorButton.setOnClickListener{onRmCalculatorButtonClick()}
        binding.caloriesCalculatorButton.setOnClickListener{onCaloriesCalculatorButtonClick()}
        binding.waterCalculatorButton.setOnClickListener{onWaterCalculatorButtonClick()}
        binding.waterRegisterButton.setOnClickListener{onWaterRegisterButtonClick()}

        return root
    }

    private fun onWaterCalculatorButtonClick() {
        startActivity(Intent(context, WaterCalculatorActivity::class.java))
    }

    private fun onWaterRegisterButtonClick() {
        startActivity(Intent(context, WaterRegisterActivity::class.java))
    }

    private fun onCaloriesCalculatorButtonClick() {
        startActivity(Intent(context, CaloriesCalculatorActivity::class.java))
    }

    private fun onRmCalculatorButtonClick() {
        startActivity(Intent(context, RMCalculatorActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}