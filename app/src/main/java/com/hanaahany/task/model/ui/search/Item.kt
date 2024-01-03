package com.hanaahany.task.model.ui.search

import com.google.gson.annotations.SerializedName
import com.hanaahany.task.model.response.repodetails.Owner

data class Item(@SerializedName("owner")
                 val owner: Owner?,
                @SerializedName("name")
                val name: String?)
