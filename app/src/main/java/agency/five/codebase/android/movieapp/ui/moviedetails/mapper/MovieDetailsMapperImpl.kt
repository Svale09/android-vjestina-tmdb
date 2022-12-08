package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.ActorViewState
import agency.five.codebase.android.movieapp.ui.component.CrewmanViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl.toString(),
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = toCrewmanViewState(movieDetails.crew),
            cast = toActorViewState(movieDetails.cast)
        )
    }

    private fun toCrewmanViewState(crewmen: List<Crewman>) =
        crewmen.map {
            CrewmanViewState(
                name = it.name,
                job = it.job
            )
        }

    private fun toActorViewState(actors: List<Actor>) =
        actors.map {
            ActorViewState(
                name = it.name,
                character = it.character,
                imageUrl = it.imageUrl.toString()
            )
        }
}
