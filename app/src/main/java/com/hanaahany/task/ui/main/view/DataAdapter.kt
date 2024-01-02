package com.hanaahany.task.ui.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanaahany.task.MainActivity
import com.hanaahany.task.databinding.RepoItemBinding
import com.hanaahany.task.model.ui.allrepo.AllRepoItem

class DataAdapter(): PagingDataAdapter<AllRepoItem, DataAdapter.MyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  RepoItemBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }
    object DiffCallback : DiffUtil.ItemCallback<AllRepoItem>() {
        override fun areItemsTheSame(oldItem: AllRepoItem, newItem: AllRepoItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: AllRepoItem, newItem: AllRepoItem): Boolean {
            return oldItem == newItem
        }
    }

    class MyViewHolder(val binding: RepoItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(currentItem: AllRepoItem){

//            binding.gitHubData = data
//
//            // Load the image using Glide
//            val thumbImage = binding.thubmImage
//            val imageUrl = data.owner.avatar_url // Assuming avatar_url is the image URL
//            Glide.with(thumbImage)
//                .load(imageUrl)
//                .circleCrop()
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .error(R.drawable.ic_launcher_foreground)
//                .fallback(R.drawable.ic_launcher_foreground)
//                .into(thumbImage)
            binding.apply {
                tvRepoOwner.text = currentItem.owner?.login

                tvRepoName.text=currentItem.name
                tvRepoDesc.text=currentItem.description
                tvStarCount.text=currentItem.owner?.id.toString()
               // Glide.with(itemView.context).load(currentItem.owner?.avatarUrl).into(imageRepo)


            }


            binding.executePendingBindings()
        }

    }

}