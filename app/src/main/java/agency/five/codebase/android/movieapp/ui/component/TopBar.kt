package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(color = Color(0xFF0b253f))
            .padding(Spacing().medium),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "Back arrow"
        )
        Image(
            painter = painterResource(id = R.drawable.tmdb_logo),
            contentDescription = "TMDB Logo"
        )
        Spacer(
            Modifier
                .height(2.dp)
                .width(2.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewTopBar() {
    MovieAppTheme() {
        TopBar(modifier = Modifier)
    }
}
