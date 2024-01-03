package com.hanaahany.task.ui.main.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.hanaahany.task.R
import com.hanaahany.task.databinding.FragmentMainBinding
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
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
        observeRepoListState()



        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val searchText = p0.toString().trim()
                if (searchText.isEmpty()) {
                    observeRepoListState()
                } else {
                    viewModel.filterRepo(searchText)
                    filterList()

                }
                Log.i(TAG, searchText)
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun filterList() {
        collectLatestFlowOnLifecycle(viewModel.filterRepo) { state ->
            when (state) {
                is ApiState.Failure -> {
                    showErrorState()
                    Log.i(TAG,state.msg)
                }

                ApiState.Loading ->{
                    showLoadingState()
                    Log.i(TAG,state.toString())
                }

                is ApiState.Success -> {
                    if (state.data.isEmpty()) {
                        showEmptyState()
                        Log.i(TAG,state.data.toString())
                    } else {
                        showRepoList(state.data)
                        Log.i(TAG,state.data.toString())
                    }
                }
            }
        }
    }

    private fun observeRepoListState() {
        collectLatestFlowOnLifecycle(viewModel.allRepo) { state ->
            when (state) {
                is ApiState.Failure -> showErrorState()

                ApiState.Loading -> showLoadingState()

                is ApiState.Success -> {
                    if (state.data.isEmpty()) {
                        showEmptyState()
                        Log.i(TAG,state.data.toString())

                    } else {
                        showRepoList(state.data)
                    }
                }
            }
        }
    }

    private fun showRepoList(repoList: List<AllRepoItem>) {
        allRepoAdapter = ReposAdapter(requireContext()) { first, second ->
            val action =
                MainFragmentDirections.actionMainFragmentToRepoDetailsFragment(
                    first,
                    second
                )
            Navigation.findNavController(requireView()).navigate(action)
        }
        allRepoAdapter.submitList(repoList)
        binding.recyclerMainRepo.adapter = allRepoAdapter
        binding.recyclerMainRepo.visibility = View.VISIBLE

    }

    private fun showLoadingState() {
        if (binding.etSearch.text?.isNotEmpty() == true) {
            binding.lottiLoad.visibility = View.VISIBLE
            binding.lottiError.visibility = View.GONE
            binding.lottiEmpty.visibility=View.GONE
            binding.fragmentMainShammer.visibility = View.VISIBLE
            binding.fragmentMainShammer.startShimmerAnimation()
        } else {
            // Reset the loading state if the search text is empty
            hideLoadingAndErrorStates()
        }

    }

    private fun showErrorState() {
        binding.lottiError.visibility = View.VISIBLE
        binding.lottiLoad.visibility = View.GONE
        binding.lottiEmpty.visibility=View.GONE
        binding.fragmentMainShammer.visibility = View.GONE
        binding.fragmentMainShammer.stopShimmerAnimation()
    }

    private fun showEmptyState() {
        binding.fragmentMainShammer.stopShimmerAnimation()
        binding.fragmentMainShammer.visibility=View.GONE
        binding.lottiError.visibility=View.GONE
        binding.lottiLoad.visibility=View.GONE
        binding.lottiEmpty.visibility = View.VISIBLE
        binding.recyclerMainRepo.visibility = View.GONE

    }
    private fun hideLoadingAndErrorStates() {
        binding.fragmentMainShammer.stopShimmerAnimation()
        binding.fragmentMainShammer.visibility=View.GONE
        binding.lottiLoad.visibility = View.GONE
        binding.lottiError.visibility = View.GONE
        binding.lottiEmpty.visibility = View.GONE
        binding.recyclerMainRepo.visibility = View.VISIBLE

    }


}




