package com.ales.fittrackmobile.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.adapters.ExerciseCustomAdapter
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityAddExerciseBinding

class AddExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExerciseBinding
    private lateinit var userContext: UserContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        createRecyclerView()

    }

    private fun createRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(
            this@AddExerciseActivity, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = buildAdapter()
    }

    private fun buildAdapter(): ExerciseCustomAdapter {
        val exerciseAdapter = ExerciseCustomAdapter(userContext.exercisesList.toTypedArray())

        exerciseAdapter.setOnItemClickListener(object: ExerciseCustomAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val exerciseId = userContext.exercisesList[position].id
                val intentResult = Intent()
                intentResult.putExtra("EXERCISEID", exerciseId)
                setResult(Activity.RESULT_OK, intentResult)
                finish()
            }
        })

        return exerciseAdapter
    }


}