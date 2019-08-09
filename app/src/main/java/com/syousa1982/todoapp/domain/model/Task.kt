package com.syousa1982.todoapp.domain.model

/**
 * Taskモデル
 *
 * @param id
 * @param name
 * @param status
 */
data class Task(
    val id: Int,
    val name: String,
    val status: Status
) {
    enum class Status(val value: String) {
        ACTIVE("active"),
        COMPLETE("complete")
    }
}