package com.hanaahany.task.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.repo.Repo
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

    private val _filterRepo = MutableStateFlow<ApiState<List<AllRepoItem>>>(ApiState.Loading)
    val filterRepo: StateFlow<ApiState<List<AllRepoItem>>>
        get() = _filterRepo

    fun getAllRepo(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllRepo().collectLatest { state ->
                _allRepo.value = state
            }
        }
    }
    fun filterRepo(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.searchRepo(query).collectLatest { state->
                _filterRepo.value = state
            }
        }
    }
    init{
        getAllRepo()
    }



}