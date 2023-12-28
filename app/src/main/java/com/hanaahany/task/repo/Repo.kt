package com.hanaahany.task.repo

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.model.ui.repodetails.RepoDetails
import com.hanaahany.task.remote.ApiState
import kotlinx.coroutines.flow.Flow

interface Repo {

    suspend fun getAllRepo(): Flow<ApiState<List<AllRepoItem>>>
    suspend fun getRepo(login:String,name:String): Flow<ApiState<RepoDetails>>

}