package com.example.avantsoft.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avantsoft.data.dao.CardListener
import com.example.avantsoft.databinding.RecyclerRowBinding
import com.example.avantsoft.domain.model.UserDataBaseModel


class VerticalAdapter(
    private var list: List<List<UserDataBaseModel>>,
    private var cardListener: CardListener
) : RecyclerView.Adapter<VerticalAdapter.SingleHolder>() {

    inner class SingleHolder(private val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val rvRecyclerRow = binding.rvRecyclerRow

        fun bind(users: List<UserDataBaseModel>) {
            rvRecyclerRow.adapter = HorizontalAdapter(users, cardListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleHolder {
        val inflater =
            RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SingleHolder(inflater)
    }

    override fun onBindViewHolder(holder: SingleHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}