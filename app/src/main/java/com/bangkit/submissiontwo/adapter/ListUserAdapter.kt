package com.bangkit.submissiontwo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.submissiontwo.DetailActivity
import com.bangkit.submissiontwo.databinding.ItemRowUserBinding
import com.bangkit.submissiontwo.model.User
import com.bumptech.glide.Glide

class ListUserAdapter(private val listUser: List<User>): RecyclerView.Adapter<ListUserAdapter.UserAdapter>() {
    inner class UserAdapter(val binding: ItemRowUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return UserAdapter(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter, position: Int) {
        val user = listUser[position]
        holder.binding.textView.text = user.username
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .circleCrop()
            .into(holder.binding.imageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USER,user.username)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =listUser.size

}