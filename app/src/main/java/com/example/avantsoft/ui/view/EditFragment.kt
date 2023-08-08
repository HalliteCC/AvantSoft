package com.example.avantsoft.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.avantsoft.databinding.FragmentUserEditBinding
import com.example.avantsoft.domain.model.UserDataBaseModel
import com.example.avantsoft.ui.viewmodel.UserEditVM
import com.example.avantsoft.utils.Const


class EditFragment : Fragment() {

    private lateinit var binding: FragmentUserEditBinding
    private lateinit var viewModel: UserEditVM

    private var userId = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserEditBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(UserEditVM::class.java)

        editUser()
        observer()
        loadData()

        return root
    }

    private fun editUser() {
        binding.buttonSave.setOnClickListener {
            val userName = binding.name.text.toString()
            var email = binding.email.text.toString()
            val age = binding.age.text.toString()
            if (userName == "") {
                Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show()
            } else {

                val model = UserDataBaseModel(
                    id = userId,
                    name = userName,
                    email = email,
                    age = age
                )


                toast(model)
            }
        }
    }

    private fun loadData() {
        val bundle = arguments
        if (bundle != null) {
            userId = bundle.getInt(Const.USER.USER_ID)
            viewModel.get(userId)
        }
    }


    private fun observer() {
        viewModel.userSave.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.name.setText(it.name)
                binding.email.setText(it.email)
                binding.age.setText(it.age)
                userId = it.id
            }
        }
    }

    private fun toast(user: UserDataBaseModel) {

        val name = binding.name.text.toString()
        val email = binding.email.text.toString()
        val age = binding.age.text.toString()


        if (userId != 0 && name != "" && email != "" && age != "") {
            viewModel.update(user)
            Toast.makeText(context, "Item Modified", Toast.LENGTH_SHORT).show()
            backImmediate()
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun backImmediate() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStackImmediate()
    }
}