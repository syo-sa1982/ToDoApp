package com.syousa1982.todoapp.presentation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.syousa1982.todoapp.databinding.FragmentTodoActiveBinding
import com.syousa1982.todoapp.domain.Result
import com.syousa1982.todoapp.presentation.item.TaskItem
import com.syousa1982.todoapp.util.extention.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 *
 */
class TodoActiveFragment : Fragment() {

    private lateinit var binding: FragmentTodoActiveBinding

    private val viewModel by sharedViewModel<TodoActiveViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoActiveBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        bindOutput(binding, viewModel)
        bindRecyclerView(binding, viewModel)
        return binding.root
    }

    private fun bindOutput(binding: FragmentTodoActiveBinding, viewModel: TodoActiveViewModel) {

        viewModel.updateResult.observe(this) {
            val actionName = "タスク更新"
            when (it) {
                is Result.Progress -> {
                    Log.d(className(), "$actionName 開始")
                }
                is Result.Success -> {
                    Log.d(className(), "$actionName 完了")
                    viewModel.getTasks()
                    viewModel.updateResult.value = null
                }
                is Result.Failure -> {
                    Log.d(className(), "$actionName 失敗", it.e)
                    showErrorMessage(actionName)
                }
            }
        }
    }

    private fun bindRecyclerView(binding: FragmentTodoActiveBinding, viewModel: TodoActiveViewModel) {
        // input
        binding.tasks.setGroupieAdapter()
        binding.tasks.setLinearLayoutManagerWithDivider()
        binding.tasks.setGroupieOnItemClickListener<TaskItem> { item, view ->
            val task = item.task ?: return@setGroupieOnItemClickListener
            viewModel.updateTask(task)
        }
        binding.tasks.setGroupieOnItemLongClickListener<TaskItem> { item, view ->
            showDeleteDialog(item)
            return@setGroupieOnItemLongClickListener true
        }
        // output
        viewModel.tasks.observe(this) {
            val actionName = "タスク取得"
            when (it) {
                is Result.Progress -> {
                    Log.d(className(), "$actionName 開始")
                }
                is Result.Success -> {
                    Log.d(className(), "$actionName 成功 ${it.data}")
                    val items = it.data.map { task ->
                        TaskItem(task)
                    }
                    binding.tasks.getGroupieAdapter().update(items)
                    binding.count.text = "${it.data.count()} items left."
                }
                is Result.Failure -> {
                    Log.e(className(), "$actionName 失敗 ${it.e}")
                    showErrorMessage(actionName)
                }

            }
        }
    }

    private fun showDeleteDialog(item: TaskItem) {
        AlertDialog.Builder(requireContext())
            .setTitle("確認")
            .setMessage("タスクを削除しますか？")
            .setPositiveButton("削除する") { _, _ ->
                item.task?.let { task ->
                    viewModel.delete(task)
                }
            }
            .setNegativeButton("キャンセル", null)
            .show()
    }

    /**
     * エラーメッセージ表示
     */
    private fun showErrorMessage(actionName: String) {
        view?.let {
            Snackbar.make(it, "$actionName 失敗しました。", Snackbar.LENGTH_LONG).show()
        }
    }

}
