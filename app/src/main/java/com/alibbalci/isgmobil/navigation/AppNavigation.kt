package com.alibbalci.isgmobil.navigation

import CompanyListScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
import com.alibbalci.isgmobil.presentation.components.BottomNavigationBar
import com.alibbalci.isgmobil.presentation.home.HomeScreen
import com.alibbalci.isgmobil.presentation.home.HomeViewModel
import com.alibbalci.isgmobil.presentation.observation.create.ObservationCreateScreen
import com.alibbalci.isgmobil.presentation.observation.create.ObservationCreateViewModel
import com.alibbalci.isgmobil.presentation.observation.detail.ObservationDetailScreen
import com.alibbalci.isgmobil.presentation.observation.detail.ObservationDetailViewModel
import com.alibbalci.isgmobil.presentation.observation.list.ObservationListScreen
import com.alibbalci.isgmobil.presentation.profile.ProfileScreen
import com.alibbalci.isgmobil.presentation.profile.ProfileViewModel
import com.alibbalci.isgmobil.presentation.session.SessionViewModel
import com.alibbalci.isgmobil.presentation.splash.SplashScreen
import com.alibbalci.isgmobil.session.SessionState

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        Routes.HOME,
        Routes.COMPANY_LIST,
        Routes.OBSERVATION_LIST,
        Routes.PROFILE
    )

    Scaffold(
        bottomBar = {

            if (currentRoute in bottomBarRoutes) {

                BottomNavigationBar(
                    currentRoute = currentRoute,

                    onHomeClick = {
                        navController.navigate(
                            Routes.HOME
                        ) {
                            launchSingleTop = true

                            popUpTo(Routes.HOME) {
                                inclusive = false
                            }
                        }
                    },

                    onCompaniesClick = {
                        navController.navigate(
                            Routes.COMPANY_LIST
                        ) {
                            launchSingleTop = true
                        }
                    },

                    onObservationsClick = {
                        navController.navigate(
                            Routes.OBSERVATION_LIST
                        ) {
                            launchSingleTop = true
                        }
                    },

                    onProfileClick = {
                        navController.navigate(
                            Routes.PROFILE
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {

            splashScreen(navController)
            loginScreen(navController)
            registerScreen(navController)
            homeScreen(navController)

            companyListScreen(navController)
            companyCreateScreen(navController)
            companyDetailScreen(navController)

            observationListScreen(navController)
            observationDetailScreen(navController)
            observationCreateScreen(navController)

            profileScreen(navController)
        }
    }
}

/*
 * SPLASH
 */
private fun NavGraphBuilder.splashScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.SPLASH
    ) {

        val sessionViewModel: SessionViewModel =
            hiltViewModel()

        val sessionState by
        sessionViewModel.sessionState
            .collectAsStateWithLifecycle()

        SplashScreen()

        LaunchedEffect(sessionState) {

            when (sessionState) {

                SessionState.Loading -> Unit

                SessionState.LoggedIn -> {

                    navController.navigate(
                        Routes.HOME
                    ) {

                        popUpTo(
                            Routes.SPLASH
                        ) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }

                SessionState.LoggedOut -> {

                    navController.navigate(
                        Routes.LOGIN
                    ) {

                        popUpTo(
                            Routes.SPLASH
                        ) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            }
        }
    }
}

/*
 * LOGIN
 */
private fun NavGraphBuilder.loginScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.LOGIN
    ) {

        val loginViewModel: LoginViewModel =
            hiltViewModel()

        LoginScreen(
            viewModel = loginViewModel,

            onNavigateToRegister = {

                navController.navigate(
                    Routes.REGISTER
                ) {
                    launchSingleTop = true
                }
            },

            onNavigateToHome = {

                navController.navigate(
                    Routes.HOME
                ) {

                    popUpTo(
                        Routes.LOGIN
                    ) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }
        )
    }
}

/*
 * REGISTER
 */
private fun NavGraphBuilder.registerScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.REGISTER
    ) {

        val registerViewModel: RegisterViewModel =
            hiltViewModel()

        RegisterScreen(
            viewModel = registerViewModel,

            onNavigateToLogin = {
                navController.popBackStack()
            }
        )
    }
}

/*
 * HOME
 */
private fun NavGraphBuilder.homeScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.HOME
    ) {

        val homeViewModel: HomeViewModel =
            hiltViewModel()

        HomeScreen(
            viewModel = homeViewModel,
            onNavigateToCompanies = {

                navController.navigate(
                    Routes.COMPANY_LIST
                ) {
                    launchSingleTop = true
                }
            },

            onNavigateToObservationCreate = {

                navController.navigate(
                    Routes.OBSERVATION_CREATE
                ) {
                    launchSingleTop = true
                }
            },

            onNavigateToObservations = {

                navController.navigate(
                    Routes.OBSERVATION_LIST
                ) {
                    launchSingleTop = true
                }
            }
        )
    }
}

