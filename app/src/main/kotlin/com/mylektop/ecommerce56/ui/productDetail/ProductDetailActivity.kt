package com.mylektop.ecommerce56.ui.productDetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.databinding.ActivityProductDetailBinding
import com.mylektop.ecommerce56.injection.ViewModelFactory
import com.mylektop.ecommerce56.model.ProductPromo

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ProductDetailViewModel::class.java)

        setSupportActionBar(binding.toolbar)

        viewModel.loadingVisibility.value = View.GONE

        viewModel.toolbar = supportActionBar!!
        viewModel.toolbar.setDisplayHomeAsUpEnabled(true)
        viewModel.toolbar.setDisplayShowHomeEnabled(true)
        viewModel.toolbar.setTitle(R.string.title_product_detail)
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back_white_24)

        val productPromo = ProductPromo(
                intent.getIntExtra("productId", 0),
                intent.getStringExtra("productImageUrl")!!,
                intent.getStringExtra("productName")!!,
                intent.getStringExtra("productDescription")!!,
                intent.getStringExtra("productPrice")!!,
                intent.getIntExtra("productLoved", 0)
        )

        viewModel.bind(productPromo)

        viewModel.successMessage.observe(this, Observer { successMessage ->
            if (successMessage != null) showSuccess(successMessage)
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_share) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, binding.tvProductName.text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

            return true
        } else if (item.itemId == android.R.id.home) {
            onBackPressed()

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showSuccess(@StringRes successMessage: Int) {
        Toast.makeText(this@ProductDetailActivity, successMessage, Toast.LENGTH_LONG).show()
        finish()
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