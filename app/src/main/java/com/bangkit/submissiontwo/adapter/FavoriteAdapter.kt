package com.bangkit.submissiontwo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.submissiontwo.DetailActivity
import com.bangkit.submissiontwo.database.UserDatabase
import com.bangkit.submissiontwo.databinding.ItemRowUserBinding
import com.bangkit.submissiontwo.helper.UserDiffCallback
import com.bumptech.glide.Glide

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val listUser = ArrayList<UserDatabase>()

    fun setListUser(listUser: List<UserDatabase>){
        val diffCallback = UserDiffCallback(this.listUser, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUser.clear()
        this.listUser.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class FavoriteViewHolder(val binding: ItemRowUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
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