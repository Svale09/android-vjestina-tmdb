package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.mock.MoviesMock.getMoviesList
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
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
    movies = MoviesMock.getMoviesList()
)

val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.NOWPLAYING_MOVIES,
        MovieCategory.NOWPLAYING_TV,
    ),
    selectedMovieCategory = MovieCategory.NOWPLAYING_MOVIES,
    movies = MoviesMock.getMoviesList()
)

val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THISWEEK
    ),
    selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
    movies = MoviesMock.getMoviesList()
)

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopBar(onBackClick = {}) },
        bottomBar = { BottomBar(onHomeClick = {}, onFavouriteClick = {}) },
        content = {}
    )

}

@Composable
fun HomeScreenBody(
) {
    LazyColumn {
        item {
            HomeScreenCategoryHeader("Popular")
            HomeScreenCategoryList(popularCategoryViewState)
            HomeScreenCategoryMoviesList(
                movieCategory = popularCategoryViewState,
                onCategoryClick = {})
        }
        item {
            HomeScreenCategoryHeader(sectionTitle = "Now Playing")
            HomeScreenCategoryList(nowPlayingCategoryViewState)
            HomeScreenCategoryMoviesList(movieCategory = nowPlayingCategoryViewState, onCategoryClick = {})
        }
        item {
            HomeScreenCategoryHeader(sectionTitle = "Upcoming")
            HomeScreenCategoryList(upcomingCategoryViewState)
            HomeScreenCategoryMoviesList(movieCategory = upcomingCategoryViewState, onCategoryClick = {})
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreenBody() {
    HomeScreenBody()
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
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit
) {
    val movies = movieCategory.movies
    LazyRow(modifier = Modifier.padding(Spacing().small)) {
        items(movies.count()) { item ->
            MovieCard(
                movieCardViewState = MovieCardViewState(
                    movies[item].imageUrl,
                    movies[item].isFavorite
                ),
                onCardClick = { /*TODO*/ },
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
