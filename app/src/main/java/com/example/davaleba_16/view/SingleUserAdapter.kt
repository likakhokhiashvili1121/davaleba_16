package com.example.davaleba_16.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.davaleba_16.R
import com.example.davaleba_16.databinding.SingleuserItemBinding
import com.example.davaleba_16.model.UsersData

class SingleUserAdapter: PagingDataAdapter<UsersData.Data, SingleUserAdapter.UserViewHolder>(UserItemCallback) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        SingleuserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class UserViewHolder(private val binding: SingleuserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val user = getItem(bindingAdapterPosition)
            binding.apply {
                Glide.with(binding.root)
                    .load(user?.avatar ?: "")
                    .placeholder(R.mipmap.ic_launcher)
                    .into(phUser)
                tvName.text = user?.firstName.plus(" ").plus(user?.lastName)
                tvEmail.text = user?.email
            }
        }
    }

    private object UserItemCallback: DiffUtil.ItemCallback<UsersData.Data>() {
        override fun areItemsTheSame(oldItem: UsersData.Data, newItem: UsersData.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UsersData.Data, newItem: UsersData.Data): Boolean {
            return oldItem == newItem
        }
    }

}