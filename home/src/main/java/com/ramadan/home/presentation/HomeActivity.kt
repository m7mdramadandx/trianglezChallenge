package com.ramadan.home.presentation

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ramadan.home.R
import com.ramadan.home.databinding.ActivityHomeBinding
import java.util.*


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var viewModel: HomeViewModel
    private lateinit var repeatedWordsAdapter: RepeatedWordsAdapter
    private lateinit var searchView: SearchView
    private lateinit var repeatedWordsList: List<Pair<String, Int>>
    private var isDescending: Boolean = true // flag for sorting list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        loadData()

        repeatedWordsAdapter = RepeatedWordsAdapter()
        binding.rvRepeatedWords.adapter = repeatedWordsAdapter

        // handle on refresh listener, reset all view to defaults
        binding.swipeToRefresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                loadData()
                binding.swipeToRefresh.isRefreshing = false
                binding.toolbar.menu?.getItem(1)?.setIcon(R.drawable.ic_sort_descending)
            }, 1000L)

        }
    }

    private fun loadData() {
        viewModel.checkInternetConnection(this).observe(this) {
            if (it == true) {
                fetchNetworkData()
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.no_internet_connection),
                    Snackbar.LENGTH_LONG
                )
                    .setBackgroundTint(Color.RED)
                    .show()
                getLocalData()
            }
        }
    }

    private fun fetchNetworkData() {
        viewModel.fetchData().observe(this) { list ->

            if (list.isNullOrEmpty()) {
                true.toggleEmptyResult()
                binding.tvErrorMsg.apply {
                    text = getString(R.string.something_went_wrong)
                    visibility = View.VISIBLE
                }

            } else {
                false.toggleEmptyResult()
                repeatedWordsList = list
                repeatedWordsAdapter.setItems(list)
                setLocalData(list)
            }
        }
    }

    private fun setLocalData(list: List<Pair<String, Int>>) {
        viewModel.insert(this, list)
    }

    private fun getLocalData() {
        viewModel.fetchOfflineData(this).observe(this) {

            if (it.isNullOrEmpty()) {
                true.toggleEmptyResult()
                binding.tvErrorMsg.apply {
                    text = getString(R.string.something_went_wrong)
                    visibility = View.VISIBLE
                }

            } else {
                false.toggleEmptyResult()
                repeatedWordsList = it
                repeatedWordsAdapter.setItems(it)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

        searchView.apply {

            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
            isIconified = false

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // close keyboard on search action clicked from the keyboard
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    hideKeyboard()
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    // reset items on typing no characters
                    if (p0?.isEmpty() == true) {
                        false.toggleEmptyResult()
                        repeatedWordsAdapter.setItems(repeatedWordsList)

                    } else {
                        // filter the list by typing characters
                        val searchResultList =
                            repeatedWordsList.filter { it.first == p0?.lowercase(Locale.getDefault()) }

                        // show empty view when results are not founds
                        if (searchResultList.isEmpty()) true.toggleEmptyResult()

                        // update the list when results are founds
                        else false.toggleEmptyResult()
                        repeatedWordsAdapter.setItems(searchResultList)
                    }
                    return true
                }

            })
        }

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            // open keyboard and start typing on open search view
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                showKeyboard()
                searchView.requestFocus()
                return true
            }

            // close keyboard and reset items on close search view
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                hideKeyboard()
                false.toggleEmptyResult()
                repeatedWordsAdapter.setItems(repeatedWordsList)
                return true
            }

        })

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // handle the action of sort appbar icon
            R.id.action_sort -> {

                // close the search view and close the keyboard
                if (::searchView.isInitialized && !searchView.isIconified) {
                    searchView.isIconified = true
                    hideKeyboard()
                }

                val sortedList = if (isDescending) repeatedWordsList.sortedBy { it.second }
                else repeatedWordsList.sortedByDescending { it.second }

                repeatedWordsAdapter.setItems(sortedList)
                binding.rvRepeatedWords.scrollToPosition(0)
                isDescending = !isDescending
                if (isDescending) item.setIcon(R.drawable.ic_sort_descending)
                else item.setIcon(R.drawable.ic_sort_ascending)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // helper fun that hide keyboard
    private fun hideKeyboard() {
        currentFocus?.let { view ->
            val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // helper fun that show keyboard
    private fun showKeyboard() {
        currentFocus?.let { view ->
            val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.showSoftInput(view, 0)
        }
    }

    // toggle fun that show/hide empty view
    private fun Boolean.toggleEmptyResult() {
        if (this) {
            binding.pbRepeatedWords.visibility = View.GONE
            binding.rvRepeatedWords.visibility = View.GONE
            binding.tvErrorMsg.apply {
                text = getString(R.string.sorry_no_result_found)
                visibility = View.VISIBLE
            }
        } else {
            binding.pbRepeatedWords.visibility = View.GONE
            binding.rvRepeatedWords.visibility = View.VISIBLE
            binding.tvErrorMsg.apply {
                text = ""
                visibility = View.GONE
            }
        }
    }

}