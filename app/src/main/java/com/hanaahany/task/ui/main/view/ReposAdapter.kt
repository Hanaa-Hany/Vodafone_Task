package com.hanaahany.task.ui.main.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanaahany.task.databinding.RepoItemBinding
import com.hanaahany.task.model.ui.allrepo.AllRepoItem

class ReposAdapter(var context: Context, private val onClick: (String, String) -> Unit) :
    ListAdapter<AllRepoItem, ReposAdapter.ReposVH>(RecyclerDiffUtilOrdersItem()) {
    private lateinit var binding: RepoItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposVH {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = RepoItemBinding.inflate(inflater, parent, false)
        Log.i("OrderFragmentAdapter", "test1")
        return ReposVH(binding)
    }

    override fun onBindViewHolder(holder: ReposVH, position: Int) {
        val currentItem = getItem(position)
        holder.onBind(currentItem)
        Log.i("OrderFragmentAdapter", "test1")

    }

    inner class ReposVH(var binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(currentItem: AllRepoItem) {

            binding.apply {
                repo = currentItem
            }
            itemView.setOnClickListener {
                onClick(currentItem.owner?.login!!, currentItem.name!!)
            }

        }
    }
}

class RecyclerDiffUtilOrdersItem : DiffUtil.ItemCallback<AllRepoItem>() {
    override fun areItemsTheSame(
        oldItem: AllRepoItem, newItem: AllRepoItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AllRepoItem, newItem: AllRepoItem
    ): Boolean {
        return oldItem == newItem
    }
}