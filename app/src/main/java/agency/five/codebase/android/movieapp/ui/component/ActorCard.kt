package agency.five.codebase.android.movieapp.ui.component


import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import agency.five.codebase.android.movieapp.mock.MoviesMock.getActor

data class ActorViewState(
    val name: String,
    val character: String,
    val imageUrl: String
)

@Composable
fun ActorCard(
    actorCardViewState: ActorViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier
            .size(width = 124.dp, height = 210.dp)
            .padding(10.dp),
        elevation = 10.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = actorCardViewState.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 290f
                        )
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = actorCardViewState.name,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = actorCardViewState.character,
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewActorCard() {
    MovieAppTheme {
        ActorCard(
            actorCardViewState = ActorViewState(getActor().name, getActor().character, getActor().imageUrl.toString())
        )
    }
}
