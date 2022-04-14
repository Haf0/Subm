package com.bangkit.submissiontwo

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.submissiontwo.adapter.SectionsPagerAdapter
import com.bangkit.submissiontwo.database.UserDatabase
import com.bangkit.submissiontwo.databinding.ActivityDetailBinding
import com.bangkit.submissiontwo.viewmodel.DatabaseViewModelFactory
import com.bangkit.submissiontwo.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    //private val detailViewModel: DetailViewModel by viewModels()
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getStringExtra(EXTRA_USER)
        val mBundle = Bundle()
        mBundle.putString(EXTRA_USER,user)
        val factory = DatabaseViewModelFactory.getInstance(this.application)
        val detailViewModel = ViewModelProvider(this,factory).get(DetailViewModel::class.java)

        detailViewModel.getDetailUser(user!!)

        detailViewModel.getAlluser().observe(this){
            isFavorite = it.any { it.username == user }
        }

        supportActionBar?.title= user
        detailViewModel.detailUser.observe(this){
            detailUser ->
            binding.apply {
                with(detailUser){
                    progressBar.visibility = View.GONE
                    tvUsername.text = username
                    tvName.text = name
                    tvLocation.text = isNull(location)
                    tvKantor.text = isNull(company)
                    tvBlog.text = isNull(blog)
                    tvFollowers.text = followers
                    tvFollowing.text = following
                    favButton.isChecked = isFavorite
                    val usersD = UserDatabase(username,avatar)
                    favButton.setOnClickListener {
                        if (isFavorite){
                            detailViewModel.delete(usersD)
                        }else{
                            detailViewModel.insert(usersD)
                        }
                    }
                    Glide.with(this@DetailActivity)
                        .load(avatar)
                        .circleCrop()
                        .into(ivAvatar)
                }
            }
        }

        viewPager(mBundle)
    }

    fun viewPager(mBundle: Bundle){
        val sectionsPagerAdapter = SectionsPagerAdapter(this,mBundle)
        binding.viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    fun isNull(it: String?): String{
        return if (it.isNullOrBlank()) "(kosong)" else it
    }

    companion object {
        const val EXTRA_USER = "extra_USER"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.following,
            R.string.followers
        )
    }
}