package com.hanaahany.task.repo

import android.widget.Toast
import com.hanaahany.task.local.LocalSource
import com.hanaahany.task.model.entity.RepoDetailsEntity
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.model.ui.issues.IssuesItem
import com.hanaahany.task.model.ui.repodetails.RepoDetails
import com.hanaahany.task.model.ui.search.Item
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.remote.RemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RepoImp private constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
) : Repo {
    private val TAG = "TAG RepoImp"

    override suspend fun getAllRepo(): Flow<ApiState<List<AllRepoItem>>> {
        return flow {
            emit(ApiState.Loading)

            val allRepoResponse = remoteSource.getAllRepo()

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
        return flow {
            emit(ApiState.Loading)
            val allRepo =
                remoteSource.getRepo(login,name)
            if (allRepo.isSuccessful) {
                remoteSource.getRepo(login,name).body()
                    ?.let {
                        emit(ApiState.Success(it.convertToRepoDetails()))
                    }
            } else {
                emit(ApiState.Failure(allRepo.message()))
            }

        }.catch {
            emit(ApiState.Failure(it.message!!))

        }
    }

    override suspend fun getIssues(login: String, name: String): Flow<ApiState<List<IssuesItem>>> {
        return flow {

            emit(ApiState.Loading)
            val allRepo =
                remoteSource.getIssues(login,name)
            if (allRepo.isSuccessful) {
                remoteSource.getIssues(login,name).body()
                    ?.let {
                        emit(ApiState.Success(it.convertToIssuesItem()))
                    }
            } else {
                emit(ApiState.Failure(allRepo.message()))
            }

        }.catch {
            emit(ApiState.Failure(it.message!!))
        }
    }

    override suspend fun searchRepo(query: String): Flow<ApiState<List<AllRepoItem>>> {
        return flow {
            emit(ApiState.Loading)
            val filterAllRepo =
                remoteSource.searchRepo(query)
            if (filterAllRepo.isSuccessful) {
                remoteSource.searchRepo(query).body()
                    ?.let {
                        emit(ApiState.Success(it.convertSearchResponseToAllRepoItem()))
                    }
            } else {
                emit(ApiState.Failure(filterAllRepo.message()))
            }

        }.catch {
            emit(ApiState.Failure(it.message!!))

        }
    }

    override fun getRepoDetails(name: String): Flow<RepoDetailsEntity> {
        return localSource.getRepoDetails(name)

    }

    override suspend fun saveRepoDetails(repoDetailsEntity: RepoDetailsEntity): Long {
        return localSource.saveRepoDetails(repoDetailsEntity)
    }

    override suspend fun updateRepoDetails(repoDetailsEntity: RepoDetailsEntity) {
        return localSource.updateRepoDetails(repoDetailsEntity)
    }

    companion object {
        @Volatile
        private var instance: RepoImp? = null
        fun getRepoImpInstance(remoteSource: RemoteSource,localSource: LocalSource): RepoImp {
            return instance ?: synchronized(this) {
                val instanceHolder = RepoImp(remoteSource,localSource)
                instance = instanceHolder
                instanceHolder

            }
        }
    }
}

