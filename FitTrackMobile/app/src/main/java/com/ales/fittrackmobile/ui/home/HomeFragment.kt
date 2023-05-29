package com.ales.fittrackmobile.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.FragmentHomeBinding
import com.ales.fittrackmobile.ui.RoutineCustomAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var userContext: UserContext

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userContext = UserContext.getInstance()
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {
        }

        createRecyclerView()

        return root
    }

    private fun createRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = RoutineCustomAdapter(userContext.user.routine.toTypedArray())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}