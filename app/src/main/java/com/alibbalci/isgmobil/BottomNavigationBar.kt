package com.alibbalci.isgmobil.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import com.alibbalci.isgmobil.ui.theme.Navy
import com.alibbalci.isgmobil.ui.theme.Orange

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onHomeClick: () -> Unit,
    onCompaniesClick: () -> Unit,
    onObservationsClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    NavigationBar(
        containerColor = Color.White
    ) {

        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = onHomeClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Ana Sayfa"
                )
            },
            label = {
                Text("Ana Sayfa")
            },
            colors = bottomNavColors()
        )

        NavigationBarItem(
            selected = currentRoute == "company_list",
            onClick = onCompaniesClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = "Şirketlerim"
                )
            },
            label = {
                Text("Şirketlerim")
            },
            colors = bottomNavColors()
        )

        NavigationBarItem(
            selected = currentRoute == "observation_list",
            onClick = onObservationsClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Gözlemlerim"
                )
            },
            label = {
                Text("Gözlemlerim")
            },
            colors = bottomNavColors()
        )

        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = onProfileClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profil"
                )
            },
            label = {
                Text("Profil")
            },
            colors = bottomNavColors()
        )
    }
}

@Composable
private fun bottomNavColors() =
    NavigationBarItemDefaults.colors(
        selectedIconColor = Orange,
        selectedTextColor = Orange,

        unselectedIconColor = Navy.copy(alpha = 0.55f),
        unselectedTextColor = Navy.copy(alpha = 0.55f),

        indicatorColor = Orange.copy(alpha = 0.10f)
    )