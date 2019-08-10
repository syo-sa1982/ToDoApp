package com.syousa1982.todoapp.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.syousa1982.todoapp.constant.TodoCollectionKind
import com.syousa1982.todoapp.databinding.FragmentTodoCollectionBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.IllegalStateException


/**
 * A simple [Fragment] subclass.
 *
 */
class TodoCollectionFragment : Fragment() {

    private lateinit var collectionPagerAdapter: CollectionPagerAdapter
    private lateinit var binding: FragmentTodoCollectionBinding

    private val viewModel by sharedViewModel<TodoCollectionViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoCollectionBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)

        collectionPagerAdapter = CollectionPagerAdapter(childFragmentManager)
        binding.pager.adapter = collectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.pager)
        return binding.root
    }
}

class CollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TodoCollectionKind.ALL.value -> TodoFragment()
            TodoCollectionKind.ACTIVE.value -> TodoActiveFragment()
            TodoCollectionKind.COMPLETED.value -> TodoCompletedFragment()
            else -> throw IllegalStateException("存在しない画面です")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val tabKind = TodoCollectionKind.from(position)
        return tabKind.getTitle()
    }
}
