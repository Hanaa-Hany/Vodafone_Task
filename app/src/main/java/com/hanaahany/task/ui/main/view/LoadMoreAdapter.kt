package com.hanaahany.task.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanaahany.task.R

class LoadMoreAdapter : LoadStateAdapter<LoadMoreAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.load_more, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        //private val btnTryAgain = view.findViewById<Button>(R.id.btnTryAgain)

        fun bind(states : LoadState){

            progressBar.isVisible = states is LoadState.Loading
            //  btnTryAgain.isVisible = states is LoadState.Loading

        }
    }
}