package com.hanaahany.task.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hanaahany.task.repo.Repo
import com.hanaahany.task.ui.main.viewmodel.MainViewModel

class BaseViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(repo) as T
            else -> throw Throwable("Unsupported view model")
        }
    }
}