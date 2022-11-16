package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val FavoritesViewStateMapper: FavoritesMapper = FavoritesMapperImpl()

val FavoritesMoviesViewState = FavoritesViewStateMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesScreen(){

}

