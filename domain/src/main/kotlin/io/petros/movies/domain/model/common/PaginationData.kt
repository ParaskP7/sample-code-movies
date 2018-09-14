package io.petros.movies.domain.model.common

data class PaginationData<T>(
    val allPageItems: ArrayList<T> = arrayListOf(),
    var latestPage: InfiniteScrollPage<T>? = null,
    var nextPage: Int? = null
) {

    fun isEmpty() = allPageItems.isEmpty()

    fun isFirstPage() = allPageItems == latestPage?.items()

    fun addPage(page: InfiniteScrollPage<T>): PaginationData<T> {
        allPageItems.addAll(page.items())
        latestPage = page
        nextPage = page.nextPage()
        return this
    }

    fun clear() {
        allPageItems.clear()
        latestPage = null
        nextPage = null
    }

    abstract class InfiniteScrollPage<T> {

        abstract fun nextPage(): Int?

        abstract fun items(): List<T>

    }

}
