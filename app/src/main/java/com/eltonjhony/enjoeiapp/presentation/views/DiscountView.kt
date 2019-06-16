package com.eltonjhony.enjoeiapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.internal.extensions.hide
import com.eltonjhony.enjoeiapp.internal.extensions.show
import kotlinx.android.synthetic.main.discount_label_view.view.*

class DiscountView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.discount_label_view, this)
    }

    var discount: String = ""
        set(value) {
            field = value
            if (value.isNotBlank()) show() else hide()
            discountPercentageTextView.text = value
        }
}