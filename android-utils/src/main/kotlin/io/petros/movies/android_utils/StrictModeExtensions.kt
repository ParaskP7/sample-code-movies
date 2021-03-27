@file:Suppress("unused")

package io.petros.movies.android_utils

import android.os.StrictMode
import io.petros.movies.android.utils.BuildConfig

/* DISK */

private const val PREFIX_J_UNIT = "org.junit."

fun permitDiskReads(block: () -> Any?): Any? {
    return when {
        isTest(PREFIX_J_UNIT) -> block()
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

fun isTest(prefix: String): Boolean {
    for (stackTrace in Thread.currentThread().stackTrace) {
        if (stackTrace.className.startsWith(prefix)) {
            return true
        }
    }
    return false
}
