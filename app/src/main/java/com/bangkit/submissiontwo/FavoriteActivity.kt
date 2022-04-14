package com.bangkit.submissiontwo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.submissiontwo.adapter.FavoriteAdapter
import com.bangkit.submissiontwo.databinding.ActivityFavoriteBinding
import com.bangkit.submissiontwo.viewmodel.DatabaseViewModelFactory
import com.bangkit.submissiontwo.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var adapter: FavoriteAdapter

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.favorite)

        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        adapter = FavoriteAdapter()

        binding?.rvUser?.layoutManager = LinearLayoutManager(this)
        binding?.rvUser?.setHasFixedSize(true)
        binding?.rvUser?.adapter = adapter



        val viewModel = obtainViewModel(this)
        viewModel.getAllUsers().observe(this) {
            if (it != null){
                adapter.setListUser(it)
            }
            binding?.progressBar?.visibility = View.GONE
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.delete_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId){
            R.id.delete_all ->{
                val viewModel = obtainViewModel(this)
                viewModel.deleteAllUsers()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel{
        val factory = DatabaseViewModelFactory.getInstance(activity.application)

        return ViewModelProvider(activity,factory).get(FavoriteViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}