/*
 * COMPANY LIST
 */
private fun NavGraphBuilder.companyListScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.COMPANY_LIST
    ) {

        val companyListViewModel:
                CompanyListViewModel =
            hiltViewModel()

        CompanyListScreen(
            viewModel = companyListViewModel,

            onCompanyClick = { companyId ->

                navController.navigate(
                    "${Routes.COMPANY_DETAIL}/$companyId"
                )
            },

            onCreateCompany = {

                navController.navigate(
                    Routes.COMPANY_CREATE
                ) {
                    launchSingleTop = true
                }
            }
        )
    }
}

/*
 * COMPANY CREATE
 */
private fun NavGraphBuilder.companyCreateScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.COMPANY_CREATE
    ) {

        val companyCreateViewModel:
                CompanyCreateViewModel =
            hiltViewModel()

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

/*
 * COMPANY DETAIL
 */
private fun NavGraphBuilder.companyDetailScreen(
    navController: NavHostController
) {

    composable(
        route =
            "${Routes.COMPANY_DETAIL}/{companyId}",

        arguments = listOf(

            navArgument(
                "companyId"
            ) {
                type = NavType.LongType
            }
        )
    ) {

        val companyDetailViewModel:
                CompanyDetailViewModel =
            hiltViewModel()

        CompanyDetailScreen(
            viewModel =
                companyDetailViewModel,

            onBack = {
                navController.popBackStack()
            }
        )
    }
}

/*
 * OBSERVATION LIST
 */
/*
 * OBSERVATION LIST
 */
private fun NavGraphBuilder.observationListScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.OBSERVATION_LIST
    ) {

        ObservationListScreen(
            onObservationClick = { observationId ->

                navController.navigate(
                    "${Routes.OBSERVATION_DETAIL}/$observationId"
                )
            }
        )
    }
}



/*
 * OBSERVATION DETAIL
 */
private fun NavGraphBuilder.observationDetailScreen(
    navController: NavHostController
) {

    composable(
        route =
            "${Routes.OBSERVATION_DETAIL}/{observationId}",

        arguments = listOf(
            navArgument(
                "observationId"
            ) {
                type = NavType.LongType
            }
        )
    ) {

        val observationDetailViewModel:
                ObservationDetailViewModel =
            hiltViewModel()

        ObservationDetailScreen(
            viewModel = observationDetailViewModel,

            onBack = {
                navController.popBackStack()
            }
        )
    }
}

/*
 * OBSERVATION CREATE
 */
/*
 * OBSERVATION CREATE
 */
private fun NavGraphBuilder.observationCreateScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.OBSERVATION_CREATE
    ) {

        val observationCreateViewModel:
                ObservationCreateViewModel =
            hiltViewModel()

        ObservationCreateScreen(
            viewModel =
                observationCreateViewModel,

            onBack = {
                navController.popBackStack()
            },

            onConfirmationSuccess = {

                navController.navigate(
                    Routes.OBSERVATION_LIST
                ) {

                    /*
                     * Observation Create ekranını
                     * back stack'ten siliyoruz.
                     *
                     * Böylece kullanıcı onaydan sonra
                     * geri tuşuna bastığında eski,
                     * tamamlanmış form ekranına dönmez.
                     */
                    popUpTo(
                        Routes.OBSERVATION_CREATE
                    ) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }
        )
    }
}
/*
 * PROFILE
 */
private fun NavGraphBuilder.profileScreen(
    navController: NavHostController
) {

    composable(
        route = Routes.PROFILE
    ) {

        val profileViewModel: ProfileViewModel =
            hiltViewModel()

        ProfileScreen(
            viewModel = profileViewModel,

            onLogout = {

                profileViewModel.logout(

                    onLogoutSuccess = {

                        navController.navigate(
                            Routes.LOGIN
                        ) {

                            popUpTo(
                                navController.graph.id
                            ) {
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