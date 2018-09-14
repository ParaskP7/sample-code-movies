package io.petros.movies.data.network.rest.response.movie

data class MoviesResultPageResponse(
    val page: Int,
    val total_pages: Int,
    val results: List<MovieResponse>
) {

    fun nextPage(): Int? {
        return if (page < total_pages) page + 1 else null
    }

}
