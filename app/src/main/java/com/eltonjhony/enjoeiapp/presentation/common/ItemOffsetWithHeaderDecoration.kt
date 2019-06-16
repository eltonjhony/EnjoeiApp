package com.eltonjhony.enjoeiapp.presentation.common

import android.graphics.Rect
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.eltonjhony.enjoeiapp.internal.extensions.toPx

class ItemOffsetWithHeaderDecoration(private val headerType: Int,
                                     private val mItemOffset: Int = 5.toPx()) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        val itemPosition = parent.getChildAdapterPosition(view)

        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }

        val itemCount = state.itemCount

        if (spanIndex == 0) {
            parent.adapter?.let {
                val type = it.getItemViewType(itemPosition)
                if (type != headerType) {
                    if (itemCount > 0 && itemPosition == itemCount - 1) {
                        outRect.set(2 * mItemOffset, mItemOffset, mItemOffset * 2, mItemOffset)
                    } else {
                        outRect.set(2 * mItemOffset, mItemOffset, mItemOffset, mItemOffset)
                    }
                }
            }
        } else {
            outRect.set(mItemOffset, mItemOffset, mItemOffset * 2, mItemOffset)
        }
    }

}