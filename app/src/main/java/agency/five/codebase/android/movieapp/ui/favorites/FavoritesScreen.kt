package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.runtime.Composable

private val FavoritesViewStateMapper: FavoritesMapper = FavoritesMapperImpl()

val FavoritesMoviesViewState = FavoritesViewStateMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesScreen(){

}
