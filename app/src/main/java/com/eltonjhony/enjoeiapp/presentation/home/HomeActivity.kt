package com.eltonjhony.enjoeiapp.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.internal.extensions.attachFragment
import com.eltonjhony.enjoeiapp.presentation.home.browseproducts.BrowseProductsFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)
        attachFragment(R.id.container, BrowseProductsFragment())
    }
}
