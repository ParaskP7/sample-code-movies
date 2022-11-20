@file:Suppress("MagicNumber")

package io.petros.movies.moviedetailscompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.petros.movies.corecompose.ui.Theme
import io.petros.movies.domain.model.movie.Movie
import java.util.*

@Preview(
    name = "Light",
    group = "Movie Details",
)
@Composable
fun MovieDetailsLightPreview() {
    Theme(darkTheme = false) {
        MovieDetailsScreen(
            state = MovieDetailsState(
                status = MovieDetailsStatus.Loaded,
                movie = previewMovie().copy(backdrop = null),
            ),
            sideEffect = MovieDetailsSideEffect.Init,
            onRetry = { },
            darkTheme = false,
        )
    }
}

@Preview(
    name = "Dark",
    group = "Movie Details",
)
@Composable
fun MovieDetailsDarkPreview() {
    Theme(darkTheme = true) {
        MovieDetailsScreen(
            state = MovieDetailsState(
                status = MovieDetailsStatus.Loaded,
                movie = previewMovie().copy(backdrop = null),
            ),
            sideEffect = MovieDetailsSideEffect.Init,
            onRetry = { },
            darkTheme = true,
        )
    }
}

fun previewMovie() = Movie(
    id = 419_704,
    title = "Ad Astra",
    releaseDate = GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time,
    voteAverage = 6.0,
    voteCount = 2958,
    overview = "The near future, a time when both hope and hardships drive humanity to look to the stars " +
            "and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut " +
            "Roy McBride undertakes a mission across the immensity of space and its many perils to " +
            "uncover the truth about a lost expedition that decades before boldly faced emptiness and " +
            "silence in search of the unknown.",
    backdrop = "http://image.tmdb.org/t/p/w500/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
)
