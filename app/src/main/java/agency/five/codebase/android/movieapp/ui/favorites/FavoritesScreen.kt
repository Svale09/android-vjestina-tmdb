package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.home.HomeMovieViewState
import agency.five.codebase.android.movieapp.ui.theme.CustomHeader
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

private val FavoritesViewStateMapper: FavoritesMapper = FavoritesMapperImpl()

val FavoritesMoviesViewState =
    FavoritesViewStateMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
    onFavouriteToggleButton: (Boolean) -> Unit
) {
    FavoritesScreen(onNavigateToMovieDetails, onFavouriteToggleButton)
}

@Composable
fun FavoritesScreen(
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
    onFavouriteToggleButton: (Boolean) -> Unit
) {
    Scaffold(
        /*topBar = { TopBar(onBackClick = {}) },
        bottomBar = { BottomBar(onHomeClick = {}, onFavouriteClick = {}) },*/
        content = { padding ->
            FavouritesBody(
                favouriteMoviesViewState = FavoritesMoviesViewState,
                padding = Spacing(),
                onNavigateToMovieDetails = onNavigateToMovieDetails,
                onFavouriteToggleButton = onFavouriteToggleButton,
            )
        }
    )
}

@Composable
fun FavouritesBody(
    favouriteMoviesViewState: FavoritesViewState,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
    onFavouriteToggleButton: (Boolean) -> Unit,
    padding: Spacing
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        Modifier
            .padding(padding.small)
            .fillMaxSize(),
        content = {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Text(
                    modifier = Modifier.padding(bottom = Spacing().small, start = Spacing().small),
                    text = "Favorites",
                    style = CustomHeader
                )
            }
            items(favouriteMoviesViewState.favouriteMovies.size) { index ->
                MovieCard(
                    movieCardViewState = favouriteMoviesViewState.favouriteMovies[index],
                    onNavigateToMovieDetails = onNavigateToMovieDetails,
                    onFavouriteToggle = onFavouriteToggleButton
                )
            }
        }
    )
}

@Preview
@Composable
private fun PreviewFavoriteScreen() {
    FavoritesScreen({},{})
}
