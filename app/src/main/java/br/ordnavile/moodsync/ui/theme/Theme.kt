package br.ordnavile.moodsync.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import br.ordnavile.moodsync.data.Mood

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun MoodSyncTheme(
    mood: Mood? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        mood != null -> {
            // If a mood is selected, we create a theme based on its color
            if (darkTheme) {
                darkColorScheme(
                    primary = mood.baseColor,
                    onPrimary = Color.White,
                    secondary = mood.baseColor.copy(alpha = 0.8f),
                    tertiary = mood.baseColor.copy(alpha = 0.6f)
                )
            } else {
                lightColorScheme(
                    primary = mood.baseColor,
                    onPrimary = Color.White,
                    secondary = mood.baseColor.copy(alpha = 0.8f),
                    tertiary = mood.baseColor.copy(alpha = 0.6f)
                )
            }
        }
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        // We handle status bar color manually or via enableEdgeToEdge in MainActivity
        // For now, let's just ensure the content looks good.
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
