package com.syousa1982.todoapp.presentation


import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.syousa1982.todoapp.constant.TodoCollectionKind
import com.syousa1982.todoapp.databinding.FragmentTodoCollectionBinding
import com.syousa1982.todoapp.domain.Result
import com.syousa1982.todoapp.util.extention.className
import com.syousa1982.todoapp.util.extention.observe
import com.syousa1982.todoapp.util.extention.setOnChangedTextListener
import com.syousa1982.todoapp.util.extention.setOnClickPauseListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


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
        binding.pager.offscreenPageLimit = 3
        binding.pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                Log.d(className(), "onPageSelected position is $position")
                collectionPagerAdapter.refresh()
            }
        })
        binding.tabLayout.setupWithViewPager(binding.pager)
        bindInput(binding, viewModel)
        return binding.root
    }

    private fun bindInput(binding: FragmentTodoCollectionBinding, viewModel: TodoCollectionViewModel) {
        binding.inputText.setOnChangedTextListener {
            viewModel.taskName.value = it
        }
        binding.addButton.setOnClickPauseListener {
            viewModel.create()
        }
        viewModel.createResult.observe(this) {
            when (it) {
                is Result.Progress -> Log.d(className(), "書き込み中")
                is Result.Success -> {
                    binding.inputText.setText("")
                    viewModel.taskName.value = null
                    hideKeyboard()
                    collectionPagerAdapter.refresh()
                    Log.d(className(), "書き込み成功")
                }
                is Result.Failure -> Log.e(className(), "書き込み失敗 ${it.e}")

            }
        }
    }

    private fun hideKeyboard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val manager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

class CollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val fragments = mutableListOf<Fragment>()
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        Log.d(className(), "position is $position")
        val fragment = when (position) {
            TodoCollectionKind.ALL.value -> TodoFragment()
            TodoCollectionKind.ACTIVE.value -> TodoActiveFragment()
            TodoCollectionKind.COMPLETED.value -> TodoCompletedFragment()
            else -> throw IllegalStateException("存在しない画面です")
        }
        fragments.add(fragment)
        return fragment
    }

    override fun getItemPosition(`object`: Any): Int {
        val target = `object` as Fragment
        return when {
            fragments.contains(target) -> PagerAdapter.POSITION_UNCHANGED
            else -> PagerAdapter.POSITION_NONE
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val tabKind = TodoCollectionKind.from(position)
        return tabKind.getTitle()
    }

    fun refresh() {
        fragments.clear()
        notifyDataSetChanged()
    }
}
