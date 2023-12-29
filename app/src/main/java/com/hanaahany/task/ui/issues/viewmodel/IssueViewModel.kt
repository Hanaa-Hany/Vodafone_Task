package com.hanaahany.task.ui.issues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.model.ui.issues.IssuesItem
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class IssueViewModel(private val repo: Repo) : ViewModel() {
    private val TAG = "TAG IssueViewModel"

    private val _issues = MutableStateFlow<ApiState<List<IssuesItem>>>(ApiState.Loading)
    val issues: StateFlow<ApiState<List<IssuesItem>>>
        get() = _issues

    fun getIssues(login:String,name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getIssues(login, name).collectLatest { state ->
                _issues.value = state
            }
        }
    }
}