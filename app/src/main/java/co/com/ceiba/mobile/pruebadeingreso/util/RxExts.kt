package co.com.ceiba.mobile.pruebadeingreso.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun <T> Observable<T>.applySchedulers(): Observable<T> = compose {
    it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.applySchedulers(): Flowable<T> = compose{
    it.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
}

class LifeDisposable(owner: LifecycleOwner) : LifecycleObserver {

    private val dis: CompositeDisposable = CompositeDisposable()

    init {
        owner.lifecycle.addObserver(this)
    }

    infix fun add(disposable: Disposable) {
        dis.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clear() {
        dis.clear()
    }
}