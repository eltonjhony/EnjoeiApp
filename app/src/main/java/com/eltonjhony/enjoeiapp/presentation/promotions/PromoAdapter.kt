package com.eltonjhony.enjoeiapp.presentation.promotions

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.internal.extensions.toBrlCurrency
import kotlinx.android.synthetic.main.promotion_item.view.*

class PromoAdapter(
    private var products: MutableList<Product> = mutableListOf(),
    val clickListener: (Product) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PromoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.promotion_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is PromoViewHolder -> {
                val product = products[position]
                holder.bind(product)
                holder.itemView.setOnClickListener { clickListener(product) }
            }
        }
    }

    fun updateProducts(results: List<Product>) {
        products.addAll(results)
        notifyDataSetChanged()
    }

    inner class PromoViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(product: Product) {
            itemView.descTextView.text = product.title
            itemView.priceTextView.text = product.price.toBrlCurrency()
            itemView.originalPriceTextView.apply {
                text = product.getFormattedOriginalPrice()
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            itemView.socialCounterView.commentsCount = product.publishedCommentsCount
        }
    }

}
