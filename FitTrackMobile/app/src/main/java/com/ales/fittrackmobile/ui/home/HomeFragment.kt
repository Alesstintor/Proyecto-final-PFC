package com.ales.fittrackmobile.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.FragmentHomeBinding
import com.ales.fittrackmobile.adapters.RoutineCustomAdapter
import com.ales.fittrackmobile.ui.routine.RoutineActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var userContext: UserContext
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userContext = UserContext.getInstance()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        createRecyclerView()

        binding.addRoutineButton.setOnClickListener {onAddRoutineButtonClick()}

        return root
    }

    override fun onResume() {
        super.onResume()
        updateRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateRecyclerView() {
        binding.recyclerView.adapter =
            RoutineCustomAdapter(userContext.user.routine.toTypedArray(), this.requireContext())
    }

    private fun onAddRoutineButtonClick() {
        val intent = Intent(this.context, RoutineActivity::class.java)
        startActivity(intent)
    }

    private fun createRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter =
            RoutineCustomAdapter(userContext.user.routine.toTypedArray(), this.requireContext())
    }
}