package com.hanaahany.task.ui.issues.view

import android.os.Bundle
import android.util.Log
import android.view.View
import com.hanaahany.task.R
import com.hanaahany.task.databinding.FragmentIssueBinding
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.ui.base.BaseFragment
import com.hanaahany.task.ui.issues.viewmodel.IssueViewModel

class IssueFragment : BaseFragment<FragmentIssueBinding, IssueViewModel>() {
    private val TAG = "TAG RepoDetailsFragment"
    override val layoutIdFragment = R.layout.fragment_issue
    override fun getViewModelClass() = IssueViewModel::class.java
    private var login: String = ""
    private var name: String = ""
    private lateinit var issueAdapter: IssueAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login = IssueFragmentArgs.fromBundle(requireArguments()).login
        name = IssueFragmentArgs.fromBundle(requireArguments()).name
        viewModel.getIssues(name, login)
        observeProductListState()


    }

    private fun observeProductListState() {
        collectLatestFlowOnLifecycle(viewModel.issues) { state ->
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
                        Log.i(TAG, "" + state.data)
                        issueAdapter = IssueAdapter(requireContext())
                        issueAdapter.submitList(state.data)
                        binding.recyclerIssues.adapter = issueAdapter

                    }
                }
            }
        }
    }

}
