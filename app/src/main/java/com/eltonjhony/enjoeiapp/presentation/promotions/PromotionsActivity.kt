package com.eltonjhony.enjoeiapp.presentation.promotions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.presentation.common.Resource
import com.eltonjhony.enjoeiapp.presentation.common.ResourceState
import kotlinx.android.synthetic.main.activity_promotions.*
import org.koin.android.viewmodel.ext.android.viewModel

class PromotionsActivity: AppCompatActivity() {

    private val promotionsViewModel: PromotionsViewModel by viewModel()

    companion object {

        fun callingIntent(context: Context?) {
            context?.startActivity(Intent(context, PromotionsActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotions)
        promoView.setup()
        promotionsViewModel.products.observe(this, Observer { updateState(it) })
        promotionsViewModel.load()
    }

    private fun updateState(resource: Resource<List<Product>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.SUCCESS -> {
                    it.data?.let { products -> promoView.update(products) }
                }
                ResourceState.ERROR -> {
                    it.throwable?.let { _ -> promoView.showGenericError() }
                } else -> Log.d("TAG", "Not mapped state")
            }
        }
    }
}