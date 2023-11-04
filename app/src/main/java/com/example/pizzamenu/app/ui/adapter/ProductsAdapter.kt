package com.example.pizzamenu.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.pizzamenu.R
import com.example.pizzamenu.databinding.ItemProductBinding
import com.example.pizzamenu.domain.model.Product

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private val differ = AsyncListDiffer(this, ProductDiffUtilCallback())

    var products: List<Product>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding(ItemProductBinding::bind)

        fun bind(product: Product) {
            binding.ivProduct.load(product.image) { crossfade(enable = true) }
            binding.tvTitle.text = product.title
            binding.tvSubtitle.text = product.restaurantChain
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product, parent, false)
        return ViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size

    private class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    }
}