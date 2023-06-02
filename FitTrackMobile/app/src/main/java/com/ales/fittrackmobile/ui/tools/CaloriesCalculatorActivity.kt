package com.ales.fittrackmobile.ui.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityCaloriesCalculatorBinding
import com.google.android.material.R
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.roundToInt

class CaloriesCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCaloriesCalculatorBinding
    private lateinit var userContext: UserContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaloriesCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Calories Calculator"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        userContext = UserContext.getInstance()

        setUpInitialValues()

        binding.calculateButton.setOnClickListener{onCalculateButtonClick()}
        binding.genderValue.setAdapter(getAutocompleteAdapter(listOf("Man", "Woman")))
        binding.activityFactorInputValue.setAdapter(getAutocompleteAdapter(getActivityFactorList()))
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

    private fun setUpInitialValues() {
        with(userContext.user) {
            if (weight != 0.0) binding.weightValue.setText(weight.toString())
            if (height != 0) binding.heightValue.setText(height.toString())
            if (age != 0) binding.ageValue.setText(age.toString())
        }
    }


    private fun onCalculateButtonClick() {
        if(!areFieldsOk()) return

        val weight = binding.weightValue.text.toString().toDouble()
        val height = binding.heightValue.text.toString().toInt()
        val age = binding.ageValue.text.toString().toInt()
        val gender = binding.genderValue.toString()
        val activityFactor = getActivityFactor()

        val result: CalorieResult =
            if (gender == "Man") {
            calculateCaloriesMen(CalorieProfile(weight, height, age, activityFactor))
            } else {
                calculateCaloriesWomen(CalorieProfile(weight, height, age, activityFactor))
            }


        showResult(result)
    }

    private fun showResult(result: CalorieResult) {
        binding.basalCaloriesText.visibility = View.VISIBLE
        binding.maintenanceCaloriesText.visibility = View.VISIBLE

        binding.basalCaloriesResult.text = result.basalCalories
        binding.maintenanceCaloriesResult.text = result.maintenanceCalories
    }

    private fun getActivityFactor(): Double {
        return when(binding.activityFactorInputValue.text.toString()) {
            "Sedentary (No training)" -> 1.2
            "Low (1-3 times a week)" -> 1.375
            "Moderate (3-5 times a week)" -> 1.55
            "High (6-7 times a week)" -> 1.725
            "Athlete (+4 hours a day)" -> 1.9
            else -> 1.2
        }
    }

    private fun calculateCaloriesWomen(profile: CalorieProfile): CalorieResult {
        val basalCal =
            (655 +
                    (9.6 * profile.weight) +
                    (1.8 * profile.height) -
                    (4.7 * profile.age)).roundToInt()

        val maintenance = (basalCal * profile.activityFactor).roundToInt()

        return CalorieResult(basalCal, maintenance)
    }

    private fun calculateCaloriesMen(profile: CalorieProfile): CalorieResult  {
        val basalCal =
                    (66 +
                    (13.7 * profile.weight) +
                    (5 * profile.height) -
                    (6.8 * profile.age)).roundToInt()

        val maintenance = (basalCal * profile.activityFactor).roundToInt()

        return CalorieResult(basalCal, maintenance)
    }

    private fun areFieldsOk(): Boolean {
        var fieldsOk = true
        fieldsOk = checkField(binding.weightValue) && fieldsOk
        fieldsOk = checkField(binding.heightValue) && fieldsOk
        fieldsOk = checkField(binding.ageValue) && fieldsOk
        fieldsOk = checkAutocompleteField(binding.genderValue) && fieldsOk
        fieldsOk = checkAutocompleteField(binding.activityFactorInputValue) && fieldsOk

        return fieldsOk
    }

    private fun checkField(field: TextInputEditText): Boolean {
        if (field.text.isNullOrEmpty()) {
            field.error = "You must enter this field"
            return false
        }
        field.error = null
        return true
    }

    private fun checkAutocompleteField(field: AutoCompleteTextView): Boolean {
        if (field.text.isNullOrEmpty()) {
            field.error = "You must enter this field"
            return false
        }
        field.error = null
        return true
    }

    private fun getActivityFactorList(): List<String> {
        return listOf(
            "Sedentary (No training)",
            "Low (1-3 times a week)",
            "Moderate (3-5 times a week)",
            "High (6-7 times a week)",
            "Athlete (+4 hours a day)",
        )
    }

    private fun getAutocompleteAdapter(list: List<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            this@CaloriesCalculatorActivity,
            R.layout.support_simple_spinner_dropdown_item,
            list
        )
    }

    data class CalorieProfile(
        val weight: Double,
        val height: Int,
        val age: Int,
        val activityFactor: Double)

    class CalorieResult(val basalCalories: String, val maintenanceCalories: String) {
        constructor(basalCalories: Int, maintenanceCalories: Int) : this(
            "$basalCalories kcal", "$maintenanceCalories kcal")
    }
}