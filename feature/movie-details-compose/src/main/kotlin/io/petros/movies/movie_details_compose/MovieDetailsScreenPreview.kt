@file:Suppress("MagicNumber")

package io.petros.movies.movie_details_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.petros.movies.core_compose.ui.Theme
import io.petros.movies.test.domain.movie

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
                movie = movie().copy(backdrop = null),
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
                movie = movie().copy(backdrop = null),
            ),
            sideEffect = MovieDetailsSideEffect.Init,
            onRetry = { },
            darkTheme = true,
        )
    }
}
