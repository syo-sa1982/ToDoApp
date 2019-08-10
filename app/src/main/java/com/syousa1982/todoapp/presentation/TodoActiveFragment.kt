package com.syousa1982.todoapp.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syousa1982.todoapp.databinding.FragmentTodoActiveBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class TodoActiveFragment : Fragment() {

    private lateinit var binding: FragmentTodoActiveBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoActiveBinding.inflate(inflater, container, false)
        binding.text1.text = "TodoActiveFragment"
        return binding.root
    }


}
