package io.petros.movies.presentation

import android.os.StrictMode
import io.petros.movies.BuildConfig

/* DISK */

private const val J_UNIT_PREFIX = "org.junit."

fun permitDiskReads(block: () -> Any?): Any? {
    return when {
        isJUnitTest() -> block()
        BuildConfig.DEBUG -> {
            val currentThreadPolicy = StrictMode.getThreadPolicy()
            val tmpThreadPolicy = StrictMode.ThreadPolicy.Builder(currentThreadPolicy)
                .permitDiskReads().build()
            StrictMode.setThreadPolicy(tmpThreadPolicy)
            val value = block()
            StrictMode.setThreadPolicy(currentThreadPolicy)
            value
        }
        else -> block()
    }
}

fun isJUnitTest(): Boolean {
    for (stackTrace in Thread.currentThread().stackTrace) {
        if (stackTrace.className.startsWith(J_UNIT_PREFIX)) {
            return true
        }
    }
    return false
}
