package com.ales.fittrackmobile.ui.tools

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ales.fittrackmobile.databinding.ActivityWaterRegisterBinding
import java.util.Calendar

class WaterRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWaterRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var waterValue = 0
    private var lastGlass = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaterRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Water Register"

        sharedPreferences = getSharedPreferences("FittrackPrefs", Context.MODE_PRIVATE)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        retrieveValues()
        updateValue()

        binding.addButton.setOnClickListener{onAddButtonClick()}
        binding.removeButton.setOnClickListener{onRemoveButtonClick()}
    }

    private fun onRemoveButtonClick() {
        if (waterValue <= 0) return
        waterValue--
        updateValue()
    }

    private fun onAddButtonClick() {
        waterValue++
        updateValue()
    }

    private fun updateValue() {
        binding.waterGlassesNumber.text = waterValue.toString()
        lastGlass = System.currentTimeMillis()
    }

    private fun saveLastValue() {
        val editor = sharedPreferences.edit()
        editor.putInt("waterValue", waterValue)
        editor.putLong("lastGlass", lastGlass)
        editor.apply()
    }

    private fun retrieveValues() {
        waterValue = sharedPreferences.getInt("waterValue", 0)
        lastGlass = sharedPreferences.getLong("lastGlass", 1)
        val lastGlassCalendar = Calendar.getInstance()
        val nowCalendar = Calendar.getInstance()
        lastGlassCalendar.timeInMillis = lastGlass

        if (!areSameDay(lastGlassCalendar, nowCalendar)) {
        waterValue = 0
        lastGlass = System.currentTimeMillis()
        }
    }

    private fun areSameDay(calendar1: Calendar, calendar2: Calendar): Boolean {
        val year1 = calendar1.get(Calendar.YEAR)
        val month1 = calendar1.get(Calendar.MONTH)
        val day1 = calendar1.get(Calendar.DAY_OF_MONTH)

        val year2 = calendar2.get(Calendar.YEAR)
        val month2 = calendar2.get(Calendar.MONTH)
        val day2 = calendar2.get(Calendar.DAY_OF_MONTH)

        return year1 == year2 && month1 == month2 && day1 == day2
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                saveLastValue()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}