package com.ales.fittrackmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
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
    private var routineId = 1L
    private var isNewRoutine = false
    private var activeRoutine = Routine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        isNewRoutine = intent.getBooleanExtra("ISNEW", false)

        createRecyclerView()
        toggleEditMode(isNewRoutine)

        binding.addExerciseButton.setOnClickListener {onAddExerciseButtonClick()}
        binding.saveRoutineButton.setOnClickListener {onSaveRoutineButtonClick()}
    }

    private fun onAddExerciseButtonClick() {

    }

    private fun saveNewRoutine() {
        userContext.user.routine.add(activeRoutine)
    }

    private fun updateRoutine() {
        val routineIterator = userContext.user.routine.listIterator()

        while (routineIterator.hasNext()) {
            val routine = routineIterator.next()
            if (routine.id == routineId) routineIterator.set(activeRoutine)
        }
    }

    private fun onSaveRoutineButtonClick() {
        if (isNewRoutine) saveNewRoutine()
        else updateRoutine()

        lifecycleScope.launch {
            userContext.updateUser()
            toggleEditMode(false)
        }

    }

    private fun toggleEditMode(inEditMode: Boolean) {
        binding.addExerciseButton.visibility = if (inEditMode) View.VISIBLE else View.GONE
        binding.saveRoutineButton.visibility = if (inEditMode) View.VISIBLE else View.GONE
        binding.titleValueEdit.inputType =
            if (inEditMode) InputType.TYPE_CLASS_TEXT else InputType.TYPE_NULL

    }

    private fun createRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(
            this@RoutineActivity, LinearLayoutManager.VERTICAL, false)

        if (isNewRoutine) {
            recyclerView.adapter = ExerciseCustomAdapter(emptyArray())
            return
        }

        routineId = intent.getLongExtra("ROUTINEID", 1)

        activeRoutine = userContext.user.routine.first { routine -> routine.id == routineId }

        binding.titleValueEdit.setText(activeRoutine.name)

        recyclerView.adapter = ExerciseCustomAdapter(activeRoutine.exerciseList.toTypedArray())
    }
}