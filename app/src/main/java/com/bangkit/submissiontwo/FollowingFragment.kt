package com.bangkit.submissiontwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bangkit.submissiontwo.adapter.FollowingAdapter
import com.bangkit.submissiontwo.databinding.FragmentFollowingBinding
import com.bangkit.submissiontwo.viewmodel.FollViewModel


class FollowingFragment : Fragment() {
    private val binding: FragmentFollowingBinding by viewBinding()
    private val detailViewModel by viewModels<FollViewModel>()
    private lateinit var username:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        username = arguments?.getString(DetailActivity.EXTRA_USER).toString()
        binding.apply {
            rvFollowing.setHasFixedSize(true)
            rvFollowing.layoutManager = LinearLayoutManager(activity)

        }
        showLoading(true)
        detailViewModel.getFollowingUsers(username)
        detailViewModel.listFoll.observe(
            viewLifecycleOwner
        ) {
            binding.rvFollowing.adapter = FollowingAdapter(it)
            showLoading(false)
        }


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentFollowingBinding.inflate(inflater,container,false).root
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}

