package com.ales.fittrackmobile.ui.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityWaterCalculatorBinding
import com.ales.fittrackmobile.helpers.Autocomplete.Companion.getAutocompleteAdapter
import com.ales.fittrackmobile.helpers.FieldChecker.Companion.checkField
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

        binding.activityFactorInputValue.setAdapter(
            getAutocompleteAdapter(getActivityFactorList(), this@WaterCalculatorActivity))

        binding.unitInputValue.setAdapter(
            getAutocompleteAdapter(getUnitList(), this@WaterCalculatorActivity))

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
        val unitList = getUnitList()
        val resultText = when(binding.unitInputValue.text.toString()) {
            unitList[0] -> result.toString() + "ml"
            unitList[1] -> (result * CONVERSION_VALUE).roundToInt().toString() + "oz"
            else -> result.toString() + "ml"
        }
        binding.resultText.text = resultText
    }

    private fun getActivityFactor(): Int {
        val activityFactorList = getActivityFactorList()
        return when(binding.activityFactorInputValue.text.toString()) {
            activityFactorList[0] ->  28
            activityFactorList[1] ->  30
            activityFactorList[2] ->  35
            activityFactorList[3] ->  40
            else -> 28
        }

    }

    private fun areFieldsOk(): Boolean {
        var fieldsOk = checkField(binding.weightWcValue)
        fieldsOk = checkField(binding.activityFactorInputValue) && fieldsOk
        fieldsOk = checkField(binding.unitInputValue) && fieldsOk
        return fieldsOk
    }

    private fun calculateMililitres(weight: Double, activityFactor: Int): Int {
        return (weight * activityFactor).roundToInt()
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
        return resources.getStringArray(R.array.water_activity_factor).toList()
    }

    private fun getUnitList(): List<String> {
        return resources.getStringArray(R.array.volume_unit_list).toList()
    }
}