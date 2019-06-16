package com.eltonjhony.enjoeiapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.internal.extensions.load
import kotlinx.android.synthetic.main.likes_view.view.*

class LikesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.likes_view, this)
    }

    var userLikes: Int = 0
        set(value) {
            field = value
            userLikesTextView.text = "$value"
        }

    var userPhoto: String = ""
        set(value) {
            field = value
            userPhotoImageView.load(value)
        }
}