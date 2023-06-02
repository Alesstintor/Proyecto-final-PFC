package com.ales.fittrackmobile.ui.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityWaterCalculatorBinding
import kotlin.math.roundToInt

class WaterCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWaterCalculatorBinding
    private lateinit var userContext: UserContext
    private val CONVERSION_VALUE = 0.033814

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaterCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Water Calculator"

        userContext = UserContext.getInstance()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.weightWcValue.setText(userContext.user.weight.toString())

        binding.activityFactorInputValue.setAdapter(getAutocompleteAdapter(getActivityFactorList()))
        binding.unitInputValue.setAdapter(getAutocompleteAdapter(getUnitList()))

        binding.calculateButton.setOnClickListener{onCalculateButtonClick()}
    }

    private fun onCalculateButtonClick() {
        if (!areFieldsOk()) return

        val weight = binding.weightWcValue.text.toString().toDouble()
        val activityFactor = getActivityFactor()
        val result = calculateMililitres(weight, activityFactor)
        showResult(result)
    }

    private fun showResult(result: Int) {
        val resultText = when(binding.unitInputValue.text.toString()) {
            "Litres" -> result.toString() + "ml"
            "Ounces" -> (result * CONVERSION_VALUE).roundToInt().toString() + "oz"
            else -> result.toString() + "ml"
        }
        binding.resultText.text = resultText
    }

    private fun getActivityFactor(): Int {
        return when(binding.activityFactorInputValue.text.toString()) {
            "Sedentary (No training)" ->  28
            "Low (1-3 times a week)" ->  30
            "Moderate (3-5 times a week)" ->  35
            "High (+6 times a week)" ->  40
            else -> 28
        }

    }

    private fun areFieldsOk(): Boolean {
        var fieldsOk = true
        if (binding.weightWcValue.text.isNullOrEmpty()) {
            binding.weightWcValue.error = "You must enter this field"
            fieldsOk = false
        }
        fieldsOk = checkAutocompleteField(binding.activityFactorInputValue) && fieldsOk
        fieldsOk = checkAutocompleteField(binding.unitInputValue) && fieldsOk
        return fieldsOk
    }

    private fun calculateMililitres(weight: Double, activityFactor: Int): Int {
        return (weight * activityFactor).roundToInt()
    }

    private fun checkAutocompleteField(field: AutoCompleteTextView): Boolean {
        if (field.text.isNullOrEmpty()) {
            field.error = "You must enter this field"
            return false
        }
        field.error = null
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getActivityFactorList(): List<String> {
        return listOf(
            "Sedentary (No training)",
            "Low (1-3 times a week)",
            "Moderate (3-5 times a week)",
            "High (+6 times a week)",
        )
    }

    private fun getUnitList(): List<String> {
        return listOf(
            "Litres",
            "Ounces"
        )
    }

    private fun getAutocompleteAdapter(list: List<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            this@WaterCalculatorActivity,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            list
        )
    }
}