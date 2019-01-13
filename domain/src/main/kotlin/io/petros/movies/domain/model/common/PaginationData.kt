package io.petros.movies.domain.model.common

@Suppress("DataClassContainsFunctions", "UnnecessaryAbstractClass")
data class PaginationData<T>(
    private val allPageItems: ArrayList<T> = arrayListOf(),
    private var latestPage: InfiniteScrollPage<T>? = null,
    private var nextPage: Int? = null
) {

    /* GET */

    fun items(): List<T> = allPageItems

    fun latestItems(): List<T>? = latestPage?.items()

    fun nextPage() = nextPage

    /* ADD/CLEAR */

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

    /* IS */

    fun isEmpty() = allPageItems.isEmpty()

    fun isFirstPage() = allPageItems == latestPage?.items()

    abstract class InfiniteScrollPage<T> {

        abstract fun nextPage(): Int?

        abstract fun items(): List<T>

    }

}
