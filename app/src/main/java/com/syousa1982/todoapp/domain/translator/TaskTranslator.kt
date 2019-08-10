package com.syousa1982.todoapp.domain.translator

import com.syousa1982.todoapp.data.db.entity.TaskEntity
import com.syousa1982.todoapp.domain.model.Task

object TaskTranslator {

    fun toModel(entity: TaskEntity): Task = Task(
        entity.id,
        entity.name,
        Task.Status.valueOf(entity.status)
    )

    fun toModels(entities: List<TaskEntity>): List<Task> = entities.map {
        toModel(it)
    }
}