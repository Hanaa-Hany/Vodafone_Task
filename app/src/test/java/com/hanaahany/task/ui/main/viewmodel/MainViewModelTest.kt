package com.hanaahany.task.ui.main.viewmodel

import com.hanaahany.task.data.source.FakeLocalSourceImp
import com.hanaahany.task.data.source.FakeRemoteSourceImp
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.repo.FakeRepoImp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class MainViewModelTest {
    private lateinit var fakeLocalSource: FakeLocalSourceImp
    private lateinit var fakeRemoteSource: FakeRemoteSourceImp
    private lateinit var repo: FakeRepoImp
    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {
        fakeLocalSource = FakeLocalSourceImp()
        fakeRemoteSource = FakeRemoteSourceImp()

        repo = FakeRepoImp(
            fakeRemoteSource,
            fakeLocalSource
        )
        viewModel = MainViewModel(repo)

    }

    @Test
    fun `getAllRepo emits success state`() = runBlocking {
        // when getAllRepo is called
        fakeRemoteSource.setShouldReturnSuccess(true)
        viewModel.getAllRepo()
        var currentState: ApiState<List<AllRepoItem>>? = null
        val job = launch {
            viewModel.allRepo.collectLatest {
                currentState = it
                print(currentState)
            }
        }
        delay(500)
        assert(currentState is ApiState.Success)
        job.cancel()
    }

    @Test
    fun `getAllRepo emits failure state`() = runBlocking {
        // when getAllRepo is called
        fakeRemoteSource.setShouldReturnSuccess(false)

        viewModel.getAllRepo()
        var currentState: ApiState<List<AllRepoItem>>? = null
        val job = launch {
            viewModel.allRepo.collectLatest {
                currentState = it
                print(currentState)
            }
        }
        delay(500)
        assert(currentState is ApiState.Failure)
        job.cancel()
    }




}