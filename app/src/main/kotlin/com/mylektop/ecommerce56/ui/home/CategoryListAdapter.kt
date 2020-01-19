package com.mylektop.ecommerce56.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.databinding.ItemCategoryBinding
import com.mylektop.ecommerce56.model.Category

class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    private lateinit var list: MutableList<Category>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_category, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return if (::list.isInitialized) list.size else 0
    }

    fun updateList(list: MutableList<Category>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CategoryViewModel()

        fun bind(category: Category) {
            viewModel.bind(category)
            binding.viewModel = viewModel
        }
    }
}