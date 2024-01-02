package com.hanaahany.task.repo

import com.hanaahany.task.data.source.FakeData
import com.hanaahany.task.data.source.FakeLocalSourceImp
import com.hanaahany.task.data.source.FakeRemoteSourceImp
import com.hanaahany.task.model.entity.RepoDetailsEntity
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.model.ui.issues.IssuesItem
import com.hanaahany.task.model.ui.repodetails.RepoDetails
import com.hanaahany.task.remote.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepoImp(
    private val fakeRemoteSourceImp: FakeRemoteSourceImp,
    private val fakeLocalSourceImp: FakeLocalSourceImp
) : Repo {
    override suspend fun getAllRepo(): Flow<ApiState<List<AllRepoItem>>> {
        return flowOf(FakeData.fakeAllRepoItem())
    }

    override suspend fun getRepo(login: String, name: String): Flow<ApiState<RepoDetails>> {
        TODO("Not yet implemented")
    }

    override suspend fun getIssues(login: String, name: String): Flow<ApiState<List<IssuesItem>>> {
        TODO("Not yet implemented")
    }

    override fun getRepoDetails(name: String): Flow<RepoDetailsEntity> {
        return flowOf(FakeData.fakeRepoDetailsEntity())
    }

    override suspend fun saveRepoDetails(repoDetailsEntity: RepoDetailsEntity): Long {
        return 1
    }

    override suspend fun updateRepoDetails(repoDetailsEntity: RepoDetailsEntity) {

    }

}
