package com.bangkit.submissiontwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bangkit.submissiontwo.adapter.FollowersAdapter
import com.bangkit.submissiontwo.databinding.FragmentFollowerBinding
import com.bangkit.submissiontwo.viewmodel.FollViewModel


class FollowerFragment : Fragment() {

    private val binding: FragmentFollowerBinding by viewBinding()
    private val detailViewModel by viewModels<FollViewModel>()
    private lateinit var username:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentFollowerBinding.inflate(inflater,container,false).root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString(DetailActivity.EXTRA_USER).toString()
        binding.apply {
            rvFollower.setHasFixedSize(true)
            rvFollower.layoutManager = LinearLayoutManager(activity)

        }
        showLoading(true)
        with(detailViewModel) {
            getFollowersUsers(username)
            listFollowers.observe(
                viewLifecycleOwner
            ) {list->
                binding.rvFollower.adapter = FollowersAdapter(list)
                showLoading(false)
            }
        }


    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}