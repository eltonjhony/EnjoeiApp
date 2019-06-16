package com.eltonjhony.enjoeiapp.internal.extensions

import android.content.res.Resources
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Int.isEven(): Boolean = this % 2 == 0

fun Double.toBrlCurrency(): String {
    val currencySymbol = DecimalFormatSymbols(Locale.getDefault())
    currencySymbol.decimalSeparator = ','
    currencySymbol.groupingSeparator = '.'

    val format = when {
        this % 1 == 0.0 -> DecimalFormat("0.#", currencySymbol)
        else -> DecimalFormat("0.00#", currencySymbol)
    }

    return String.format("%s %s", "R$", format.format(this))
}