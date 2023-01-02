package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl : FavoritesMapper {
    override fun toFavoritesViewState(favouriteMovies: List<Movie>) =
        FavoritesViewState(favoritesMovieViewStates(favouriteMovies = favouriteMovies))

    private fun favoritesMovieViewStates(favouriteMovies: List<Movie>) =
        favouriteMovies.map {
            MovieCardViewState(
                movieId = it.id,
                imageUrl = it.imageUrl,
                isFavourite = it.isFavorite
            )
        }
}
