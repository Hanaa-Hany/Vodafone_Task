package com.hanaahany.task.data.source

import com.hanaahany.task.model.entity.RepoDetailsEntity
import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.allrepo.AllRepoResponseItem
import com.hanaahany.task.model.response.allrepo.OwnerResponse
import com.hanaahany.task.model.response.issues.*
import com.hanaahany.task.model.response.repodetails.Owner
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.model.ui.issues.IssuesItem
import com.hanaahany.task.model.ui.issues.User
import com.hanaahany.task.remote.ApiState

object FakeData {

    fun fakeAllRepoResponse(): AllRepoResponse {
        val fakeList = listOf(
            AllRepoResponseItem(archiveUrl = "https://example.com/archive",
                assigneesUrl = "https://example.com/assignees",
                blobsUrl = "https://example.com/blobs",
                branchesUrl = "https://example.com/branches",
                collaboratorsUrl = "https://example.com/collaborators",
                commentsUrl = "https://example.com/comments",
                commitsUrl = "https://example.com/commits",
                compareUrl = "https://example.com/compare",
                contentsUrl = "https://example.com/contents",
                contributorsUrl = "https://example.com/contributors",
                deploymentsUrl = "https://example.com/deployments",
                description = "This is a sample repository",
                downloadsUrl = "https://example.com/downloads",
                eventsUrl = "https://example.com/events",
                fork = false,
                forksUrl = "https://example.com/forks",
                fullName = "exampleuser/sample-repo",
                gitCommitsUrl = "https://example.com/git-commits",
                gitRefsUrl = "https://example.com/git-refs",
                gitTagsUrl = "https://example.com/git-tags",
                hooksUrl = "https://example.com/hooks",
                htmlUrl = "https://github.com/exampleuser/sample-repo",
                id = 123,
                issueCommentUrl = "https://example.com/issue-comments",
                issueEventsUrl = "https://example.com/issue-events",
                issuesUrl = "https://example.com/issues",
                keysUrl = "https://example.com/keys",
                labelsUrl = "https://example.com/labels",
                languagesUrl = "https://example.com/languages",
                mergesUrl = "https://example.com/merges",
                milestonesUrl = "https://example.com/milestones",
                name = "sample-repo",
                nodeId = "abc123",
                notificationsUrl = "https://example.com/notifications",
                owner = fakeOwnerResponse(), // You need to implement generateFakeOwnerResponse() similarly
                `private` = false,
                pullsUrl = "https://example.com/pulls",
                releasesUrl = "https://example.com/releases",
                stargazersUrl = "https://example.com/stargazers",
                statusesUrl = "https://example.com/statuses",
                subscribersUrl = "https://example.com/subscribers",
                subscriptionUrl = "https://example.com/subscription",
                tagsUrl = "https://example.com/tags",
                teamsUrl = "https://example.com/teams",
                treesUrl = "https://example.com/trees",
                url = "https://github.com/exampleuser/sample-repo"
            ),

        )
        return AllRepoResponse().apply { addAll(fakeList) }
    }

    fun fakeAllRepoItem(): ApiState<List<AllRepoItem> >{
        val fakeItems=  List(5) {
            AllRepoItem(

                description = "This is a sample repository",
                name = "sample-repo",
                owner = fakeOwnerResponse(), // You need to implement generateFakeOwnerResponse() similarly

            )
        }
        return ApiState.Success(fakeItems)
    }



    fun fakeOwnerResponse(): OwnerResponse {
        return OwnerResponse(
            avatarUrl = "https://example.com/avatar",
            eventsUrl = "https://example.com/events",
            followersUrl = "https://example.com/followers",
            followingUrl = "https://example.com/following",
            gistsUrl = "https://example.com/gists",
            gravatarId = "abc123",
            htmlUrl = "https://github.com/exampleuser",
            id = 123,
            login = "exampleuser",
            nodeId = "xyz789",
            organizationsUrl = "https://example.com/organizations",
            receivedEventsUrl = "https://example.com/received_events",
            reposUrl = "https://example.com/repos",
            siteAdmin = false,
            starredUrl = "https://example.com/starred",
            subscriptionsUrl = "https://example.com/subscriptions",
            type = "User",
            url = "https://example.com/user"

        )
    }

