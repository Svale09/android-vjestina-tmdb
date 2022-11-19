package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock.getMoviesList
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.foundation.clickable as clickable

data class MovieCardViewState(
    val imageUrl: String?,
    val isFavourite: Boolean,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    onFavouriteToggle: (Boolean) -> Unit
) {
    Card(
        modifier
            .size(122.dp, 179.dp)
            .padding(Spacing().small)
            .clickable{onCardClick()},
        elevation = Spacing().small
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = movieCardViewState.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            FavouriteButton(
                modifier = Modifier.align(Alignment.TopStart),
                buttonViewState = FavouriteButtonState(false),
                onFavouriteToggle = onFavouriteToggle
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMovieCard() {
    val movies = getMoviesList()
    MovieCard(
        movieCardViewState = MovieCardViewState(movies[0].imageUrl, movies[0].isFavorite),
        onCardClick = {},
        onFavouriteToggle = {}
    )
}
