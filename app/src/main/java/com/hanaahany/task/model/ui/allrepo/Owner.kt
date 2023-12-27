package com.hanaahany.task.model.ui.allrepo

import com.google.gson.annotations.SerializedName

data class Owner( @SerializedName("id")
                  val id: Int?,
                  @SerializedName("login")
                  val login: String?)
