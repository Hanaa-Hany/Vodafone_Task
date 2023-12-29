package com.hanaahany.task.model.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hanaahany.task.model.response.repodetails.Owner

@Entity(tableName = "RepoDetails_table")
data class RepoDetailsEntity(
    val owner: Owner?,
    @PrimaryKey
    @NonNull
    val name: String,
    val description: String?,
    val forks: Int?
)
