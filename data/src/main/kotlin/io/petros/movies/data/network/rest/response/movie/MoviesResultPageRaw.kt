package io.petros.movies.data.network.rest.response.movie

@Suppress("ConstructorParameterNaming", "DataClassContainsFunctions")
data class MoviesResultPageRaw(
    val page: Int,
    val total_pages: Int,
    val results: List<MovieRaw>
) {

    fun nextPage() = if (page < total_pages) page + 1 else null

}
