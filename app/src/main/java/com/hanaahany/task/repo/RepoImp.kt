package com.hanaahany.task.repo

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.remote.RemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RepoImp private constructor(
    private val remoteSource: RemoteSource,
) : Repo {
    private val TAG = "TAG RepoImp"

    override suspend fun getAllRepo(): Flow<ApiState<List<AllRepoItem>>> {
        return flow {

            emit(ApiState.Loading)
            val allRepo =
                remoteSource.getAllRepo()
            if (allRepo.isSuccessful) {
                remoteSource.getAllRepo().body()
                    ?.let {
                        emit(ApiState.Success(it.convertToAllRepoResponseItem()))
                    }
            } else {
                emit(ApiState.Failure(allRepo.message()))
            }

        }.catch {
            emit(ApiState.Failure(it.message!!))
        }

    }

    companion object {
        @Volatile
        private var instance: RepoImp? = null
        fun getRepoImpInstance(remoteSource: RemoteSource): RepoImp {
            return instance ?: synchronized(this) {
                val instanceHolder = RepoImp(remoteSource)
                instance = instanceHolder
                instanceHolder

            }
        }
    }
}

