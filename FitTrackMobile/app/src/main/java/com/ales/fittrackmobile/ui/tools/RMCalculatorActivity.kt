package com.ales.fittrackmobile.ui.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ales.fittrackmobile.databinding.ActivityRmcalculatorBinding
import com.google.android.material.R
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

        binding.unitInputValue.setAdapter(getAutocompleteAdapter())

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

    private fun getAutocompleteAdapter(): ArrayAdapter<String> {
        val list = listOf("Kg", "Lb")
        return ArrayAdapter(
            this@RMCalculatorActivity,
            R.layout.support_simple_spinner_dropdown_item,
            list
        )
    }

    private fun onCalculateButtonClick() {
        val weight: Int
        val reps: Int
        try {
            weight = checkValues(binding.weightRmValue.text)
            reps = checkValues(binding.repsRmValue.text)
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

    private fun checkValues(field : Editable?): Int {
        if (field?.isEmpty() == true) throw InputMismatchException()
        return field.toString().toInt()
    }
}