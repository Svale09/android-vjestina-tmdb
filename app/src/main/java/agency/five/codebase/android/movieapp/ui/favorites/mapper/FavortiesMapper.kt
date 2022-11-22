package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.model.Movie

interface FavoritesMapper {
    fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState
}
