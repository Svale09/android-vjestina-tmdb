package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.mock.MoviesMock.getActor
import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.ui.component.ActorCard
import agency.five.codebase.android.movieapp.ui.component.FavouriteButton
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}

@Preview
@Composable
private fun MainActivityPreview(){
    MovieAppTheme {
        MainScreen()
    }
}
