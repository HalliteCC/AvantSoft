package com.example.avantsoft.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.avantsoft.R
import com.example.avantsoft.data.dao.CardListener
import com.example.avantsoft.databinding.FragmentUiHomeBinding
import com.example.avantsoft.domain.model.UserDataBaseModel
import com.example.avantsoft.ui.adapter.VerticalAdapter
import com.example.avantsoft.ui.viewmodel.UiViewModel
import com.example.avantsoft.utils.Const

class UiHome : Fragment(), CardListener {

    private lateinit var userAdapter: VerticalAdapter
    private lateinit var viewModel: UiViewModel


    private var _binding: FragmentUiHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(UiViewModel::class.java)

        _binding = FragmentUiHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and Adapter
        userAdapter = VerticalAdapter(emptyList(), this)
        binding.rvUiHome.adapter = userAdapter

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsersFromDatabase()
    }

    private fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner) {
            showUsersList(it)
        }

    }

    private fun showUsersList(users: List<UserDataBaseModel>) {
        val chunkedUsers = users.chunked(10)
        // Update the Adapter data with the new list of users
        binding.rvUiHome.adapter = VerticalAdapter(chunkedUsers, this)
    }


    override fun onEditClick(id: Int) {
        val bundle = bundleOf(Const.USER.USER_ID to id)
        Navigation.findNavController(binding.root)
            .navigate(R.id.editFragment, bundle)
    }


    override fun onDelete(user: Int) {
        viewModel.delete(user)
        viewModel.getUsersFromDatabase()
        Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
    }
}