package com.androidforge.streakhappits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.androidforge.streakhappits.core.designsystem.theme.StreakHabitsTheme
import com.androidforge.streakhappits.navigation.AppNavHost
import com.androidforge.streakhappits.presentation.onboarding.OnboardingPrefManager
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var onboardingPrefManager: OnboardingPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make the app span the full screen, behind the system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            StreakHabitsTheme {
                val systemUiController = rememberSystemUiController()
                // For a dark theme, `darkIcons` should generally be false
                val useDarkIcons = MaterialTheme.colorScheme.background.luminance() > 0.5f

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent, // Transparent status bar
                        darkIcons = useDarkIcons
                    )
                    systemUiController.setNavigationBarColor(
                        color = Color.Transparent, // Transparent navigation bar
                        darkIcons = useDarkIcons
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavHost(
                        navController = navController,
                        onboardingPrefManager = onboardingPrefManager
                    )
                }
            }
        }
    }
}