package com.ales.fittrackmobile.ui.routine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.adapters.ExerciseCustomAdapter
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityRoutineBinding
import com.ales.fittrackmobile.entities.Exercise
import com.ales.fittrackmobile.entities.Routine
import com.ales.fittrackmobile.ui.exercise.AddExerciseActivity
import kotlinx.coroutines.launch

class RoutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineBinding
    private lateinit var userContext: UserContext
    private lateinit var getExerciseToAdd : ActivityResultLauncher<Intent>
    private lateinit var exerciseAdapter: ExerciseCustomAdapter
    private var routineId = -1L
    private var activeRoutine = Routine()
    private var isNewRoutine = false
    private var isEditing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        routineId = intent.getLongExtra("ROUTINEID", -1)
        isNewRoutine = (routineId == -1L)

        configureInitialState()

        title = activeRoutine.name

        getExerciseToAdd = makeRegisterForActivityResult()

        binding.titleValueEdit.setText(activeRoutine.name)
        binding.titleValue.text = activeRoutine.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.addExerciseButton.setOnClickListener {onAddExerciseButtonClick()}
        binding.saveRoutineButton.setOnClickListener {onSaveRoutineButtonClick()}
        binding.editButton.setOnClickListener{toggleEditMode(true)}
        binding.deleteRoutineButton.setOnClickListener{onDeleteRoutineButtonClick()}
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (isEditing && !isNewRoutine) {
                    toggleEditMode(false)
                    return true
                }
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configureInitialState() {
        if (isNewRoutine) {
            createRecyclerView(emptyList<Exercise>().toMutableList())
            activeRoutine.name = "Routine"
        } else {

            val userRoutine = userContext.user.routine.first{ routine -> routine.id == routineId }
            setActiveRoutine(userRoutine)

            createRecyclerView(activeRoutine.exerciseList)
        }

        toggleEditMode(isNewRoutine)
    }

    private fun onDeleteRoutineButtonClick() {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Delete Routine")
        builder.setMessage("Are you sure you want to delete the routine?")

        builder.setPositiveButton("Delete") { _, _ ->
            deleteRoutine()
        }

        builder.setNegativeButton("Cancel") { _, _ ->
            return@setNegativeButton
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteRoutine() {
        setSavingMode(true)
        lifecycleScope.launch {
            try {
                userContext.user.routine.removeIf { routine -> routine.id == activeRoutine.id }
                userContext.updateUser()
                finish()

            } catch (e: Exception) {
                Toast.makeText(this@RoutineActivity,
                    "Error while deleting the routine", Toast.LENGTH_SHORT).show()

                setSavingMode(false)
            }
        }
    }

    private fun setActiveRoutine(routine: Routine) {
        activeRoutine.id = routine.id
        activeRoutine.name = routine.name
        activeRoutine.exerciseList = routine.exerciseList.toMutableList()
        activeRoutine.description = routine.description
    }

    private fun makeRegisterForActivityResult(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            val exerciseId = it.data?.getLongExtra("EXERCISEID", -1)
            if (exerciseId != -1L) {
                val exerciseToAdd =
                    userContext.exercisesList.find { exercise -> exercise.id == exerciseId }

                activeRoutine.exerciseList.add(exerciseToAdd)
                updateRecyclerView()
            }
        }
    }

    private fun onAddExerciseButtonClick() {
        getExerciseToAdd.launch(Intent(this@RoutineActivity, AddExerciseActivity::class.java))
    }

    private fun onSaveRoutineButtonClick() {
        if (binding.titleValueEdit.text.isNotEmpty()) {
            activeRoutine.name = binding.titleValueEdit.text.toString()
        }

        if (!isNewRoutine) updateExistingRoutine()

        persistRoutine()

    }

    private fun updateExistingRoutine() {
        val routineIterator = userContext.user.routine.listIterator()

        while (routineIterator.hasNext()) {
            val routine = routineIterator.next()
            if (routine.id == routineId) routineIterator.set(activeRoutine)
        }
    }

    private fun persistRoutine() {
        lifecycleScope.launch {
            try {
                setSavingMode(true)
                val newRoutine = userContext.saveRoutine(activeRoutine)
                if (isNewRoutine) saveNewRoutineToUser(newRoutine)
                userContext.updateUser()
                binding.titleValue.text = activeRoutine.name
                toggleEditMode(false)
            } catch (e: Exception) {
                Toast.makeText(this@RoutineActivity,
                    "Error Saving the Routine", Toast.LENGTH_SHORT).show()
            }
            setSavingMode(false)
        }
    }

    private fun saveNewRoutineToUser(newRoutine: Routine) {
        userContext.user.routine.add(newRoutine)
    }

    private fun setSavingMode(isSaving: Boolean) {
        binding.saveRoutineButton.isEnabled = !isSaving
        binding.addExerciseButton.isEnabled = !isSaving
        binding.deleteRoutineButton.isEnabled = !isSaving
    }

    private fun toggleEditMode(inEditMode: Boolean) {
        isEditing = inEditMode
        binding.titleValue.visibility = if (inEditMode) View.GONE else View.VISIBLE
        binding.editButton.visibility = if (inEditMode) View.GONE else View.VISIBLE

        binding.titleValueEdit.visibility = if (inEditMode) View.VISIBLE else View.GONE
        binding.addExerciseButton.visibility = if (inEditMode) View.VISIBLE else View.GONE
        binding.saveRoutineButton.visibility = if (inEditMode) View.VISIBLE else View.GONE
        binding.deleteRoutineButton.visibility =
            if (inEditMode && !isNewRoutine) View.VISIBLE else View.GONE

        showDeleteButtons(inEditMode)

    }

    private fun createRecyclerView(exerciseList: MutableList<Exercise>) {
        val recyclerView = binding.recyclerView

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(
            this@RoutineActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = buildAdapter(exerciseList)
    }

    private fun buildAdapter(exerciseList: MutableList<Exercise>): ExerciseCustomAdapter {
        val adapter = ExerciseCustomAdapter(exerciseList.toMutableList())
        adapter.setOnDeleteItemClickListener(object: ExerciseCustomAdapter.OnDeleteItemClickListener {
            override fun onDeleteItemClick(position: Int) {
                activeRoutine.exerciseList.removeAt(position)
                updateRecyclerView()
            }

        })
        exerciseAdapter = adapter
        return adapter
    }

    private fun updateRecyclerView() {
        exerciseAdapter.updateItems(activeRoutine.exerciseList)
    }

    private fun showDeleteButtons(show: Boolean) {
        exerciseAdapter.setDeleteButtonVisible(show)
        updateRecyclerView()
    }
}