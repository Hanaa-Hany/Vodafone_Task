package com.hanaahany.task.remote

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.remote.services.Network
import retrofit2.Response

class RemoteSourceImp private constructor() : RemoteSource {
    private val TAG = "TAG RemoteSourceImp"
    private val network = Network.retrofitService


    override suspend fun getAllRepo(): Response<AllRepoResponse> {
        return Network.retrofitService.getAllRepo()
    }

    override suspend fun getRepo(login: String, name: String): Response<RepoDetailsResponse> {
        return Network.retrofitService.getRepo(login,name)
    }

    companion object {
        @Volatile
        private var instance: RemoteSourceImp? = null
        fun getRemoteSourceImpInstance(): RemoteSourceImp {
            return instance ?: synchronized(this) {
                val instanceHolder = RemoteSourceImp()
                instance = instanceHolder
                instanceHolder

            }
        }
    }


}