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
    private var repoList: List<AllRepoItem> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeIssueListState()



        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val searchText = p0.toString().trim()
                if (searchText.isEmpty()){
                    observeIssueListState()
                }else{
                    viewModel.filterRepo(searchText)
                }


                Log.i(TAG,searchText)
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }
        )

        filterList()
    }

    private fun filterList() {
        collectLatestFlowOnLifecycle(viewModel.filterRepo) { state ->
            when (state) {
                is ApiState.Failure -> {

                }

                ApiState.Loading -> {

                }

                is ApiState.Success -> {
                    Log.i(TAG, "observeProductListState: success ${state.data}")
                    if (state.data.isEmpty()) {

                    } else {
                        //cartAdapter.submitList
                        Log.i(TAG, "" + state.data)

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
                    binding.fragmentMainShammer.visibility = View.VISIBLE
                    binding.fragmentMainShammer.startShimmerAnimation()
                }

                is ApiState.Success -> {
                    binding.fragmentMainShammer.visibility = View.GONE
                    binding.lottiLoad.visibility = View.GONE
                    binding.lottiError.visibility = View.GONE
                    Log.i(TAG, "observeProductListState: success ${state.data}")
                    if (state.data.isEmpty()) {

                        binding.fragmentMainShammer.stopShimmerAnimation()
                        binding.lottiEmpty.visibility = View.VISIBLE
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
