package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.BottomBar
import agency.five.codebase.android.movieapp.ui.component.FavouriteButton
import agency.five.codebase.android.movieapp.ui.component.TopBar
import agency.five.codebase.android.movieapp.ui.component.UserScoreProgressBar
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.CustomHeader
import agency.five.codebase.android.movieapp.ui.theme.SectionTitle
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage


private val MovieDetailsScreenMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val MovieDetailsScreenViewState =
    MovieDetailsScreenMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsScreen(
    movieDetailsScreenViewState: MovieDetailsViewState
) {
    Scaffold(
        topBar = { TopBar(onBackClick = {}) },
        bottomBar = { BottomBar(onHomeClick = {}, onFavouriteClick = {}) },
        content = { padding -> MovieDetailsBody(padding = Spacing(), movieDetailsScreenViewState) }
    )
}

@Composable
fun MovieDetailsBody(
    padding: Spacing,
    movieDetailsScreenViewState: MovieDetailsViewState
) {

}

@Composable
fun Poster(movieDetailsScreenViewState: MovieDetailsViewState) {
    ConstraintLayout {
        val (image, info) = createRefs()
        AsyncImage(
            model = movieDetailsScreenViewState.imageUrl,
            contentDescription = "Movie poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .constrainAs(image) {}
        )
        FavouriteButton(
            isFavourite = movieDetailsScreenViewState.isFavorite,
            modifier = Modifier.padding(Spacing().medium),
            onFavouriteToggle = {}
        )
        Box(
            Modifier
                .constrainAs(info) { bottom.linkTo(image.bottom) }
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black),
                        startY = 0f
                    )
                )
                .fillMaxWidth()
        ) {
            Column(
                Modifier.padding(22.dp)
            ) {
                Row(
                    Modifier.padding(vertical = Spacing().small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UserScoreProgressBar(movieProgress = movieDetailsScreenViewState.voteAverage)
                    Text(
                        text = "User score",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = Spacing().small)
                    )
                }
                Text(
                    text = movieDetailsScreenViewState.title,
                    style = CustomHeader,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = Spacing().small)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPoster() {
    Poster(movieDetailsScreenViewState = MovieDetailsScreenViewState)
}

@Composable
fun MovieOverview(
    movieDetailsScreenViewState: MovieDetailsViewState
) {
    Column(Modifier.padding(Spacing().medium)) {
        Text(
            text = "Overview",
            style = SectionTitle,
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .padding(bottom = Spacing().small)
        )
        Text(
            text = movieDetailsScreenViewState.overview,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun PreviewMovieOverview() {
    MovieOverview(movieDetailsScreenViewState = MovieDetailsScreenViewState)
}
