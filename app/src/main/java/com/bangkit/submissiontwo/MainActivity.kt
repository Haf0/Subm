package com.bangkit.submissiontwo

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.submissiontwo.adapter.ListUserAdapter
import com.bangkit.submissiontwo.databinding.ActivityMainBinding
import com.bangkit.submissiontwo.model.User
import com.bangkit.submissiontwo.viewmodel.MainViewModel
import com.bangkit.submissiontwo.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = SettingPreferences.getInstance(dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            MainViewModel::class.java
        )

        supportActionBar?.title = getString(R.string.app_name)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(false)
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        viewModel.list.observe(
            this
        ) {
            if (it != null) {
                showLoading(false)
                showRecyclerView(it)
            }

        }


    }
    private fun showRecyclerView(list: List<User>) {
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUser.adapter = listUserAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_field, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView


        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                showLoading(true)
                viewModel.getUserFromSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var boolReturn = false


        when (item.itemId) {
            R.id.fav -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                boolReturn = true
            }
            R.id.light_mode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                viewModel.saveThemeSetting(false)
                boolReturn = true
            }
            R.id.night_mode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                viewModel.saveThemeSetting(true)
                boolReturn = true
            }
        }
        return boolReturn
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }



}