package com.alibbalci.isgmobil.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Orange,
    onPrimary = CardBackground,

    secondary = Navy,
    onSecondary = CardBackground,

    background = AppBackground,
    onBackground = TextPrimary,

    surface = CardBackground,
    onSurface = TextPrimary,

    surfaceVariant = InputBackground,
    onSurfaceVariant = TextSecondary,

    outline = BorderLight,

    error = RiskRed
)

private val DarkColorScheme = darkColorScheme(
    primary = Orange,
    secondary = NavyLight
)

@Composable
fun IsgMobilTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    /*
     * Tasarım modelimiz şu an açık tema üzerine kurulu.
     * O yüzden şimdilik LightColorScheme kullanıyoruz.
     *
     * Daha sonra gerçek dark theme ekleyebiliriz.
     */
    val colorScheme = LightColorScheme

    val view = LocalView.current

    if (!view.isInEditMode) {

        val window =
            (view.context as Activity).window

        window.statusBarColor =
            Navy.toArgb()

        WindowCompat
            .getInsetsController(
                window,
                view
            )
            .isAppearanceLightStatusBars = false
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}