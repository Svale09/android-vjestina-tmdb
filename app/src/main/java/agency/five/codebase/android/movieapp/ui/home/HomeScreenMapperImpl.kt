package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelTextViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState


class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>
    ): HomeMovieCategoryViewState {
        return HomeMovieCategoryViewState(
            moviecategories = toHomeMovieCategoryLabelViewState(
                movieCategories,
                selectedMovieCategory
            ),
            movies = toHomeMovieViewState(movies)
        )
    }

    private fun toHomeMovieViewState(movies: List<Movie>): List<HomeMovieViewState> {
        val homeMoviesViewStates: MutableList<HomeMovieViewState> = mutableListOf()
        for (movie in movies) {
            homeMoviesViewStates.add(
                HomeMovieViewState(
                    movie.id,
                    movie.imageUrl,
                    movie.isFavorite
                )
            )
        }
        return homeMoviesViewStates
    }

    private fun toHomeMovieCategoryLabelViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory
    ): List<MovieCategoryLabelViewState> {
        val movieCategoryLabelViewState: MutableList<MovieCategoryLabelViewState> = mutableListOf()
        for (category in movieCategories) {
            movieCategoryLabelViewState.add(
                MovieCategoryLabelViewState(
                    category.ordinal,
                    isSelected = category == selectedMovieCategory,
                    categoryText = MovieCategoryLabelTextViewState.MovieCategoryStringResource(
                        when (category) {
                            MovieCategory.POPULAR_STREAMING -> R.string.streaming
                            MovieCategory.POPULAR_FORRENT -> R.string.for_rent
                            MovieCategory.POPULAR_ONTV -> R.string.on_tv
                            MovieCategory.POPULAR_INTHEATERS -> R.string.in_theaters
                            MovieCategory.NOWPLAYING_MOVIES -> R.string.movies
                            MovieCategory.NOWPLAYING_TV -> R.string.tv
                            MovieCategory.UPCOMING_THISWEEK -> R.string.this_week
                            MovieCategory.UPCOMING_TODAY -> R.string.today
                        }
                    )
                )
            )
        }
        return movieCategoryLabelViewState
    }
}

data class HomeMovieCategoryViewState(
    val moviecategories: List<MovieCategoryLabelViewState>,
    val movies: List<HomeMovieViewState>
)

data class HomeMovieViewState(
    val id: Int,
    val imageUrl: String?,
    val isFavorite: Boolean,
)
