package com.alibbalci.isgmobil


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alibbalci.isgmobil.navigation.AppNavigation

import com.alibbalci.isgmobil.ui.theme.IsgMobilTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IsgMobilTheme {
                AppNavigation()
            }
        }
    }
}