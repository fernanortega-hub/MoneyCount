package com.fernanortega.mymoneycount.domain.util

sealed class ValidatorResult<out T> {
    data object Valid: ValidatorResult<Nothing>()
    data class Invalid<T>(val invalidResult: T): ValidatorResult<T>()
}