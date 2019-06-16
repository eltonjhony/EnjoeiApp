package com.eltonjhony.enjoeiapp.presentation.home.browseproducts

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.internal.extensions.load
import com.eltonjhony.enjoeiapp.internal.extensions.roundTopBorders
import com.eltonjhony.enjoeiapp.internal.extensions.toBrlCurrency
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter(
    private var products: MutableList<Product> = mutableListOf(),
    val clickListener: (Product) -> Unit,
    val promotionClickListener: () -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            BANNER_VIEW_TYPE -> object : ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.banner_view,
                    parent,
                    false
                )
            ) {}
            else -> ProductsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.product_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = products.size + 1
    override fun getItemViewType(position: Int): Int = when {
        isHeaderPosition(position) -> BANNER_VIEW_TYPE
        else -> PRODUCTS_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ProductsViewHolder -> {
                val product = products[position - 1]
                holder.bind(product)
                holder.itemView.setOnClickListener { clickListener(product) }
            } else -> holder.itemView.setOnClickListener { promotionClickListener() }
        }
    }

    fun updateProducts(results: List<Product>) {
        val positionStart = products.size + 1
        products.addAll(results)
        notifyItemRangeInserted(positionStart, results.size)
    }

    private fun isHeaderPosition(position: Int): Boolean = position == 0

    inner class ProductsViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(product: Product) {
            itemView.productImageView.roundTopBorders()
            itemView.productImageView.load(product.defaultPhoto)
            itemView.titleTextView.text = product.title
            itemView.priceTextView.text = product.price.toBrlCurrency()
            itemView.reactionView.userLikes = product.likesCount
            itemView.reactionView.userPhoto = product.user.photo
            itemView.discountView.discount = product.getFormattedDiscountPercentage()
            itemView.sizeTextView.text = product.getFormattedSize()
        }
    }

    companion object {
        const val BANNER_VIEW_TYPE = 0
        const val PRODUCTS_VIEW_TYPE = 1
        const val PRODUCTS_COLUMNS_PER_LINE = 2
        const val PRODUCTS_COLUMNS_SPAN_SIZE = 1
    }
}
