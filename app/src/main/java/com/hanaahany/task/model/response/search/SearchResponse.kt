package com.hanaahany.task.model.response.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<ItemResponse?>?,
    @SerializedName("total_count")
    val totalCount: Int?
)