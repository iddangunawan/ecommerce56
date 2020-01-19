package com.mylektop.ecommerce56.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.databinding.ActivityLoginBinding
import com.mylektop.ecommerce56.injection.ViewModelFactory
import com.mylektop.ecommerce56.ui.home.HomeListActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(LoginViewModel::class.java)

        binding.btnSignin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, HomeListActivity::class.java))
            finish()
        }

        binding.btnSigninFb.setOnClickListener {
            startActivity(Intent(this@LoginActivity, HomeListActivity::class.java))
            finish()
        }

        binding.btnSigninGoogle.setOnClickListener {
            startActivity(Intent(this@LoginActivity, HomeListActivity::class.java))
            finish()
        }
    }
}