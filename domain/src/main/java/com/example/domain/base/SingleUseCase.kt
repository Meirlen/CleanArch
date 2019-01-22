package com.example.domain.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T> : UseCase() {


    abstract fun buildUseCaseSingle(): Single<T>

    fun execute(onSuccess: ((t: T) -> Unit), onError: ((t: Throwable) -> Unit)) {
        disposeLatest()
        val disposable = buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
        disposables.add(disposable)
        lastDisposable = disposable
    }
}