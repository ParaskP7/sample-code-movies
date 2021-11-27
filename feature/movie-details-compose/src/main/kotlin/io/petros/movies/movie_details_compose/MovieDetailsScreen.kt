@file:Suppress("MagicNumber")

package io.petros.movies.movie_details_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import io.petros.movies.core_compose.ui.DarkColor
import io.petros.movies.core_compose.ui.LightColor
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.feature.movie.details.compose.R

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsState,
    sideEffect: MovieDetailsSideEffect,
    onRetry: () -> Unit,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    Surface(color = if (darkTheme) DarkColor.containerBackground else LightColor.containerBackground) {
        ConstraintLayout {
            val (main, snackbar) = createRefs()
            MovieDetailsScreenMain(state, main)
            MovieDetailsScreenSnackbar(sideEffect, onRetry, snackbar)
        }
    }
}

@Composable
private fun ConstraintLayoutScope.MovieDetailsScreenMain(
    state: MovieDetailsState,
    main: ConstrainedLayoutReference,
) {
    Column(
        modifier = Modifier.constrainAs(main) { top.linkTo(parent.top) }
    ) {
        MovieImage(state)
        MovieDetails(state.movie)
    }
}

@Composable
@Suppress("unused", "UNUSED_PARAMETER", "ControlFlowWithEmptyBody", "ForbiddenComment")
private fun ConstraintLayoutScope.MovieDetailsScreenSnackbar(
    sideEffect: MovieDetailsSideEffect,
    onRetry: () -> Unit,
    snackbar: ConstrainedLayoutReference,
) {
    if (sideEffect == MovieDetailsSideEffect.Error) {
        // TODO: Implement snackbar.
    }
}

@Composable
private fun MovieImage(
    state: MovieDetailsState,
) {
    ConstraintLayout {
        val (main, progressBar) = createRefs()
        MovieImageMain(state.movie.backdrop, main)
        MovieImageError(state.status, progressBar)
    }
}

@Composable
private fun ConstraintLayoutScope.MovieImageMain(
    backdrop: String?,
    main: ConstrainedLayoutReference,
) {
    BackdropImage(
        modifier = Modifier
            .constrainAs(main) { top.linkTo(parent.top) }
            .height(250.dp)
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(4.dp)
            ),
        backdrop = backdrop,
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun BackdropImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    backdrop: String? = null,
) {
    backdrop?.let {
        AsyncImage(
            model = it,
            modifier = modifier,
            contentScale = contentScale,
            contentDescription = stringResource(R.string.ivBackdropContentDescription),
        )
    } ?: run {
        Image(
            painter = ColorPainter(Color.Gray),
            modifier = modifier,
            contentScale = contentScale,
            contentDescription = stringResource(R.string.ivBackdropContentDescription),
        )
    }
}

@Composable
private fun ConstraintLayoutScope.MovieImageError(
    status: MovieDetailsStatus,
    progressBar: ConstrainedLayoutReference,
) {
    if (status == MovieDetailsStatus.Loading) {
        Row(
            modifier = Modifier
                .constrainAs(progressBar) { top.linkTo(parent.top) }
                .height(250.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.secondary,
            )
        }
    }
}

@Composable
private fun MovieDetails(
    movie: Movie,
) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = movie.title,
        style = MaterialTheme.typography.h5.copy(
            color = MaterialTheme.colors.onPrimary
        ),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
    Text(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
        ),
        text = movie.releaseDate(),
        style = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.onPrimary
        ),
    )
    Text(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
        ),
        text = movie.vote(),
        style = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.onPrimary
        ),
    )
    Text(
        modifier = Modifier.padding(16.dp),
        text = movie.overview,
        style = MaterialTheme.typography.subtitle1.copy(
            color = MaterialTheme.colors.onPrimary
        ),
    )
}
