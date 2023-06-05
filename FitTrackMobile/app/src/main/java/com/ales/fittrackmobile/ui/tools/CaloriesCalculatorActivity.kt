package com.ales.fittrackmobile.ui.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityCaloriesCalculatorBinding
import com.ales.fittrackmobile.helpers.Autocomplete.Companion.getAutocompleteAdapter
import com.ales.fittrackmobile.helpers.FieldChecker.Companion.checkField
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

        binding.genderValue.setAdapter(getAutocompleteAdapter(
            getGenderList(), this@CaloriesCalculatorActivity))

        binding.activityFactorInputValue.setAdapter(getAutocompleteAdapter(
            getActivityFactorList(), this@CaloriesCalculatorActivity))
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
            if (gender == getGenderList()[0]) {
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
        val activityFactorList = getActivityFactorList()
        return when(binding.activityFactorInputValue.text.toString()) {
            activityFactorList[0] -> 1.2
            activityFactorList[1] -> 1.375
            activityFactorList[2] -> 1.55
            activityFactorList[3] -> 1.725
            activityFactorList[4] -> 1.9
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
        fieldsOk = checkField(binding.genderValue) && fieldsOk
        fieldsOk = checkField(binding.activityFactorInputValue) && fieldsOk

        return fieldsOk
    }

    private fun getActivityFactorList(): List<String> {
        return resources.getStringArray(R.array.calories_activity_factor).toList()
    }

    private fun getGenderList(): List<String> {
        return resources.getStringArray(R.array.genders_list).toList()
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