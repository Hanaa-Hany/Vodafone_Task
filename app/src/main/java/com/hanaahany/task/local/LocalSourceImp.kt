package com.hanaahany.task.local

import android.content.Context
import com.hanaahany.task.local.db.RepoDatabase
import com.hanaahany.task.model.entity.RepoDetailsEntity
import kotlinx.coroutines.flow.Flow

class LocalSourceImp  private constructor( context: Context):LocalSource {
    private val repoDao=RepoDatabase.getRepoDetailsDataBaseInstance(context).repoDao()
    override fun getRepoDetails(name: String): Flow<RepoDetailsEntity> {
        return repoDao.getRepoDetails(name)
    }

    override suspend fun saveRepoDetails(repoDetailsEntity: RepoDetailsEntity): Long {
        return repoDao.saveRepoDetails(repoDetailsEntity)
    }

    override suspend fun updateRepoDetails(repoDetailsEntity: RepoDetailsEntity) {
        return repoDao.updateRepoDetails(repoDetailsEntity)
    }

    companion object {
        @Volatile
        private var instance: LocalSourceImp? = null
        fun getLocalSourceImpInstance(context: Context): LocalSourceImp {
            return instance ?: synchronized(this) {
                val instanceHolder = LocalSourceImp(context)
                instance = instanceHolder
                instanceHolder

            }
        }
    }
}