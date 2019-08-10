package com.syousa1982.todoapp.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todoapp.databinding.FragmentTodoCompletedBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class TodoCompletedFragment : Fragment() {

    private lateinit var binding: FragmentTodoCompletedBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoCompletedBinding.inflate(inflater, container, false)
        binding.text1.text = "TodoCompletedFragment"
        return binding.root
    }


}
