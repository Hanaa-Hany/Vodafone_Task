package com.hanaahany.task.data.source

import com.hanaahany.task.local.LocalSource
import com.hanaahany.task.model.entity.RepoDetailsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalSourceImp: LocalSource {
    override fun getRepoDetails(name: String): Flow<RepoDetailsEntity> {
        return flow { FakeData.fakeRepoDetailsEntity()}
    }

    override suspend fun saveRepoDetails(repoDetailsEntity: RepoDetailsEntity): Long {
        return 1
    }

    override suspend fun updateRepoDetails(repoDetailsEntity: RepoDetailsEntity) {
        TODO("Not yet implemented")
    }
}