package com.ales.fittrackmobile.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.adapters.ExerciseCustomAdapter
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityAddExerciseBinding
import com.ales.fittrackmobile.entities.Exercise

class AddExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExerciseBinding
    private lateinit var userContext: UserContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        createRecyclerView()

         supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.createExerciseButton.setOnClickListener {onCreateExerciseButtonClick()}

    }

    override fun onResume() {
        updateRecyclerView()
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                setResult(RESULT_OK)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onCreateExerciseButtonClick() {
        startActivity(Intent(this@AddExerciseActivity, ExerciseActivity::class.java))
    }

    private fun createRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(
            this@AddExerciseActivity, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = buildAdapter(userContext.exercisesList.toTypedArray())
    }

    private fun updateRecyclerView() {
        binding.recyclerView.adapter = buildAdapter(userContext.exercisesList.toTypedArray())
    }

    private fun buildAdapter(exerciseArray: Array<Exercise>): ExerciseCustomAdapter {
        val exerciseAdapter = ExerciseCustomAdapter(exerciseArray)

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