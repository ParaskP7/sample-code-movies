package io.petros.movies.domain.model.common

data class PaginationData<T>(
    val allPageItems: List<T> = listOf(),
    val latestPage: InfiniteScrollPage<T>? = null,
    val nextPage: Int? = null,
) {

    interface InfiniteScrollPage<T> {

        val nextPage: Int?

        val items: List<T>

    }

}
