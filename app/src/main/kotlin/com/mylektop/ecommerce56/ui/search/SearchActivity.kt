package com.mylektop.ecommerce56.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.databinding.ActivitySearchBinding
import com.mylektop.ecommerce56.injection.ViewModelFactory
import com.mylektop.ecommerce56.model.ProductPromo

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(SearchViewModel::class.java)

        setSupportActionBar(binding.toolbar)

        viewModel.toolbar = supportActionBar!!
        viewModel.toolbar.setDisplayHomeAsUpEnabled(true)
        viewModel.toolbar.setDisplayShowHomeEnabled(true)
        viewModel.toolbar.setTitle(R.string.title_search)
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back_white_24)

        viewModel.productListAdapter.updateList(intent.getSerializableExtra("listProduct") as ArrayList<ProductPromo>)
        binding.productList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_view, menu)

        val searchView = menu?.findItem(R.id.item_search_view)?.actionView as SearchView
        searchView.queryHint = "Search Product"
        searchView.isIconified = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                Toast.makeText(this@SearchActivity, "Searching $query ..", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.productListAdapter.getFilter(newText!!)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}