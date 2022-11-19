package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.BottomBar
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.TopBar
import agency.five.codebase.android.movieapp.ui.theme.Blue
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

private val FavoritesViewStateMapper: FavoritesMapper = FavoritesMapperImpl()

val FavoritesMoviesViewState =
    FavoritesViewStateMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesScreen() {
    Scaffold(
        topBar = { TopBar(onBackClick = {}) },
        bottomBar = { BottomBar(onHomeClick = {}, onFavouriteClick = {}) },
        content = { padding ->
            FavouritesBody(
                favouriteMoviesViewState = FavoritesMoviesViewState,
                padding = Spacing()
            )
        }
    )
}

@Composable
fun FavouritesBody(
    favouriteMoviesViewState: FavoritesViewState,
    padding: Spacing
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        Modifier
            .padding(padding.medium)
            .fillMaxSize(),
        content = {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Text(
                    modifier = Modifier.padding(Spacing().small),
                    text = "Favorites",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )
            }
            items(favouriteMoviesViewState.favouriteMovies.size) { index ->
                MovieCard(
                    movieCardViewState = favouriteMoviesViewState.favouriteMovies[index],
                    onCardClick = { },
                    onFavouriteToggle = { }
                )
            }
        }
    )
}

@Preview
@Composable
private fun PreviewFavoriteScreen() {
    FavoritesScreen()
}
