package com.hanaahany.task.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.hanaahany.task.R
import com.hanaahany.task.databinding.FragmentMainBinding
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.ui.base.BaseFragment
import com.hanaahany.task.ui.main.viewmodel.MainViewModel


class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
    private val TAG = "TAG MainFragment"
    override val layoutIdFragment = R.layout.fragment_main
    override fun getViewModelClass() = MainViewModel::class.java
    private lateinit var allRepoAdapter: ReposAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllRepo()
        observeIssueListState()


    }

    private fun observeIssueListState() {
        collectLatestFlowOnLifecycle(viewModel.allRepo) { state ->
            when (state) {
                is ApiState.Failure -> {
                    Log.i(TAG, "observeProductListState: failure ${state.msg}")
                }

                ApiState.Loading -> {
                    Log.i(TAG, "observeProductListState: loading...")
                }

                is ApiState.Success -> {
                    Log.i(TAG, "observeProductListState: success ${state.data}")
                    if (state.data.isEmpty()) {

                    } else {
                        //cartAdapter.submitList
                        Log.i(TAG,""+state.data)

                        allRepoAdapter = ReposAdapter(requireContext()) {first,second->
                            val action=MainFragmentDirections.actionMainFragmentToRepoDetailsFragment(first,second)
                            Navigation.findNavController(requireView()).navigate(action)
                        }

                        allRepoAdapter.submitList(state.data)
                        binding.recyclerMainRepo.adapter = allRepoAdapter

                    }
                }
            }
        }
    }
}