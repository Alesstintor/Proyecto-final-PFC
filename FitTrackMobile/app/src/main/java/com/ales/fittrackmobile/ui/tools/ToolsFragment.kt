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

        return root
    }

    private fun onRmCalculatorButtonClick() {
        startActivity(Intent(context, RMCalculatorActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}