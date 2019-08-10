package com.syousa1982.todoapp.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todoapp.databinding.FragmentTodoBinding
import com.syousa1982.todoapp.util.extention.observe
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 *
 */
class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding

    private val viewModel by sharedViewModel<TodoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        binding.text1.text = "TodoFragment"
        bindOutput(binding, viewModel)
        return binding.root
    }

    private fun bindOutput(binding: FragmentTodoBinding, viewModel: TodoViewModel) {
        viewModel.tasks.observe(this){

        }
    }

    companion object {

        /**
         * Emailを保持するBundleID
         */
        private const val BUNDLE_POSITION = "com.syousa1982.todoapp.presentation.TodoFragment.BUNDLE_POSITION"

        fun newInstance(position: Int) = TodoFragment().apply {
            arguments = Bundle().apply {
                putInt(BUNDLE_POSITION, position)
            }
        }
    }
}
