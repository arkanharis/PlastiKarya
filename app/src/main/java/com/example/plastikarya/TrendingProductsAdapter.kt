package com.example.plastikarya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TrendingProductsAdapter(
    private val products: List<Product>,
    private val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<TrendingProductsAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardProduct: CardView = itemView.findViewById(R.id.card_product)
        private val ivProductImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        private val tvProductPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        private val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        private val tvSoldCount: TextView = itemView.findViewById(R.id.tv_sold_count)

        fun bind(product: Product) {
            tvProductName.text = product.name
            tvProductPrice.text = product.price
            tvRating.text = String.format("%.1f", product.rating)
            tvSoldCount.text = "${product.soldCount} terjual"

            // Set placeholder image or load from URL
            // For now, using a placeholder drawable
            ivProductImage.setImageResource(R.drawable.ic_recycle)

            cardProduct.setOnClickListener {
                onProductClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_trending, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}