package com.mylektop.ecommerce56.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.databinding.ItemProductBinding
import com.mylektop.ecommerce56.model.ProductPromo
import com.mylektop.ecommerce56.ui.productDetail.ProductDetailActivity

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var list: MutableList<ProductPromo> = mutableListOf()
    private var listCopy: MutableList<ProductPromo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_product, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<ProductPromo>) {
        this.list = list
        listCopy = arrayListOf()
        listCopy.addAll(list)

        notifyDataSetChanged()
    }

    fun getFilter(query: String) {
        list.clear()

        if (query.isEmpty()) {
            list.addAll(listCopy)
        } else {
            for (item: ProductPromo in listCopy) {
                if (item.title.toLowerCase().contains(query.toLowerCase())) {
                    list.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ProductViewModel()

        fun bind(productPromo: ProductPromo) {
            viewModel.bind(productPromo)
            binding.viewModel = viewModel
            binding.executePendingBindings()
            binding.itemClickListener = object : OnItemClickListener {
                override fun onItemClick(view: View) {
                    val intent: Intent = Intent(view.context, ProductDetailActivity::class.java).apply {
                        putExtra("productId", System.currentTimeMillis().toInt())
                        putExtra("productImageUrl", productPromo.imageUrl)
                        putExtra("productName", productPromo.title)
                        putExtra("productDescription", productPromo.description)
                        putExtra("productPrice", productPromo.price)
                        putExtra("productLoved", productPromo.loved)
                    }

                    view.context.startActivity(intent)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View)
    }
}