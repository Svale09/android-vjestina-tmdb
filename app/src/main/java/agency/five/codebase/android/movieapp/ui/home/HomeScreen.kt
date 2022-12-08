package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock.getMoviesList
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.CustomHeader
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val popularCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(
        listOf(
            MovieCategory.POPULAR_STREAMING,
            MovieCategory.POPULAR_ONTV,
            MovieCategory.POPULAR_FORRENT,
            MovieCategory.POPULAR_INTHEATERS
        ), MovieCategory.POPULAR_STREAMING, getMoviesList()
    )
val nowPlayingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(
        listOf(
            MovieCategory.NOWPLAYING_TV,
            MovieCategory.NOWPLAYING_MOVIES
        ), MovieCategory.NOWPLAYING_TV, getMoviesList()
    )
val upcomingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(
        listOf(
            MovieCategory.UPCOMING_TODAY,
            MovieCategory.UPCOMING_THISWEEK
        ), MovieCategory.UPCOMING_TODAY, getMoviesList()
    )

@Composable
fun HomeRoute(
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
) {
    var popularMovies by remember { mutableStateOf(popularCategoryViewState) }
    var nowPlayingMovies by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingMovies by remember { mutableStateOf(upcomingCategoryViewState) }

    HomeScreen(
        popularMovies,
        nowPlayingMovies,
        upcomingMovies,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onCategoryClick = {
            when (it.itemId) {
                MovieCategory.POPULAR_STREAMING.ordinal,
                MovieCategory.POPULAR_FORRENT.ordinal,
                MovieCategory.POPULAR_ONTV.ordinal,
                MovieCategory.POPULAR_INTHEATERS.ordinal
                -> popularMovies =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        listOf(
                            MovieCategory.POPULAR_STREAMING,
                            MovieCategory.POPULAR_ONTV,
                            MovieCategory.POPULAR_FORRENT,
                            MovieCategory.POPULAR_INTHEATERS
                        ),
                        MovieCategory.values()[it.itemId],
                        getMoviesList()
                    )
                MovieCategory.NOWPLAYING_MOVIES.ordinal,
                MovieCategory.NOWPLAYING_TV.ordinal
                -> nowPlayingMovies =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        listOf(
                            MovieCategory.NOWPLAYING_TV,
                            MovieCategory.NOWPLAYING_MOVIES
                        ),
                        MovieCategory.values()[it.itemId],
                        getMoviesList()
                    )
                else -> upcomingMovies =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        listOf(
                            MovieCategory.UPCOMING_TODAY,
                            MovieCategory.UPCOMING_THISWEEK
                        ),
                        MovieCategory.values()[it.itemId],
                        getMoviesList()
                    )
            }
        }
    )
}

@Composable
fun HomeScreen(
    popularMovies: HomeMovieCategoryViewState,
    nowPlayingMovies: HomeMovieCategoryViewState,
    upcomingMovies: HomeMovieCategoryViewState,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
    padding: Spacing = Spacing()
) {
    Scaffold(
        content = { padding ->
            HomeScreenBody(
                popularMovies,
                nowPlayingMovies,
                upcomingMovies,
                padding = Spacing(),
                onCategoryClick = onCategoryClick,
                onNavigateToMovieDetails = onNavigateToMovieDetails
            )
        }
    )
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        popularMovies = popularCategoryViewState,
        nowPlayingMovies = nowPlayingCategoryViewState,
        upcomingMovies = upcomingCategoryViewState,
        onCategoryClick = {},
        onNavigateToMovieDetails = {}
    )
}

@Composable
fun HomeScreenBody(
    popularMovies: HomeMovieCategoryViewState,
    nowPlayingMovies: HomeMovieCategoryViewState,
    upcomingMovies: HomeMovieCategoryViewState,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
    padding: Spacing
) {
    LazyColumn(modifier = Modifier.padding(top = Spacing().small)) {
        item {
            HomeScreenCategoryHeader("Popular")
            HomeScreenCategoryList(
                popularMovies,
                onCategoryClick = onCategoryClick
            )
            HomeScreenCategoryMoviesList(
                movieCategory = popularMovies,
                onFavoriteToggle = {},
                onNavigateToMovieDetails = onNavigateToMovieDetails
            )
        }
        item {
            HomeScreenCategoryHeader(sectionTitle = "Now Playing")
            HomeScreenCategoryList(
                nowPlayingMovies,
                onCategoryClick
            )
            HomeScreenCategoryMoviesList(
                movieCategory = nowPlayingMovies,
                onFavoriteToggle = {},
                onNavigateToMovieDetails = onNavigateToMovieDetails
            )
        }
        item {
            HomeScreenCategoryHeader(sectionTitle = "Upcoming")
            HomeScreenCategoryList(
                upcomingMovies,
                onCategoryClick
            )
            HomeScreenCategoryMoviesList(
                movieCategory = upcomingMovies,
                onNavigateToMovieDetails = onNavigateToMovieDetails,
                onFavoriteToggle = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreenBody() {
    HomeScreenBody(
        popularMovies = popularCategoryViewState,
        nowPlayingMovies = nowPlayingCategoryViewState,
        upcomingMovies = upcomingCategoryViewState,
        onCategoryClick = {},
        onNavigateToMovieDetails = {},
        padding = Spacing()
    )
}

@Composable
fun HomeScreenCategoryList(
    categories: HomeMovieCategoryViewState,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = Spacing().medium, vertical = Spacing().small),
        horizontalArrangement = Arrangement.spacedBy(Spacing().large)
    ) {
        items(categories.movieCategories.count()) { item ->
            MovieCategoryLabel(
                movieCategoryLabelViewState = categories.movieCategories[item],
                onClick = onCategoryClick
            )
        }
    }
}

@Composable
fun HomeScreenCategoryMoviesList(
    movieCategory: HomeMovieCategoryViewState,
    onFavoriteToggle: (Boolean) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit
) {
    val movies = movieCategory.movies
    LazyRow(modifier = Modifier.padding(Spacing().small)) {
        items(movies.count()) { item ->
            MovieCard(
                movieCardViewState = MovieCardViewState(
                    movies[item].id,
                    movies[item].imageUrl,
                    movies[item].isFavorite
                ),
                onNavigateToMovieDetails = onNavigateToMovieDetails,
                onFavouriteToggle = onFavoriteToggle,
                modifier = Modifier
                    .height(200.dp)
                    .width(130.dp)
            )
        }
    }
}

@Composable
fun HomeScreenCategoryHeader(
    sectionTitle: String
) {
    Text(
        text = sectionTitle,
        modifier = Modifier.padding(horizontal = Spacing().medium),
        style = CustomHeader
    )
}
