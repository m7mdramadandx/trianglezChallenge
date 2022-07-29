package com.ramadan.home

import android.app.SearchManager
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.ramadan.home.databinding.ActivityHomeBinding
import com.ramadan.netwrok.RequestTask
import java.util.*


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    private lateinit var wordsAdapter: WordsAdapter
    private lateinit var searchView: SearchView
    private lateinit var task: AsyncTask<String?, Int, List<String>>
    private lateinit var repeatedWordsList: List<Pair<String, Int>>
    private var isDescending: Boolean = true // flag for sorting list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        wordsAdapter = WordsAdapter()
        binding.rvRepeatedWords.adapter = wordsAdapter
        val execute = RequestTask()
        task = execute.execute("http://instabug.com/")

        /**
         * convert all chars to lower case
         * remove special characters
         * split the full words with a space to catch each word in a list form
         * grouping words with them selves to catch el repeated ones
         * put the count of each repeated word
         * convert the map to list of pairs
         * remove empty items from list
         * sort list by descending order
         */
        repeatedWordsList = task.get().toString()
            .lowercase(Locale.getDefault())
            .replace(Regex("[$&+,:;=?@#|/¿§«»ω⊙¤°℃℉€¥£¢¡®©0‘'\\[\\]<>→“\".^*()%!-]"), "")
            .split(" ")
            .groupingBy { it }
            .eachCount()
            .toList()
            .filterNot { it.first == "" }
            .sortedByDescending { it.second }

        false.toggleEmptyResult()
        wordsAdapter.setItems(repeatedWordsList)

        // handle on refresh listener, reset all view to defaults
        binding.swipeToRefresh.setOnRefreshListener {
            wordsAdapter.setItems(repeatedWordsList)
            binding.swipeToRefresh.isRefreshing = false
            binding.toolbar.menu?.getItem(1)?.setIcon(R.drawable.ic_sort_descending)
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
                        wordsAdapter.setItems(repeatedWordsList)

                    } else {
                        // filter the list by typing characters
                        val searchResultList =
                            repeatedWordsList.filter { it.first == p0?.lowercase(Locale.getDefault()) }

                        // show empty view when results are not founds
                        if (searchResultList.isEmpty()) true.toggleEmptyResult()

                        // update the list when results are founds
                        else false.toggleEmptyResult()
                        wordsAdapter.setItems(searchResultList)
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
                wordsAdapter.setItems(repeatedWordsList)
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

                wordsAdapter.setItems(sortedList)
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
            binding.rvRepeatedWords.visibility = View.GONE
            binding.tvErrorMsg.apply {
                text = "No Result"
                visibility = View.VISIBLE
            }
        } else {
            binding.rvRepeatedWords.visibility = View.VISIBLE
            binding.tvErrorMsg.apply {
                text = ""
                visibility = View.GONE
            }
        }
    }

}