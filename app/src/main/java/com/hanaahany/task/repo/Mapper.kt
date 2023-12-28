package com.hanaahany.task.repo

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.allrepo.AllRepoResponseItem
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.model.ui.repodetails.RepoDetails

fun AllRepoResponse.convertToAllRepoResponseItem():List<AllRepoItem>{
    return this.map {it->
        AllRepoItem(
            name = it.name,
            description = it.description,
            owner = it.owner
        )
    }
}

fun RepoDetailsResponse.convertToRepoDetails():RepoDetails{

        return RepoDetails(
            name = name,
            description = description,
            owner = owner,
            forks = forks
        )

}