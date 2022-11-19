package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Blue
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.tmdb_logo),
                contentDescription = "TMDB logo",
            )
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Back arrow",
                Modifier.clickable { onBackClick() }
                    .padding(Spacing().medium)
            )
        },
        backgroundColor = Blue
    )
}

@Preview
@Composable
private fun PreviewTopBar() {
    MovieAppTheme() {
        TopBar(onBackClick = {})
    }
}
