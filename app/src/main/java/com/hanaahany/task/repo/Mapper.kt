package com.hanaahany.task.repo

import com.hanaahany.task.model.entity.RepoDetailsEntity
import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.issues.IssuesResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.response.search.SearchResponse
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.model.ui.issues.IssuesItem
import com.hanaahany.task.model.ui.issues.User
import com.hanaahany.task.model.ui.repodetails.RepoDetails
import com.hanaahany.task.model.ui.search.Item

fun AllRepoResponse.convertToAllRepoResponseItem(): List<AllRepoItem> {
    return this.map { it ->
        AllRepoItem(
            name = it.name,
            description = it.description,
            owner = it.owner
        )
    }
}

fun RepoDetailsResponse.convertToRepoDetails(): RepoDetails {

    return RepoDetails(
        this.owner,
        this.name,
        this.description,
        this.forks,
    )

}

fun IssuesResponse.convertToIssuesItem(): List<IssuesItem> {
    return this.map { it ->
        IssuesItem(
            title = it.title,
            state = it.state,
            number = it.number,
            createdAt = it.createdAt,
            body = it.body,
            user = User(avatarUrl = it.user?.avatarUrl, login = it.user?.login)
        )
    }
}

fun RepoDetailsEntity.convertToRepoDetails(): RepoDetails {
    return RepoDetails(
        this.owner,
        this.name,
        this.description,
        this.forks,

        )
}

fun RepoDetails.convertToRepoDetailsEntity(): RepoDetailsEntity {
    return RepoDetailsEntity(
        this.owner,
        this.name!!,
        this.description,
        this.forks

    )
}

//fun SearchResponse.convertSearchResponseToSearchItem(): List<Item> {
//    return this.items?.map {
//        Item(
//            owner = it?.owner,
//            name = it?.name
//        )
//    } ?: emptyList()
//
//
//}
fun SearchResponse.convertSearchResponseToAllRepoItem(): List<AllRepoItem> {
    return this.items?.map {
        AllRepoItem(
            name = it?.name,
            description = it?.description,
            owner = it?.owner
        )
    } ?: emptyList()


}

