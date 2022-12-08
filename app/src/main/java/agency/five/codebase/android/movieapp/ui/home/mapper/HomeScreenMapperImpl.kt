package agency.five.codebase.android.movieapp.ui.home.mapper

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelTextViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieCategoryViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieViewState


class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>
    ) = HomeMovieCategoryViewState(
        movieCategories = toHomeMovieCategoryLabelViewState(
            movieCategories,
            selectedMovieCategory
        ), toHomeMovieViewState(movies)
    )

    private fun toHomeMovieViewState(movies: List<Movie>) =
        movies.map {
            HomeMovieViewState(
                isFavorite = it.isFavorite,
                imageUrl = it.imageUrl,
                id = it.id
            )
        }

    private fun toHomeMovieCategoryLabelViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory
    ) = movieCategories.map {
        MovieCategoryLabelViewState(
            itemId = it.ordinal,
            isSelected = it == selectedMovieCategory,
            categoryText = MovieCategoryLabelTextViewState.MovieCategoryStringResource(when (it){
                MovieCategory.POPULAR_STREAMING -> R.string.streaming
                MovieCategory.POPULAR_ONTV -> R.string.on_tv
                MovieCategory.POPULAR_FORRENT -> R.string.for_rent
                MovieCategory.POPULAR_INTHEATERS -> R.string.in_theaters
                MovieCategory.NOWPLAYING_TV -> R.string.tv
                MovieCategory.NOWPLAYING_MOVIES -> R.string.movies
                MovieCategory.UPCOMING_TODAY -> R.string.today
                MovieCategory.UPCOMING_THISWEEK -> R.string.this_week
            })
        )
    }
}
