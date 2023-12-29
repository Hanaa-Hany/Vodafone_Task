package com.hanaahany.task.local

import com.hanaahany.task.model.entity.RepoDetailsEntity
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    fun getRepoDetails(name: String): Flow<RepoDetailsEntity>
    suspend fun saveRepoDetails(repoDetailsEntity: RepoDetailsEntity):Long
    suspend fun updateRepoDetails(repoDetailsEntity: RepoDetailsEntity)
}