    fun fakeRepoDetailsEntity():RepoDetailsEntity {

        return RepoDetailsEntity(
            owner = fakeOwner(),
            name = "Fake Repository",
            description = "This is a fake repository for testing purposes",
            forks = 10
        )
    }
    fun fakeOwner(): Owner{
        return Owner( avatarUrl = "https://example.com/avatar",
            eventsUrl = "https://example.com/events",
            followersUrl = "https://example.com/followers",
            followingUrl = "https://example.com/following",
            gistsUrl = "https://example.com/gists",
            gravatarId = "abc123",
            htmlUrl = "https://github.com/exampleuser",
            id = 123,
            login = "exampleuser",
            nodeId = "xyz789",
            organizationsUrl = "https://example.com/organizations",
            receivedEventsUrl = "https://example.com/received_events",
            reposUrl = "https://example.com/repos",
            siteAdmin = false,
            starredUrl = "https://example.com/starred",
            subscriptionsUrl = "https://example.com/subscriptions",
            type = "User",
            url = "https://example.com/user")
    }

    fun fakeIssuesItem(): IssuesItem {
        return IssuesItem(
            title = "Fake Title",
            state = "Open",
            number = 123,
            createdAt = "2022-01-01T12:00:00Z",
            body = "Fake Body",
            user = fakeUser()
        )


      }
    fun fakeUser(): User {
        return User(
            login = "FakeUser",
            avatarUrl = "https://example.com/avatar.jpg"
            // Add other properties as needed
        )
    }

    fun fakeIssuesResponse(): IssuesResponse {
        val fakeIssuesList = mutableListOf<IssuesResponseItem>()
        repeat(5) { // Adjust the number based on how many fake items you want
            fakeIssuesList.add(fakeIssuesResponseItem())
        }
        return IssuesResponse()
    }

    fun fakeIssuesResponseItem(): IssuesResponseItem {
        return IssuesResponseItem(
            activeLockReason = null,
            assignee = null,
            assignees = emptyList(),
            authorAssociation = "MEMBER",
            body = "Fake Issue Body",
            closedAt = null,
            comments = 0,
            commentsUrl = "https://example.com/comments",
            createdAt = "2022-01-01T12:00:00Z",
            draft = false,
            eventsUrl = "https://example.com/events",
            htmlUrl = "https://example.com/issue/1",
            id = 123,
            labels = emptyList(),
            labelsUrl = "https://example.com/labels",
            locked = false,
            milestone = null,
            nodeId = "fakeNodeId",
            number = 1,
            performedViaGithubApp = null,
            pullRequest = fakePullRequest(),
            reactions = fakeReactions(),
            repositoryUrl = "https://example.com/repo",
            state = "open",
            stateReason = null,
            timelineUrl = "https://example.com/timeline",
            title = "Fake Issue Title",
            updatedAt = "2022-01-02T12:00:00Z",
            url = "https://example.com/issue/1",
            user = fakeUserResponse()
        )
    }

    fun fakePullRequest(): PullRequest {
        return PullRequest(
            diffUrl = "https://example.com/diff",
            htmlUrl = "https://example.com/pull/1",
            mergedAt = null,
            patchUrl = "https://example.com/patch",
            url = "https://example.com/pull/1"
        )
    }

    fun fakeReactions(): Reactions {
        return Reactions(
            confused = 0,
            eyes = 0,
            heart = 0,
            hooray = 0,
            laugh = 0,
            rocket = 0,
            totalCount = 0,
            url = "https://example.com/reactions",
            x1 = 0,
            x2 = 0
        )
    }

    fun fakeUserResponse(): UserResponse {
        return UserResponse(
            avatarUrl = "https://example.com/avatar.jpg",
            eventsUrl = "https://example.com/events",
            followersUrl = "https://example.com/followers",
            followingUrl = "https://example.com/following",
            gistsUrl = "https://example.com/gists",
            gravatarId = "fakeGravatarId",
            htmlUrl = "https://example.com/user",
            id = 456,
            login = "fakeuser",
            nodeId = "fakeNodeId",
            organizationsUrl = "https://example.com/orgs",
            receivedEventsUrl = "https://example.com/received-events",
            reposUrl = "https://example.com/repos",
            siteAdmin = false,
            starredUrl = "https://example.com/starred",
            subscriptionsUrl = "https://example.com/subscriptions",
            type = "User",
            url = "https://example.com/user"
        )
    }
}