package com.example.avantsoft.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.avantsoft.data.dao.CardListener
import com.example.avantsoft.databinding.HomeRowBinding
import com.example.avantsoft.domain.model.UserDataBaseModel

class HorizontalAdapter(
    private var userList: List<UserDataBaseModel>,
    private var  listener: CardListener
) :
    RecyclerView.Adapter<HorizontalAdapter.UserViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = HomeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }


    inner class UserViewHolder(
        private val binding: HomeRowBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserDataBaseModel) {
            binding.name.text = user.name
            binding.email.text = user.email
            binding.id.text = user.id.toString()
            binding.age.text = user.age

            binding.card.setOnClickListener {
                listener.onEditClick(user.id)
            }

            binding.card.setOnLongClickListener {
                AlertDialog.Builder(itemView.context)
                    .setTitle("Delete User")
                    .setMessage("Do you want to remove this user?")
                    .setPositiveButton("YES") { dialog, which -> listener.onDelete(user.id) }
                    .setNegativeButton("NO", null)
                    .create()
                    .show()
                true

            }

        }
    }
}