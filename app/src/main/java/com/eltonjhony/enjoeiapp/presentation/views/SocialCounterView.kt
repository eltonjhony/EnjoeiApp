package com.eltonjhony.enjoeiapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.internal.extensions.hide
import com.eltonjhony.enjoeiapp.internal.extensions.show
import kotlinx.android.synthetic.main.social_counter_view.view.*

class SocialCounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    var commentsCount: Int = 0
        set(value) {
            field = value
            if (value <= 0) commentsCountTextView.hide() else commentsCountTextView.show()
            commentsCountTextView.text = "$value"
        }

    init {
        View.inflate(context, R.layout.social_counter_view, this)
    }
}