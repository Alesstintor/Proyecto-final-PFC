package com.ales.fittrackmobile.ui.user_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.FragmentUserPageBinding
import com.ales.fittrackmobile.adapters.RecordCustomAdapter
import com.ales.fittrackmobile.ui.auth.LoginActivity

class UserPageFragment : Fragment() {

    private var _binding: FragmentUserPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var userContext: UserContext

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPageBinding.inflate(inflater, container, false)

        val root: View = binding.root

        userContext = UserContext.getInstance()

        createRecyclerView()

        loadUser()

        binding.editProfileButton.setOnClickListener{onEditProfileButtonClick()}
        binding.logoutButton.setOnClickListener{onLogoutButtonClick()}

        return root
    }

    private fun onLogoutButtonClick() {
        userContext.logout()
        startActivity(Intent(this.requireContext(), LoginActivity::class.java))
        activity?.finish()
    }

    override fun onResume() {
        super.onResume()
        loadUser()
    }

    private fun loadUser() {
        binding.nameValue.text = userContext.user.name
        binding.heightValue.text = userContext.user.height.toString()
        binding.weightValue.text = userContext.user.weight.toString()
        binding.ageValue.text = userContext.user.age.toString()
        binding.genreValue.text = userContext.user.genre
    }

    private fun createRecyclerView() {
        val recyclerView = binding.workoutsList

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = RecordCustomAdapter(userContext.recordList.orEmpty().toTypedArray())
    }

    private fun onEditProfileButtonClick() {
        startActivity(Intent(this.context, UserPageEditView::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}