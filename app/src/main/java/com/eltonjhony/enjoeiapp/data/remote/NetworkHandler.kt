package com.eltonjhony.enjoeiapp.data.remote

import android.content.Context
import com.eltonjhony.enjoeiapp.internal.extensions.networkInfo

class NetworkHandler(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected ?: false
}