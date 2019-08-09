package com.syousa1982.todoapp.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todoapp.constant.TodoCollectionKind
import com.syousa1982.todoapp.databinding.FragmentTodoBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(BUNDLE_POSITION) }?.apply {
            val tabKind = TodoCollectionKind.from(getInt(BUNDLE_POSITION))
            binding.text1.text = tabKind.getTitle()
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
