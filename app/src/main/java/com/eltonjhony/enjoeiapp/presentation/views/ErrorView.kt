package com.eltonjhony.enjoeiapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.internal.extensions.hide
import com.eltonjhony.enjoeiapp.internal.extensions.show
import kotlinx.android.synthetic.main.error_view.view.*

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    fun showError(title: String, description: String, tryAgainListener: (() -> Unit)? = null) {
        errorTitleTextView.text = title
        errorDescriptionTextView.text = description
        loadingView.hide()
        tryAgainListener?.let { listener ->
            tryAgainButton.setOnClickListener {
                loadingView.show()
                listener.invoke()
            }
            tryAgainButton.show()
        }
        show()
    }

    init {
        View.inflate(context, R.layout.error_view, this)
    }
}