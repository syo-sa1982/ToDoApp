package com.syousa1982.todoapp.presentation.item

import com.bumptech.glide.Glide
import com.syousa1982.todoapp.R
import com.syousa1982.todoapp.databinding.ItemTaskBinding
import com.syousa1982.todoapp.domain.model.Task
import com.xwray.groupie.databinding.BindableItem

data class TaskItem(var task: Task? = null) : BindableItem<ItemTaskBinding>() {
    override fun getLayout(): Int = R.layout.item_task

    override fun bind(viewBinding: ItemTaskBinding, position: Int) {
        task?.let {
            viewBinding.taskName.text = it.name
            when (it.status) {
                Task.Status.ACTIVE -> Glide.with(viewBinding.image).load(R.drawable.ic_check_off_24dp).into(viewBinding.image)
                Task.Status.COMPLETE -> Glide.with(viewBinding.image).load(R.drawable.ic_check_on_24dp).into(viewBinding.image)
            }
        }
    }
}