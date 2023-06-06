package com.ales.fittrackmobile.ui.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.widget.Toast
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.databinding.ActivityRmcalculatorBinding
import com.ales.fittrackmobile.helpers.Autocomplete.Companion.getAutocompleteAdapter
import com.ales.fittrackmobile.helpers.FieldChecker.Companion.checkField
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.InputMismatchException

class RMCalculatorActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRmcalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRmcalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "1RM Calculator"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.unitInputValue.setAdapter(getAutocompleteAdapter(getUnitList(), this@RMCalculatorActivity))

        binding.calculateButton.setOnClickListener{onCalculateButtonClick()}
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

    private fun onCalculateButtonClick() {
        val fieldsOk = checkField(binding.weightRmValue)
        if (!checkField(binding.repsRmValue) || !fieldsOk) return

        val weight: Int
        val reps: Int
        try {
            weight = getValues(binding.weightRmValue.text)
            reps = getValues(binding.repsRmValue.text)
        } catch (e: InputMismatchException) {
            Toast.makeText(this@RMCalculatorActivity,
                "You must enter valid values", Toast.LENGTH_SHORT).show()
            return
        }

        var units = "kg"

        if (binding.unitInputValue.text.isNotEmpty())
            units = binding.unitInputValue.text.toString().lowercase()

        binding.resultText.text =
            buildString {
        append(calculate1RM(weight, reps))
        append(units)
    }
    }

    private fun calculate1RM(weight: Int, reps: Int): Double {
        val result = BigDecimal(weight * (1+ 0.0333 * reps))
        return result.setScale(2, RoundingMode.FLOOR).toDouble()
    }

    private fun getValues(field : Editable?): Int {
        if (field?.isEmpty() == true) throw InputMismatchException()
        return field.toString().toInt()
    }

    private fun getUnitList(): List<String> {
        return resources.getStringArray(R.array.weight_unit_list).toList()
    }
}