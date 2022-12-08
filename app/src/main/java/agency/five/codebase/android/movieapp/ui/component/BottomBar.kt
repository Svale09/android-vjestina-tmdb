package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun BottomBar(
    onHomeClick: () -> Unit,
    onFavouriteClick: () -> Unit
) {
    BottomAppBar (backgroundColor = Color.White){
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(id = R.drawable.home_icon_selected),
                    contentDescription = "Home icon",
                    Modifier.clickable { onHomeClick() }
                )
                Text(text = "Home")
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(id = R.drawable.favuoirte_icon_selected),
                    contentDescription = "Favourite icon",
                    Modifier.clickable { onFavouriteClick() }
                )
                Text(text = "Favourites")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBottomBar() {
    BottomBar({}, {})
}
