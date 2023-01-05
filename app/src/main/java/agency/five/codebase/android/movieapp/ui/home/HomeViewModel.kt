package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    homeScreenMapper: HomeScreenMapper
) : ViewModel() {

    private val popularMovieCategorySelected: MutableStateFlow<MovieCategory> =
        MutableStateFlow(MovieCategory.POPULAR_ONTV)
    private val nowPlayingMovieCategorySelected: MutableStateFlow<MovieCategory> =
        MutableStateFlow(MovieCategory.NOWPLAYING_MOVIES)
    private val upcomingMoviesCateogrySelected: MutableStateFlow<MovieCategory> =
        MutableStateFlow(MovieCategory.UPCOMING_THISWEEK)

    @OptIn(ExperimentalCoroutinesApi::class)
    val popularMovies: StateFlow<HomeMovieCategoryViewState> =
        popularMovieCategorySelected.flatMapLatest { selectedMovieCategory ->
            movieRepository.popularMovies(movieCategory = selectedMovieCategory)
                .map { movies ->
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = listOf(
                            MovieCategory.POPULAR_STREAMING,
                            MovieCategory.POPULAR_ONTV,
                            MovieCategory.POPULAR_FORRENT,
                            MovieCategory.POPULAR_INTHEATERS
                        ),
                        selectedMovieCategory = selectedMovieCategory,
                        movies = movies
                    )
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMovieCategoryViewState(
                emptyList(), emptyList()
            )
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val nowPlayingMovies: StateFlow<HomeMovieCategoryViewState> =
        popularMovieCategorySelected.flatMapLatest { selectedMovieCategory ->
            movieRepository.nowPlayingMovies(movieCategory = selectedMovieCategory)
                .map { movies ->
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = listOf(
                            MovieCategory.NOWPLAYING_MOVIES,
                            MovieCategory.NOWPLAYING_TV
                        ),
                        selectedMovieCategory = selectedMovieCategory,
                        movies = movies
                    )
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMovieCategoryViewState(
                emptyList(), emptyList()
            )
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val upcomingMovies: StateFlow<HomeMovieCategoryViewState> =
        popularMovieCategorySelected.flatMapLatest { selectedMovieCategory ->
            movieRepository.upcomingMovies(movieCategory = selectedMovieCategory)
                .map { movies ->
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = listOf(
                            MovieCategory.UPCOMING_TODAY,
                            MovieCategory.UPCOMING_THISWEEK
                        ),
                        selectedMovieCategory = selectedMovieCategory,
                        movies = movies
                    )
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMovieCategoryViewState(
                emptyList(), emptyList()
            )
        )

    fun addToFavorites(movieId: Int) {
        viewModelScope.launch {
            movieRepository.addMovieToFavorites(movieId)
        }
    }

    fun removeFromFavorites(movieId: Int) {
        viewModelScope.launch {
            movieRepository.removeMovieFromFavorites(movieId)
        }
    }

    fun changeSelectedCategory(categoryId: Int) {
        viewModelScope.launch {
            when (categoryId) {
                MovieCategory.POPULAR_ONTV.ordinal,
                MovieCategory.POPULAR_STREAMING.ordinal,
                MovieCategory.POPULAR_INTHEATERS.ordinal,
                MovieCategory.POPULAR_FORRENT.ordinal
                -> {
                    popularMovieCategorySelected.update { MovieCategory.values()[categoryId] }
                }
                MovieCategory.NOWPLAYING_TV.ordinal,
                MovieCategory.NOWPLAYING_MOVIES.ordinal
                -> nowPlayingMovieCategorySelected.update { MovieCategory.values()[categoryId] }
                else -> {
                    upcomingMoviesCateogrySelected.update { MovieCategory.values()[categoryId] }
                }
            }
        }
    }
}
