package com.ales.fittrackmobile.ui.user_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.HomeActivity
import com.ales.fittrackmobile.databinding.FragmentUserPageBinding
import com.ales.fittrackmobile.ui.CustomAdapter

class UserPageFragment : Fragment() {

    private var _binding: FragmentUserPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userPageViewModel =
            ViewModelProvider(this).get(UserPageViewModel::class.java)

        _binding = FragmentUserPageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val username: TextView = binding.username
        userPageViewModel.text.observe(viewLifecycleOwner) {
            username.text = it
        }

        val recyclerView = binding.workoutsList

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val data = Array(10){i -> HomeActivity.Exercise("Title $i", "Subtitle $i")}

        recyclerView.adapter = CustomAdapter(data)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}