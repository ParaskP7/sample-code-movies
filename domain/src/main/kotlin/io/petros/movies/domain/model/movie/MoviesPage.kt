package io.petros.movies.domain.model.movie

import io.petros.movies.domain.model.common.PaginationData

data class MoviesPage(
    override val nextPage: Int?,
    override val items: List<Movie>
) : PaginationData.InfiniteScrollPage<Movie>
