package com.hanaahany.task.repo

import com.hanaahany.task.data.source.FakeData
import com.hanaahany.task.data.source.FakeLocalSourceImp
import com.hanaahany.task.data.source.FakeRemoteSourceImp
import com.hanaahany.task.model.entity.RepoDetailsEntity
import com.hanaahany.task.remote.ApiState
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime
import org.hamcrest.CoreMatchers.containsString


@ExperimentalCoroutinesApi
@ExperimentalTime
@RunWith(MockitoJUnitRunner::class)
class RepoTest {
    private lateinit var fakeLocalSource: FakeLocalSourceImp
    private lateinit var fakeRemoteSource: FakeRemoteSourceImp
    private lateinit var repo: FakeRepoImp

    @Before
    fun setUp() {
        fakeLocalSource = FakeLocalSourceImp()
        fakeRemoteSource = FakeRemoteSourceImp()

        repo = FakeRepoImp(
            fakeRemoteSource,
            fakeLocalSource
        )
    }



    @Test
    fun getAllRepo_AllRepo() = runBlocking {
        val result = repo.getAllRepo()
        result.collectLatest {
            when (it) {
                is ApiState.Loading -> {

                }
                is ApiState.Failure -> {

                }
                is ApiState.Success -> {

                    assertEquals(FakeData.fakeAllRepoResponse().convertToAllRepoResponseItem(), it.data)
                }
            }

        }

    }

    @Test
    fun getRepoDetails_RepoDetailsCached() = runBlocking {
        // Given: Insert repo details
        val fakeRepoDetails = FakeData.fakeRepoDetailsEntity()
        repo.saveRepoDetails(fakeRepoDetails)

        // When: Retrieve repo details
        var result: RepoDetailsEntity? = null
        repo.getRepoDetails("").collect {
            result = it
        }

        // Then
        TestCase.assertNotNull(result)
        TestCase.assertEquals(fakeRepoDetails.name, result?.name)



    }

    @Test
    fun insertRepo_RepoDetailsCached() = runBlocking {

        // Given: Insert repo details
        val fakeRepoDetails = FakeData.fakeRepoDetailsEntity()
        repo.saveRepoDetails(fakeRepoDetails)

        // When: Retrieve repo details
        var result: RepoDetailsEntity? = null
        repo.getRepoDetails("").collect {
            result = it
            print(result)
        }

        // Then
        TestCase.assertNotNull(result)
        assertThat(result?.name, containsString(fakeRepoDetails.name))


    }

}