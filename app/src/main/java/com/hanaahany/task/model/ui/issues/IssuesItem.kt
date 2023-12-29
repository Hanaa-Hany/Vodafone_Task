package com.hanaahany.task.model.ui.issues

import com.google.gson.annotations.SerializedName

data class IssuesItem(
    @SerializedName("title")
    val title: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("user")
    val user: User?
)
