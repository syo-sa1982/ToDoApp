package com.syousa1982.todoapp.domain

/**
 * Rxの成功と失敗のモデルを格納
 */
sealed class Result<T> {

    /**
     * プログレス
     *
     * @param value Boolean
     */
    data class Progress<T>(private val value: Boolean = true) : Result<T>()

    /**
     * 成功
     *
     * @param data データ
     */
    data class Success<T>(val data: T) : Result<T>()

    /**
     * 失敗
     *
     * @param e エラー
     */
    data class Failure<T>(val e: Throwable) : Result<T>()

    companion object {

        /**
         * プログレス
         */
        fun <T> progress(): Result<T> = Progress()

        /**
         * 成功
         *
         * @param data データ
         * @return Result
         */
        fun <T> success(data: T): Result<T> = Success(data)

        /**
         * 失敗
         *
         * @param e エラー
         * @return Result
         */
        fun <T> failure(e: Throwable): Result<T> = Failure(e)
    }
}