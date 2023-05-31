package com.ales.fittrackmobile.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.adapters.ExerciseCustomAdapter
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityRoutineBinding
import com.ales.fittrackmobile.entities.Routine
import kotlinx.coroutines.launch

class RoutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineBinding
    private lateinit var userContext: UserContext
    private var routineId = -1L
    private var isNewRoutine = false
    private var activeRoutine = Routine()
    private lateinit var getExerciseToAdd : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        isNewRoutine = intent.getBooleanExtra("ISNEW", false)

        createRecyclerView()
        toggleEditMode(isNewRoutine)
        getExerciseToAdd = makeRegisterForActivityResult()

        binding.addExerciseButton.setOnClickListener {onAddExerciseButtonClick()}
        binding.saveRoutineButton.setOnClickListener {onSaveRoutineButtonClick()}
        binding.editButton.setOnClickListener{toggleEditMode(true)}
    }

    private fun makeRegisterForActivityResult(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            val exerciseId = it.data?.getLongExtra("EXERCISEID", -1)
            if (exerciseId != -1L) {
                val exerciseToAdd =
                    userContext.exercisesList.find { exercise -> exercise.id == exerciseId }

                if (exerciseToAdd != null) {
                    activeRoutine.exerciseList.add(exerciseToAdd)
                    updateRecyclerView()
                }
            }
        }
    }

    private fun onAddExerciseButtonClick() {
        getExerciseToAdd.launch(Intent(this@RoutineActivity, AddExerciseActivity::class.java))
    }

    private fun saveNewRoutine(newRoutine: Routine) {
        userContext.user.routine.add(newRoutine)
    }

    private fun updateExistingRoutine() {
        val routineIterator = userContext.user.routine.listIterator()

        while (routineIterator.hasNext()) {
            val routine = routineIterator.next()
            if (routine.id == routineId) routineIterator.set(activeRoutine)
        }
    }

    private fun onSaveRoutineButtonClick() {
        if (binding.titleValueEdit.text.isNotEmpty()) {
            activeRoutine.name = binding.titleValueEdit.text.toString()
        }

        if (!isNewRoutine) updateExistingRoutine()

        lifecycleScope.launch {
            try {
            val newRoutine = userContext.saveRoutine(activeRoutine)
            if (isNewRoutine) saveNewRoutine(newRoutine)
            userContext.updateUser()
            toggleEditMode(false)
            } catch (e: Exception) {
                Toast.makeText(this@RoutineActivity,
                    "Error Saving the Routine", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun toggleEditMode(inEditMode: Boolean) {
        binding.titleValue.visibility = if (inEditMode) View.GONE else View.VISIBLE
        binding.editButton.visibility = if (inEditMode) View.GONE else View.VISIBLE

        binding.titleValueEdit.visibility = if (inEditMode) View.VISIBLE else View.GONE
        binding.addExerciseButton.visibility = if (inEditMode) View.VISIBLE else View.GONE
        binding.saveRoutineButton.visibility = if (inEditMode) View.VISIBLE else View.GONE

    }

    private fun createRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(
            this@RoutineActivity, LinearLayoutManager.VERTICAL, false)

        if (isNewRoutine) {
            recyclerView.adapter = ExerciseCustomAdapter(emptyArray())
            activeRoutine.name = "Routine"
            return
        }

        routineId = intent.getLongExtra("ROUTINEID", -1)

        activeRoutine = userContext.user.routine.first { routine -> routine.id == routineId }

        binding.titleValueEdit.setText(activeRoutine.name)
        binding.titleValue.text = activeRoutine.name

        recyclerView.adapter = ExerciseCustomAdapter(activeRoutine.exerciseList.toTypedArray())
    }

    private fun updateRecyclerView() {
        binding.recyclerView.adapter = ExerciseCustomAdapter(activeRoutine.exerciseList.toTypedArray())
    }
}