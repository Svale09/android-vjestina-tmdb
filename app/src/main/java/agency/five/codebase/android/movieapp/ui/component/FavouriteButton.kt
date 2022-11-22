package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class FavouriteButtonState(
    var isFavourite: Boolean
)

@Composable
fun FavouriteButton(
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
    onFavouriteToggle: (Boolean) -> Unit = { },
) {
    Box(
        modifier
            .padding(5.dp)
            .size(37.dp)
            .clip(shape = CircleShape)
            .background(Color(0x0B253F).copy(alpha = 0.6F))
            .clickable { onFavouriteToggle( isFavourite) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                id =
                if (isFavourite) R.drawable.favuoirteicon_selected else R.drawable.favouriteicon_unselected
            ),
            contentDescription = "Favourite Icon",
        )
    }
}


@Preview
@Composable
private fun PreviewFavouriteButton() {
    FavouriteButton(false)
}
