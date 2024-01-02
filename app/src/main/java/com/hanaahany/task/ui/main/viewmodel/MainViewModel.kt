package com.hanaahany.task.ui.main.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.allrepo.AllRepoResponseItem
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.repo.Repo
import com.hanaahany.task.ui.main.view.DataAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo) : ViewModel() {
    private val TAG = "TAG MainViewModel"

    private val _allRepo = MutableStateFlow<ApiState<List<AllRepoItem>>>(ApiState.Loading)
    val allRepo: StateFlow<ApiState<List<AllRepoItem>>>
        get() = _allRepo

    fun getAllRepo(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllRepo().collectLatest { state ->
                _allRepo.value = state
            }
        }
    }

//    var dataAdapter: DataAdapter = DataAdapter()
//
//    val repositories = Pager(PagingConfig(pageSize = 1)) {
//        GithubPagingSource(repo)
//    }.flow.cachedIn(viewModelScope)
//
//
//
//    fun setAdapterData(data: PagingData<AllRepoItem>) {
//        viewModelScope.launch {
//            dataAdapter.submitData(data)
//        }
//    }

}