package com.hanaahany.task.repo

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.remote.ApiState
import kotlinx.coroutines.flow.Flow

interface Repo {

    suspend fun getAllRepo(): Flow<ApiState<List<AllRepoItem>>>
}