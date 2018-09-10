package io.petros.movies.domain.reactive.rx

import io.reactivex.Scheduler

/**
 * A [Scheduler] intended for IO-bound work. The implementation is backed by an [java.util.concurrent.Executor]
 * thread-pool that will grow as needed. This can be used for asynchronously performing blocking IO.
 *
 * A [Scheduler] which executes actions on the Android UI thread.
 */
class RxSchedulers(val io: Scheduler, val androidMainThread: Scheduler)
