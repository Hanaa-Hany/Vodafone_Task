package com.hanaahany.task.model.ui.search

import com.google.gson.annotations.SerializedName


data class Search(@SerializedName("items")
                  val items: List<Item?>?
                 )
