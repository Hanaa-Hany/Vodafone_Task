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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

class FakeRepoImp(
    private val fakeRemoteSourceImp: FakeRemoteSourceImp,
    private val fakeLocalSourceImp: FakeLocalSourceImp
) : Repo {
    override suspend fun getAllRepo(): Flow<ApiState<List<AllRepoItem>>> {
        return flow {
            emit(ApiState.Loading)

            val allRepoResponse = fakeRemoteSourceImp.getAllRepo()

            if (allRepoResponse.isSuccessful) {
                val allRepoItems = allRepoResponse.body()
                if (allRepoItems != null) {
                    emit(ApiState.Success(allRepoItems.convertToAllRepoResponseItem()))
                } else {
                    emit(ApiState.Failure("Response body is null"))
                }
            } else {
                emit(ApiState.Failure(allRepoResponse.message()))
            }

        }.catch { e ->
            emit(ApiState.Failure(e.message ?: "An unexpected error occurred"))
        }

    }



    override suspend fun getRepo(login: String, name: String): Flow<ApiState<RepoDetails>> {
        TODO("Not yet implemented")
    }

    override suspend fun getIssues(login: String, name: String): Flow<ApiState<List<IssuesItem>>> {

        return flow {
            emit(ApiState.Loading)

            val issues = fakeRemoteSourceImp.getIssues("","")

            if (issues.isSuccessful) {
                val issueItem = issues.body()
                if (issueItem != null) {
                    emit(ApiState.Success(issueItem.convertToIssuesItem()))
                } else {
                    emit(ApiState.Failure("Response body is null"))
                }
            } else {
                emit(ApiState.Failure(issues.message()))
            }

        }.catch { e ->
            emit(ApiState.Failure(e.message ?: "An unexpected error occurred"))
        }

    }

    override suspend fun searchRepo(query: String): Flow<ApiState<List<AllRepoItem>>> {
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
