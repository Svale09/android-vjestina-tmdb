package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState

class FavoritesMapperImpl : FavoritesMapper {
    override fun toFavoritesViewState(favouriteMovies: List<Movie>): FavoritesViewState {
        val favouriteMoviesCardViewState: MutableList<MovieCardViewState> = mutableListOf()
        for (favouriteMovie in favouriteMovies) {
            favouriteMoviesCardViewState.add(
                MovieCardViewState(
                    favouriteMovie.imageUrl,
                    favouriteMovie.isFavorite
                )
            )
        }
        return FavoritesViewState(favouriteMoviesCardViewState)
    }
}
