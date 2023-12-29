package com.hanaahany.task.local.db

import androidx.room.*
import com.hanaahany.task.model.entity.RepoDetailsEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface RepoDAO {

    @Query("SELECT * FROM RepoDetails_table WHERE name=:name")
    fun getRepoDetails(name: String): Flow<RepoDetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRepoDetails(repoDetailsEntity: RepoDetailsEntity):Long

    @Update
    suspend fun updateRepoDetails(repoDetailsEntity: RepoDetailsEntity)
}