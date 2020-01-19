package com.mylektop.ecommerce56.ui.purchaseHistory

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.databinding.ActivityPurchaseHistoryBinding
import com.mylektop.ecommerce56.injection.ViewModelFactory

class PurchaseHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPurchaseHistoryBinding
    private lateinit var viewModel: PurchaseHistoryViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase_history)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PurchaseHistoryViewModel::class.java)

        setSupportActionBar(binding.toolbar)

        viewModel.toolbar = supportActionBar!!
        viewModel.toolbar.setDisplayHomeAsUpEnabled(true)
        viewModel.toolbar.setDisplayShowHomeEnabled(true)
        viewModel.toolbar.setTitle(R.string.title_purchase_history)
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back_white_24)

        binding.productList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewModel = viewModel
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()

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