package com.androidforge.streakhappits.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidforge.streakhappits.presentation.add_edit_habit.AddEditHabitScreen
import com.androidforge.streakhappits.presentation.common.navigation.Screen
import com.androidforge.streakhappits.presentation.habit_details.HabitDetailsScreen
import com.androidforge.streakhappits.presentation.home.HomeScreen
import com.androidforge.streakhappits.presentation.onboarding.OnboardingPrefManager
import com.androidforge.streakhappits.presentation.onboarding.OnboardingScreen
import com.androidforge.streakhappits.presentation.settings.SettingsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onboardingPrefManager: OnboardingPrefManager
) {
    val isOnboardingCompleted by onboardingPrefManager.isOnboardingCompleted.collectAsState(initial = false)

    val startDestination = if (isOnboardingCompleted) Screen.Home.route else Screen.Onboarding.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(
                onOnboardingComplete = {
                    navController.popBackStack() // Remove onboarding from back stack
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditHabit.route,
            arguments = listOf(
                navArgument("habitId") {
                    type = NavType.LongType
                    defaultValue = -1L
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val habitId = backStackEntry.arguments?.getLong("habitId") ?: -1L
            AddEditHabitScreen(navController = navController, habitId = habitId)
        }
        composable(
            route = Screen.HabitDetails.route,
            arguments = listOf(
                navArgument("habitId") {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val habitId = backStackEntry.arguments?.getLong("habitId") ?: -1L
            HabitDetailsScreen(navController = navController, habitId = habitId)
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}