package io.petros.movies.data.reactive.rx

import io.petros.movies.domain.reactive.rx.RxSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxSchedulersProvider {

    fun rxSchedulers(): RxSchedulers = RxSchedulers(Schedulers.io(), AndroidSchedulers.mainThread())

}
