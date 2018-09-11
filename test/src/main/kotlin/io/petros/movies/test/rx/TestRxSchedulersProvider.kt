package io.petros.movies.test.rx

import io.petros.movies.domain.reactive.rx.RxSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestRxSchedulersProvider {

    companion object {

        fun provideRxSchedulers(
            io: Scheduler = Schedulers.trampoline(),
            androidMainThread: Scheduler = Schedulers.trampoline()
        ): RxSchedulers {
            return RxSchedulers(io, androidMainThread)
        }

    }

}
