package com.hanaahany.task.repo

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.allrepo.AllRepoResponseItem
import com.hanaahany.task.model.ui.allrepo.AllRepoItem

fun AllRepoResponse.convertToAllRepoResponseItem():List<AllRepoItem>{
    return this.map {it->
        AllRepoItem(
            name = it.name,
            description = it.description,
            owner = it.owner
        )
    }
}