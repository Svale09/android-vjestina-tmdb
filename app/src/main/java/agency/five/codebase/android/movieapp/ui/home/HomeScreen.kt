package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock.getMoviesList
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState
import agency.five.codebase.android.movieapp.ui.theme.CustomHeader
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ONTV,
        MovieCategory.POPULAR_FORRENT,
        MovieCategory.POPULAR_INTHEATERS,
    ),
    selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
    movies = getMoviesList()
)

val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.NOWPLAYING_MOVIES,
        MovieCategory.NOWPLAYING_TV,
    ),
    selectedMovieCategory = MovieCategory.NOWPLAYING_MOVIES,
    movies = getMoviesList()
)

val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THISWEEK
    ),
    selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
    movies = getMoviesList()
)

@Composable
fun HomeRoute(
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit
) {
    /*var popularMovies by remember { mutableStateOf(popularCategoryViewState) }
    var nowPlayingMovies by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingMovies by remember { mutableStateOf(upcomingCategoryViewState) }*/
    HomeScreen(
        onCategoryClick = {
            when (it.itemId) {
                MovieCategory.POPULAR_STREAMING.ordinal,
                MovieCategory.POPULAR_FORRENT.ordinal,
                MovieCategory.POPULAR_ONTV.ordinal,
                MovieCategory.POPULAR_INTHEATERS.ordinal -> homeScreenMapper.toHomeMovieCategoryViewState(
                    listOf(
                        MovieCategory.POPULAR_STREAMING,
                        MovieCategory.POPULAR_ONTV,
                        MovieCategory.POPULAR_FORRENT,
                        MovieCategory.POPULAR_INTHEATERS,
                    ),
                    MovieCategory.values()[it.itemId],
                    getMoviesList()
                )
                MovieCategory.NOWPLAYING_MOVIES.ordinal,
                MovieCategory.NOWPLAYING_TV.ordinal -> homeScreenMapper.toHomeMovieCategoryViewState(
                    listOf(
                        MovieCategory.NOWPLAYING_MOVIES,
                        MovieCategory.NOWPLAYING_TV
                    ),
                    MovieCategory.values()[it.itemId],
                    getMoviesList()
                )
                else -> homeScreenMapper.toHomeMovieCategoryViewState(
                    listOf(
                        MovieCategory.UPCOMING_TODAY,
                        MovieCategory.UPCOMING_THISWEEK
                    ),
                    MovieCategory.values()[it.itemId],
                    getMoviesList()
                )
            }
        },
        onNavigateToMovieDetails = onNavigateToMovieDetails
    )
}

@Composable
fun HomeScreen(
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
    padding: Spacing = Spacing()
) {
    Scaffold(
        topBar = { TopBar(onBackClick = {}) },
        bottomBar = { BottomBar(onHomeClick = {}, onFavouriteClick = {}) },
        content = { padding ->
            HomeScreenBody(
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
    HomeScreen({},{})
}

@Composable
fun HomeScreenBody(
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
    padding: Spacing
) {
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
        item {
            HomeScreenCategoryHeader("Popular")
            HomeScreenCategoryList(popularCategoryViewState)
            HomeScreenCategoryMoviesList(
                movieCategory = popularCategoryViewState,
                onCategoryClick = onCategoryClick,
                onCardClick = onNavigateToMovieDetails
            )
        }
        item {
            HomeScreenCategoryHeader(sectionTitle = "Now Playing")
            HomeScreenCategoryList(nowPlayingCategoryViewState)
            HomeScreenCategoryMoviesList(
                movieCategory = nowPlayingCategoryViewState,
                onCategoryClick = onCategoryClick,
                onCardClick = onNavigateToMovieDetails
            )
        }
        item {
            HomeScreenCategoryHeader(sectionTitle = "Upcoming")
            HomeScreenCategoryList(upcomingCategoryViewState)
            HomeScreenCategoryMoviesList(
                movieCategory = upcomingCategoryViewState,
                onCategoryClick = onCategoryClick,
                onCardClick = onNavigateToMovieDetails
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreenBody() {
    HomeScreenBody(padding = Spacing())
}

@Composable
fun HomeScreenCategoryList(
    categories: HomeMovieCategoryViewState
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = Spacing().medium, vertical = Spacing().small),
        horizontalArrangement = Arrangement.spacedBy(Spacing().large)
    ) {
        items(categories.movieCategories.count()) { item ->
            MovieCategoryLabel(
                movieCategoryLabelViewState = categories.movieCategories[item],
                onClick = {})
        }
    }
}

@Composable
fun HomeScreenCategoryMoviesList(
    movieCategory: HomeMovieCategoryViewState,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onCardClick: (HomeMovieViewState) -> Unit
) {
    val movies = movieCategory.movies
    LazyRow(modifier = Modifier.padding(Spacing().small)) {
        items(movies.count()) { item ->
            MovieCard(
                movieCardViewState = MovieCardViewState(
                    movies[item].imageUrl,
                    movies[item].isFavorite
                ),
                onCardClick = { onCardClick },
                onFavouriteToggle = {},
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
