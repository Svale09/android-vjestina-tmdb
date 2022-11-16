package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import android.text.Layout.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text



@Composable
fun BottomBar(
    modifier: Modifier,
) {
    Row(
        modifier
            .padding(Spacing().medium)
            .fillMaxWidth(),
        Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Arrangem
        ) {
            Image(
                painter = painterResource(id = R.drawable.homeicon_selected),
                contentDescription = "Home icon"
            )
            Spacer(modifier = Modifier.height(Spacing().small))
            Text(text = "Home")
        }
        Column() {
            Image(
                painter = painterResource(id = R.drawable.favuoirteicon_selected),
                contentDescription = "Favourite icon"
            )
            Spacer(modifier = Modifier.height(Spacing().small))
            Text(text = "Favourites")
        }
    }
}

@Preview
@Composable
private fun PreviewBottomBar() {
    BottomBar(modifier = Modifier)
}
