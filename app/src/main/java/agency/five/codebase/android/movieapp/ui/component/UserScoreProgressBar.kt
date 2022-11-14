package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock.getMovieDetails
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserScoreProgressBar(
    movieProgress: Float,
    modifier: Modifier = Modifier
) {
    Box(modifier.wrapContentSize()) {
        Canvas(
            modifier = Modifier
                .size(80.dp)
                .padding(10.dp),
        ) {
            drawArc(
                color = Color(152, 237, 159),
                alpha = 0.2f,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 6.dp.toPx())
            )
            drawArc(
                color = Color(61, 235, 75),
                startAngle = 270f,
                sweepAngle = movieProgress * 360f,
                useCenter = false,
                style = Stroke(width = 6.dp.toPx())
            )
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = (movieProgress * 100).toString(),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
private fun PreviewUserScoreProgressBar() {
    val rating: Float = getMovieDetails().voteAverage
    UserScoreProgressBar(rating)
}
