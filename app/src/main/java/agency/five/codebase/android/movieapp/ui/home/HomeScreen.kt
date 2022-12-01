package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.BottomBar
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.component.TopBar
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
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
    movies = MoviesMock.getMoviesList()
)

val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(
        MovieCategory.NOWPLAYING_MOVIES,
        MovieCategory.NOWPLAYING_MOVIES,
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
fun HomeScreenBody() {
    LazyColumn {
        item {
            HomeScreenCategoryList()
            LazyRow() {

            }
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreenBody() {
    HomeScreenBody()
}

@Composable
fun HomeScreenCategoryList(){
    LazyRow(
        modifier = Modifier.padding(Spacing().medium),
        horizontalArrangement = Arrangement.spacedBy(Spacing().large)
    ) {
        items(popularCategoryViewState.movieCategories.count()){
                item ->
            MovieCategoryLabel(movieCategoryLabelViewState = popularCategoryViewState.movieCategories[item], onClick = {})
        }
    }
}
