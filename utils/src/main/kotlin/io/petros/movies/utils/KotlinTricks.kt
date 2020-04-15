package io.petros.movies.utils

/**
 * The superpower of sealed classes is only unleashed if when is used as an expression. Otherwise, the compiler doesn’t
 * force developers to handle all cases. However, with this little exhaustive trick, a when statement can be enforce to
 * become exhaustive.
 */
val <T> T.exhaustive: T
    get() = this
