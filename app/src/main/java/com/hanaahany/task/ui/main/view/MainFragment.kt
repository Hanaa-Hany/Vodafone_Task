package com.hanaahany.task.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanaahany.task.R
import com.hanaahany.task.databinding.FragmentMainBinding
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.ui.base.BaseFragment
import com.hanaahany.task.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
    private val TAG = "TAG MainFragment"
    override val layoutIdFragment = R.layout.fragment_main
    override fun getViewModelClass() = MainViewModel::class.java
    private lateinit var allRepoAdapter: ReposAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.recyclerMainRepo.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            val decoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
//            addItemDecoration(decoration)
//            adapter = viewModel.dataAdapter.withLoadStateFooter(LoadMoreAdapter())
//        }
//
//        viewModel.repositories
//        initObserver()
//
//    }
//
//    private fun initObserver() {
//
//        lifecycleScope.launch{
//            viewModel.repositories.collect {
//                viewModel.setAdapterData(it)
//                Log.i(TAG,it.toString())
//            }
//        }
//
//        lifecycleScope.launchWhenCreated {
//            viewModel.dataAdapter.loadStateFlow.collect{
//                val state = it.refresh
//                binding.progressbar.isVisible = state is LoadState.Loading
//            }
//        }
//
//    }
//
//
        viewModel.getAllRepo()
        observeIssueListState()


    }

    private fun observeIssueListState() {
        collectLatestFlowOnLifecycle(viewModel.allRepo) { state ->
            when (state) {
                is ApiState.Failure -> {
                    Log.i(TAG, "observeProductListState: failure ${state.msg}")
                    binding.lottiError.visibility = View.VISIBLE
                    binding.lottiLoad.visibility = View.GONE
                }

                ApiState.Loading -> {
                    Log.i(TAG, "observeProductListState: loading...")
                    binding.lottiLoad.visibility = View.GONE
                    binding.lottiError.visibility = View.GONE
                    binding.fragmentMainShammer.visibility=View.VISIBLE
                    binding.fragmentMainShammer.startShimmerAnimation()
                }

                is ApiState.Success -> {
                    binding.fragmentMainShammer.visibility=View.GONE
                    binding.lottiLoad.visibility = View.GONE
                    binding.lottiError.visibility = View.GONE
                    Log.i(TAG, "observeProductListState: success ${state.data}")
                    if (state.data.isEmpty()) {

                        binding.fragmentMainShammer.stopShimmerAnimation()
                        binding.lottiEmpty.visibility=View.VISIBLE
                    } else {
                        //cartAdapter.submitList
                        Log.i(TAG, "" + state.data)
                        binding.fragmentMainShammer.stopShimmerAnimation()
                        allRepoAdapter = ReposAdapter(requireContext()) { first, second ->
                            val action =
                                MainFragmentDirections.actionMainFragmentToRepoDetailsFragment(
                                    first,
                                    second
                                )
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
