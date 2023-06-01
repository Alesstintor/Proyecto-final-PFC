package com.ales.fittrackmobile.ui.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityExerciseBinding
import com.ales.fittrackmobile.entities.Exercise
import com.ales.fittrackmobile.entities.Muscles
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private lateinit var userContext: UserContext
    private lateinit var chipGroup: ChipGroup
    private val selectedMuscles = mutableListOf<Muscles>()
    private var exercise = Exercise()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()
        chipGroup = binding.chipGroup

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        (binding.typeInput.editText as? AutoCompleteTextView)?.setAdapter(
            getAutocompleteAdapter(getTypesList()))

        binding.muscleEditText.setAdapter(
            getAutocompleteAdapter(Muscles.values().map { muscles -> muscles.name }))

        val exerciseId = intent.getLongExtra("EXERCISEID", -1L)

        if (exerciseId != -1L) setExistingExercise(exerciseId)

        binding.createExerciseButton.setOnClickListener{onCreateExerciseButtonClick()}
        binding.addMuscleButton.setOnClickListener{onAddMuscleButtonClick()}
    }

    private fun getAutocompleteAdapter(list: List<String>): ArrayAdapter<String> {

        return ArrayAdapter(
            this@ExerciseActivity,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            list)
    }

    private fun getTypesList(): List<String> {
        return listOf("Weight x Reps")
    }

    private fun setExistingExercise(exerciseId: Long) {
        val existingExercise =
            userContext.exercisesList.find { exercise -> exercise.id == exerciseId }

        if (existingExercise != null) {
            exercise.id = existingExercise.id
            exercise.name = existingExercise.name
            exercise.description = existingExercise.description
            exercise.muscles = existingExercise.muscles.toList()
            exercise.type = existingExercise.type
            flushFields()
        }
    }

    private fun flushFields() {
        binding.titleValue.setText(exercise.name)
        binding.descriptionTextField.setText(exercise.description)
        binding.typeInputValue.setText(exercise.type)
        exercise.muscles.forEach { muscle ->
            addChip(muscle.name)
            selectedMuscles.add(muscle)
         }
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

    private fun setNewExercise() {
        exercise.name = binding.titleValue.text.toString()
        exercise.description = binding.descriptionTextField.text.toString()
        exercise.type = binding.typeInputValue.text.toString()
        exercise.muscles = selectedMuscles
    }

    private fun onCreateExerciseButtonClick() {
        setNewExercise()
        lifecycleScope.launch{
            try {
                setSaving(true)
                userContext.saveExercise(exercise)
                userContext.fetchExercisesData()
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@ExerciseActivity,
                    "Error Saving the Exercise", Toast.LENGTH_SHORT).show()
                setSaving(false)
            }
        }
    }

    private fun onAddMuscleButtonClick() {
        if (selectedMuscles.size >= 4) {
            Toast.makeText(this@ExerciseActivity,
                "Max number of muscles reached", Toast.LENGTH_SHORT).show()
            return
        }

        val muscle = binding.muscleEditText.text.toString().uppercase()

        if (muscle.isBlank()) return
        if (!Muscles.values().any { it.name == muscle }) return
        if (selectedMuscles.contains(Muscles.valueOf(muscle))) {
            binding.muscleEditText.text = null
            return
        }

        addChip(muscle)
        selectedMuscles.add(Muscles.valueOf(muscle))
        binding.muscleEditText.text = null

    }

    private fun addChip(muscle: String) {
        val chip = Chip(chipGroup.context)
        chip.text = muscle
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener {
            chipGroup.removeView(chip)
            selectedMuscles.remove(Muscles.valueOf(chip.text.toString()))
        }
        chipGroup.addView(chip)
    }

    private fun setSaving(isSaving: Boolean) {
        binding.createExerciseButton.isEnabled = !isSaving
        binding.addMuscleButton.isEnabled = !isSaving
    }
}