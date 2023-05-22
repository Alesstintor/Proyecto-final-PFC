package com.ales.fittrackmobile.ui.user_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.HomeActivity
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.FragmentUserPageBinding
import com.ales.fittrackmobile.ui.CustomAdapter

class UserPageFragment : Fragment() {

    private var _binding: FragmentUserPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var editProfile: Button

    private val userContext: UserContext = UserContext()

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

        loadUser()

        val recyclerView = binding.workoutsList

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val data = Array(10){i -> HomeActivity.Exercise("Title $i", "Subtitle $i")}

        recyclerView.adapter = CustomAdapter(data)

        editProfile = binding.editProfileButton

        editProfile.setOnClickListener{onEditProfileButtonClick()}

        return root
    }

    private fun loadUser() {
        binding.username.text = userContext.getUsername()
        binding.heightValue.text = userContext.getHeight().toString()
        binding.weightValue.text = userContext.getWeight().toString()
        binding.ageValue.text = userContext.getAge().toString()
        binding.genreValue.text = userContext.getGenre()
    }

    fun onEditProfileButtonClick() {
        val intent = Intent(this.context, UserPageEditView::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}