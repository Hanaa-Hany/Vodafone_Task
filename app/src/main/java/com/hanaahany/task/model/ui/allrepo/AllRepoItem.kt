package com.hanaahany.task.model.ui.allrepo

import com.google.gson.annotations.SerializedName
import com.hanaahany.task.model.response.allrepo.OwnerResponse

data class AllRepoItem(
    @SerializedName("name")
    val name: String?, @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: OwnerResponse?
)