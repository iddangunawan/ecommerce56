package com.mylektop.ecommerce56.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.google.android.material.snackbar.Snackbar
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.databinding.ActivityHomeBinding
import com.mylektop.ecommerce56.injection.ViewModelFactory
import com.mylektop.ecommerce56.model.ProductPromo
import com.mylektop.ecommerce56.ui.purchaseHistory.PurchaseHistoryActivity
import com.mylektop.ecommerce56.ui.search.SearchActivity

class HomeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeListViewModel
    private var errorSnackbar: Snackbar? = null
    private val snapHelperStart: SnapHelper = GravitySnapHelper(Gravity.START)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(HomeListViewModel::class.java)

        setSupportActionBar(binding.toolbar)

        viewModel.toolbar = supportActionBar!!
        viewModel.toolbar.setTitle(R.string.title_home)

        binding.categoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.productList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.bottomNavigation = binding.navView
        viewModel.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    viewModel.toolbar.setTitle(R.string.title_home)
                    return@setOnNavigationItemSelectedListener true
                }
                /*R.id.navigation_feed -> {
                    viewModel.toolbar.setTitle(R.string.title_feed)
                    return@setOnNavigationItemSelectedListener true
                }*/
                /*R.id.navigation_cart -> {
                    viewModel.toolbar.setTitle(R.string.title_cart)
                    return@setOnNavigationItemSelectedListener true
                }*/
                R.id.navigation_profile -> {
                    viewModel.toolbar.setTitle(R.string.title_profile)
                    val intent: Intent = Intent(this, PurchaseHistoryActivity::class.java)

                    startActivity(intent)

                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewModel = viewModel

        snapHelperStart.attachToRecyclerView(binding.categoryList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_search) {
            val list: ArrayList<ProductPromo> = ArrayList()

            list.addAll(viewModel.listProduct)

            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("listProduct", list)

            startActivity(intent)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}