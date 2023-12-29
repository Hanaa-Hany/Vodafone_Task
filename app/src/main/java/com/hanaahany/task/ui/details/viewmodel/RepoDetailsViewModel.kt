package com.hanaahany.task.ui.details.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.ui.repodetails.RepoDetails
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RepoDetailsViewModel (private val repo: Repo) : ViewModel() {
    private val TAG = "TAG RepoDetailsViewModel"

    private val _repoDetails = MutableStateFlow<ApiState<RepoDetails>>(ApiState.Loading)
    val repoDetails:StateFlow<ApiState<RepoDetails>>
        get() = _repoDetails

    fun getRepoDetails(login:String,name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getRepo(login,name).collectLatest { state ->
                _repoDetails.value = state
                Log.i(TAG,state.toString())
            }
        }
    }

}