package com.faith.hymnal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.faith.hymnal.ui.HymnViewModel
import com.faith.hymnal.ui.detail.HymnDetailScreen
import com.faith.hymnal.ui.home.HomeScreen
import com.faith.hymnal.ui.theme.HymnalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HymnalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: HymnViewModel = viewModel(factory = HymnViewModel.Factory)

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable(
                            route = "hymn/{hymnId}",
                            arguments = listOf(navArgument("hymnId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val hymnId = backStackEntry.arguments?.getInt("hymnId") ?: 1
                            HymnDetailScreen(
                                navController = navController,
                                viewModel = viewModel,
                                hymnId = hymnId
                            )
                        }
                    }
                }
            }
        }
    }
}
