
package com.alibbalci.isgmobil.navigation

import CompanyListScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alibbalci.isgmobil.presentation.auth.login.LoginScreen
import com.alibbalci.isgmobil.presentation.auth.login.LoginViewModel
import com.alibbalci.isgmobil.presentation.auth.register.RegisterScreen
import com.alibbalci.isgmobil.presentation.auth.register.RegisterViewModel
import com.alibbalci.isgmobil.presentation.company.create.CompanyCreateScreen
import com.alibbalci.isgmobil.presentation.company.create.CompanyCreateViewModel
import com.alibbalci.isgmobil.presentation.company.detail.CompanyDetailScreen
import com.alibbalci.isgmobil.presentation.company.detail.CompanyDetailViewModel

import com.alibbalci.isgmobil.presentation.company.list.CompanyListViewModel
import com.alibbalci.isgmobil.presentation.home.HomeScreen
import com.alibbalci.isgmobil.presentation.home.HomeViewModel
import com.alibbalci.isgmobil.presentation.session.SessionViewModel
import com.alibbalci.isgmobil.presentation.splash.SplashScreen
import com.alibbalci.isgmobil.session.SessionState

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        splashScreen(navController)
        loginScreen(navController)
        registerScreen(navController)
        homeScreen(navController)

        companyListScreen(navController)
        companyCreateScreen(navController)
        companyDetailScreen(navController)
    }
}

private fun NavGraphBuilder.splashScreen(
    navController: NavHostController
) {
    composable(route = Routes.SPLASH) {

        val sessionViewModel: SessionViewModel = hiltViewModel()

        val sessionState by sessionViewModel.sessionState
            .collectAsStateWithLifecycle()

        SplashScreen()

        LaunchedEffect(sessionState) {
            when (sessionState) {

                SessionState.Loading -> Unit

                SessionState.LoggedIn -> {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }

                SessionState.LoggedOut -> {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.loginScreen(
    navController: NavHostController
) {
    composable(route = Routes.LOGIN) {

        val loginViewModel: LoginViewModel = hiltViewModel()

        LoginScreen(
            viewModel = loginViewModel,

            onNavigateToRegister = {
                navController.navigate(Routes.REGISTER) {
                    launchSingleTop = true
                }
            },

            onNavigateToHome = {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.LOGIN) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }
        )
    }
}

private fun NavGraphBuilder.registerScreen(
    navController: NavHostController
) {
    composable(route = Routes.REGISTER) {

        val registerViewModel: RegisterViewModel = hiltViewModel()

        RegisterScreen(
            viewModel = registerViewModel,

            onNavigateToLogin = {
                navController.popBackStack()
            }
        )
    }
}

private fun NavGraphBuilder.homeScreen(
    navController: NavHostController
) {
    composable(route = Routes.HOME) {

        val homeViewModel: HomeViewModel = hiltViewModel()

        HomeScreen(
            onNavigateToCompanies = {
                navController.navigate(Routes.COMPANY_LIST) {
                    launchSingleTop = true
                }
            },

            onLogout = {
                homeViewModel.logout(
                    onLogoutSuccess = {
                        navController.navigate(Routes.LOGIN) {

                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            }
        )
    }
}

private fun NavGraphBuilder.companyListScreen(
    navController: NavHostController
) {
    composable(route = Routes.COMPANY_LIST) {

        val companyListViewModel: CompanyListViewModel = hiltViewModel()

        CompanyListScreen(
            viewModel = companyListViewModel,

            onCompanyClick = { companyId ->
                navController.navigate(
                    "${Routes.COMPANY_DETAIL}/$companyId"
                )
            },

            onCreateCompany = {
                navController.navigate(Routes.COMPANY_CREATE) {
                    launchSingleTop = true
                }
            }
        )
    }
}

private fun NavGraphBuilder.companyCreateScreen(
    navController: NavHostController
) {
    composable(route = Routes.COMPANY_CREATE) {

        val companyCreateViewModel: CompanyCreateViewModel = hiltViewModel()

        CompanyCreateScreen(
            viewModel = companyCreateViewModel,

            onCompanyCreated = {
                navController.popBackStack(
                    route = Routes.COMPANY_LIST,
                    inclusive = false
                )
            }
        )
    }
}

private fun NavGraphBuilder.companyDetailScreen(
    navController: NavHostController
) {
    composable(
        route = "${Routes.COMPANY_DETAIL}/{companyId}",
        arguments = listOf(
            navArgument("companyId") {
                type = NavType.LongType
            }
        )
    ) {

        val companyDetailViewModel: CompanyDetailViewModel = hiltViewModel()

        CompanyDetailScreen(
            viewModel = companyDetailViewModel,

            onBack = {
                navController.popBackStack()
            }
        )
    }
}

