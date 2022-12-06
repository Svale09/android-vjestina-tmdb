package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.CustomHeader
import agency.five.codebase.android.movieapp.ui.theme.SectionTitle
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage


private val MovieDetailsScreenMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val MovieDetailsScreenViewState =
    MovieDetailsScreenMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute() {
    MovieDetailsScreen(MovieDetailsScreenViewState)
}

@Composable
fun MovieDetailsScreen(
    movieDetailsScreenViewState: MovieDetailsViewState
) {
    Scaffold(
        content = { padding ->
            MovieDetailsBody(
                padding = Spacing(),
                movieDetailsScreenViewState = movieDetailsScreenViewState
            )
        }
    )
}

@Composable
fun MovieDetailsBody(
    padding: Spacing,
    movieDetailsScreenViewState: MovieDetailsViewState
) {
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .verticalScroll(scrollState)
            .padding(padding.default)
            .fillMaxSize()
    )
    {
        Poster(movieDetailsScreenViewState = movieDetailsScreenViewState)
        MovieOverview(overview = movieDetailsScreenViewState.overview)
        CrewGrid(crewmen = movieDetailsScreenViewState.crew)
        CastGrid(cast = movieDetailsScreenViewState.cast)
    }
}

@Preview
@Composable
private fun PreviewMovieDetailsBody() {
    MovieDetailsBody(padding = Spacing(), movieDetailsScreenViewState = MovieDetailsScreenViewState)
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
    overview: String,
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
            text = overview,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun PreviewMovieOverview() {
    MovieOverview(overview = MovieDetailsScreenViewState.overview)
}

@Composable
fun CrewGrid(
    crewmen: List<CrewmanViewState>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing().medium)
            .wrapContentHeight(), //crash happens if the height isn't set to an exact measurements
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(Spacing().medium),
        horizontalArrangement = Arrangement.spacedBy(42.dp),
        userScrollEnabled = false,
        content = {
            items(crewmen.size) { index ->
                CrewItem(crewMember = crewmen[index], modifier = Modifier)
            }
        }
    )
}

@Preview
@Composable
private fun PreviewCrewGrid() {
    CrewGrid(crewmen = MovieDetailsScreenViewState.crew)
}

@Composable
fun CastGrid(
    cast: List<ActorViewState>
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(text = "Top Billed Cast", style = SectionTitle, modifier = Modifier.padding(10.dp))
        LazyRow() {
            items(cast.size) { index ->
                ActorCard(actorCardViewState = cast[index])
            }
        }
    }

}

@Preview
@Composable
private fun PreviewCastGrid() {
    CastGrid(cast = MovieDetailsScreenViewState.cast)
}
