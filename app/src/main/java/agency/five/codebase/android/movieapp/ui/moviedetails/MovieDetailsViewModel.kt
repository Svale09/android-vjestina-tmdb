package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    MovieDetailsScreenMapper: MovieDetailsMapper,
    movieId: Int
) : ViewModel() {
    val movieDetailsViewState: StateFlow<MovieDetailsViewState> =
        movieRepository.movieDetails(movieId)
            .map { MovieDetailsViewState ->
                MovieDetailsScreenMapper.toMovieDetailsViewState(MovieDetailsViewState)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = MovieDetailsViewState(
                    id = 1,
                    imageUrl = "",
                    voteAverage = 0.0.toFloat(),
                    title = "",
                    overview = "",
                    isFavorite = false,
                    crew = emptyList(),
                    cast = emptyList()
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
}