package com.hanaahany.task.ui.issues.viewmodel

import com.hanaahany.task.data.source.FakeLocalSourceImp
import com.hanaahany.task.data.source.FakeRemoteSourceImp
import com.hanaahany.task.model.ui.issues.IssuesItem
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.repo.FakeRepoImp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class IssueViewModelTest {

    private lateinit var fakeLocalSource: FakeLocalSourceImp
    private lateinit var fakeRemoteSource: FakeRemoteSourceImp
    private lateinit var repo: FakeRepoImp
    private lateinit var viewModel: IssueViewModel
    @Before
    fun setUp() {
        fakeLocalSource = FakeLocalSourceImp()
        fakeRemoteSource = FakeRemoteSourceImp()

        repo = FakeRepoImp(
            fakeRemoteSource,
            fakeLocalSource
        )
        viewModel= IssueViewModel(repo)

    }

    @Test
    fun `getAllRepo emits success state`() = runBlocking{
        // when getAllRepo is called
        fakeRemoteSource.setShouldReturnSuccess(true)
        viewModel.getIssues("","")
        var currentState : ApiState<List<IssuesItem>>? = null
        val job=launch {
            viewModel.issues.collectLatest{
                currentState=it
                print(currentState)
            }
        }
        delay(500)
        assert(currentState is ApiState.Success)
        job.cancel()
    }

    @Test
    fun `getAllRepo emits faliure state`() = runBlocking{
        // when getAllRepo is called
        fakeRemoteSource.setShouldReturnSuccess(false)
        viewModel.getIssues("","")
        var currentState : ApiState<List<IssuesItem>>? = null
        val job=launch {
            viewModel.issues.collectLatest{
                currentState=it
                print(currentState)
            }
        }
        delay(500)
        assert(currentState is ApiState.Failure)
        job.cancel()
    }
}