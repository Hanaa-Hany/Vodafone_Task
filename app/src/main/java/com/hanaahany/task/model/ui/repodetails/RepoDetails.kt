package com.hanaahany.task.model.ui.repodetails

import com.google.gson.annotations.SerializedName
import com.hanaahany.task.model.response.allrepo.OwnerResponse

data class RepoDetails(
    @SerializedName("owner") val owner: OwnerResponse?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("forks")
    val forks: Int?
)
