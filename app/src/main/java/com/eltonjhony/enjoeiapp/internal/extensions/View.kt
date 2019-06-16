package com.eltonjhony.enjoeiapp.internal.extensions

import android.graphics.Outline
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eltonjhony.enjoeiapp.R

fun ImageView.load(url: String?) {
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.placeholderOf(R.drawable.placeholder))
        .into(this)
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.roundTopBorders(curveRadius: Int = 4) {
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(0, 0, width, (height + curveRadius.toPx()), curveRadius.toFloat())
        }
    }
    clipToOutline = true
}

fun AppCompatActivity.attachFragment(@IdRes container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().add(container, fragment).commit()
}

fun AppCompatActivity.displayHomeAsUpEnabled(view: Toolbar) {
    setSupportActionBar(view)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}
