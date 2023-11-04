package com.example.pizzamenu.app.viewmodel

private typealias OnError = (Throwable) -> Unit
private typealias OnLoading = () -> Unit
private typealias OnSuccess<T> = (T) -> Unit

sealed class ScreenState<T> {
    fun on(
        error: OnError,
        loading: OnLoading,
        success: OnSuccess<T>
    ) {
        when (this) {
            is ErrorScreenState -> onError(error)
            is LoadingScreenState -> loading()
            is SuccessScreenState -> success(value)
        }
    }

    class ErrorScreenState<T>(val throwable: Throwable) : ScreenState<T>() {
        private var isShowedError = false

        fun onError(doOnError: (Throwable) -> Unit) {
            if (!isShowedError) {
                doOnError(throwable)
                isShowedError = true
            }
        }
    }
    class LoadingScreenState<T> : ScreenState<T>()
    class SuccessScreenState<T>(val value: T) : ScreenState<T>()
}