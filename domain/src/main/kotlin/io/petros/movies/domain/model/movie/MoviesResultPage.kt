package io.petros.movies.domain.model.movie

import io.petros.movies.domain.model.common.PaginationData

data class MoviesResultPage(
    val nextPage: Int?,
    val movies: List<Movie>
) : PaginationData.InfiniteScrollPage<Movie>() {

    override fun nextPage() = nextPage

    override fun items() = movies

}
