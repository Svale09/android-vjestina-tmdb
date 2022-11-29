package agency.five.codebase.android.movieapp.ui.component


import agency.five.codebase.android.movieapp.ui.theme.Gray700
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


sealed class MovieCategoryLabelTextViewState {
    data class MovieCategoryStringParam(val value: String) : MovieCategoryLabelTextViewState()
    data class MovieCategoryStringResource(@StringRes val value: Int) :
        MovieCategoryLabelTextViewState()
}

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit
) {
    Box(modifier = modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .width(intrinsicSize = IntrinsicSize.Max)
                .clickable { onClick(true) }
        ) {
            Text(
                text = when (movieCategoryLabelViewState.categoryText) {
                    is MovieCategoryLabelTextViewState.MovieCategoryStringParam -> movieCategoryLabelViewState.categoryText.value
                    is MovieCategoryLabelTextViewState.MovieCategoryStringResource -> stringResource(id = movieCategoryLabelViewState.categoryText.value)
                },
                fontSize = 24.sp,
                fontWeight = if (movieCategoryLabelViewState.isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (movieCategoryLabelViewState.isSelected) Color.Black else Gray700,
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (movieCategoryLabelViewState.isSelected) {
                Box(
                    modifier = Modifier
                        .height(3.dp)
                        .fillMaxWidth()
                        .background(color = Color(0xFF0b253f))
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMovieCategoryLabel() {
    MovieCategoryLabel(
        movieCategoryLabelViewState = MovieCategoryLabelViewState(
            1,
            false,
            MovieCategoryLabelTextViewState.MovieCategoryStringParam("Action")
        ),
        modifier = Modifier,
        onClick = {}
    )
}
