package com.digikraft.myapplication.model

sealed class GenericApiResponse<T>(val data: T? = null, val errorMessage: String? = null) {

    class Loading<T> : GenericApiResponse<T>()

    class Success<T>(data: T?) : GenericApiResponse<T>(data = data)

    class Error<T>(errorMessage: String?) : GenericApiResponse<T>(errorMessage = errorMessage)
}