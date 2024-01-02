package com.hanaahany.task.ui.issues.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanaahany.task.databinding.IssueItemBinding
import com.hanaahany.task.model.ui.issues.IssuesItem
import com.hanaahany.task.utils.changeDateFormat


class IssueAdapter(var context: Context) :
    ListAdapter<IssuesItem, IssueAdapter.IssueVH>(RecyclerDiffUtilOrdersItem()) {
    private lateinit var binding: IssueItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueVH {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = IssueItemBinding.inflate(inflater, parent, false)
        Log.i("OrderFragmentAdapter", "test1")
        return IssueVH(binding)
    }

    override fun onBindViewHolder(holder: IssueVH, position: Int) {
        val currentItem = getItem(position)
        holder.onBind(currentItem)
        Log.i("OrderFragmentAdapter", "test1")

    }

    inner class IssueVH(var binding: IssueItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun onBind(currentItem: IssuesItem) {

            binding.apply {
                issue = currentItem
                tvDate.text= changeDateFormat(currentItem.createdAt!!)
            }

        }
    }
}


class RecyclerDiffUtilOrdersItem : DiffUtil.ItemCallback<IssuesItem>() {
    override fun areItemsTheSame(
        oldItem: IssuesItem, newItem: IssuesItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: IssuesItem, newItem: IssuesItem
    ): Boolean {
        return oldItem == newItem
    }
}