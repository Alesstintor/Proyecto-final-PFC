package com.ales.fittrackmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.adapters.ExerciseCustomAdapter
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityRoutineBinding

class RoutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineBinding
    private lateinit var userContext: UserContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()
        createRecyclerView()
    }

    private fun createRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this@RoutineActivity, LinearLayoutManager.VERTICAL, false)

        val routineId = intent.getLongExtra("ROUTINEID", 1)

        val routine = userContext.user.routine.first { routine -> routine.id == routineId }

        recyclerView.adapter = ExerciseCustomAdapter(routine.exerciseList.toTypedArray())
    }
}