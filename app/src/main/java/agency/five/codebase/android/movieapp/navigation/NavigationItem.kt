package agency.five.codebase.android.movieapp.navigation

import agency.five.codebase.android.movieapp.R

const val HOME_ROUTE = "Home"
const val FAVORITES_ROUTE = "Favorites"

sealed class NavigationItem(
    override val route: String,
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val labelId: Int,
) : MovieAppDestination(route) {
    object HomeDestination : NavigationItem(
        route = HOME_ROUTE,
        selectedIconId = R.drawable.homeicon_selected,
        unselectedIconId = R.drawable.homeicon_unselected,
        labelId = R.string.home,
    )

    object FavoritesDestination : NavigationItem(
        route = FAVORITES_ROUTE,
        selectedIconId = R.drawable.favuoirteicon_selected,
        unselectedIconId = R.drawable.favouriteicon_unselected,
        labelId = R.string.favorites,
    )
}
