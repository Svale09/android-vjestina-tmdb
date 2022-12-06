package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl : FavoritesMapper {
    override fun toFavoritesViewState(favouriteMovies: List<Movie>): FavoritesViewState {
        val favouriteMoviesCardViewState: MutableList<MovieCardViewState> = mutableListOf()
        for (favouriteMovie in favouriteMovies) {
            favouriteMoviesCardViewState.add(
                MovieCardViewState(
                    favouriteMovie.id,
                    favouriteMovie.imageUrl,
                    favouriteMovie.isFavorite,
                )
            )
        }
        return FavoritesViewState(favouriteMoviesCardViewState)
    }
}
