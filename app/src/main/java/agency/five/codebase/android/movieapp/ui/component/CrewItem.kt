package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


data class CrewmanViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewItem(
    crewMember: CrewmanViewState,
    modifier: Modifier,
) {
    Column(modifier.wrapContentSize()) {
        Text(
            text = crewMember.name,
            fontWeight = FontWeight.Bold
        )
        Text(text = crewMember.job)
    }
}

@Preview(showBackground = true)
@Composable
private fun CrewItemPreview() {
    CrewItem(crewMember = CrewmanViewState(name = "Jon Favreau", job = "Director"), modifier = Modifier)
}
