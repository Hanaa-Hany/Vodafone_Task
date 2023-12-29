package com.hanaahany.task.ui.details.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.hanaahany.task.R
import com.hanaahany.task.databinding.FragmentRepoDetailsBinding
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.ui.base.BaseFragment
import com.hanaahany.task.ui.details.viewmodel.RepoDetailsViewModel
import com.hanaahany.task.ui.main.view.MainFragmentDirections
import com.hanaahany.task.ui.main.view.ReposAdapter
import com.hanaahany.task.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RepoDetailsFragment : BaseFragment<FragmentRepoDetailsBinding, RepoDetailsViewModel>() {
    private val TAG = "TAG RepoDetailsFragment"
    override val layoutIdFragment = R.layout.fragment_repo_details
    override fun getViewModelClass() = RepoDetailsViewModel::class.java
    private var login:String=""
    private var name:String=""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login = RepoDetailsFragmentArgs.fromBundle(requireArguments()).login
        name = RepoDetailsFragmentArgs.fromBundle(requireArguments()).name


        Log.i(TAG,login+name)
        viewModel.getRepoDetails(name,login)
        observeRepoListState()



    }

    private fun observeRepoListState() {
        collectLatestFlowOnLifecycle(viewModel.repoDetails) { state ->
            when (state) {
                is ApiState.Failure -> {
                    Log.i(TAG, "observeProductListState: failure ${state.msg}")
                    Log.i(TAG,name)
                    viewModel.getRepoDetailsFromDB(login)
                    observeRepo()

                }

                ApiState.Loading -> {
                    Log.i(TAG, "observeProductListState: loading...")
                }

                is ApiState.Success -> {
                  Log.i(TAG,"${state.data}")
                    binding.tvForkRepoDetails.text=state.data.forks.toString()
                    binding.tvRepoName.text=state.data.name
                    binding.tvOwnerName.text=state.data.owner?.login
                    binding.tvStarRepoDetails.text=state.data.owner?.id.toString()
                    binding.tvDescription.text=state.data.description
                    Glide.with(this).load(state.data.owner?.avatarUrl).into(binding.imageRepoDetails)
                    viewModel.saveRepoDetailsFromDB(state.data)
                    viewModel.updateRepoDetailsFromDB(state.data)
                    binding.btnShowIssue.setOnClickListener {
                        val action= RepoDetailsFragmentDirections.actionRepoDetailsFragmentToIssueFragment(login,name)
                        Navigation.findNavController(requireView()).navigate(action)
                    }

                    }
                }
            }
        }
    private fun observeRepo(){
          lifecycleScope .launch {
            viewModel.repoDBDetails.collect{
                binding.tvForkRepoDetails.text=it.forks.toString()
                binding.tvRepoName.text=it.name
                binding.tvOwnerName.text=it.owner?.login
                binding.tvStarRepoDetails.text=it.owner?.id.toString()
                binding.tvDescription.text=it.description
                Glide.with(this@RepoDetailsFragment).load(it.owner?.avatarUrl).into(binding.imageRepoDetails)
            }
        }
    }



}