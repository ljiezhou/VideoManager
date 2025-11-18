package com.ds.common.net.state

import com.ds.common.net.network.AppException


sealed class ResultState<out T> {
    companion object{

        fun <T> onAppSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onAppLoading(loadingMessage: String): ResultState<T> = Loading(loadingMessage)
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)
    }

    data class Loading(val loadingMessage: String) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: AppException) : ResultState<Nothing>()
}