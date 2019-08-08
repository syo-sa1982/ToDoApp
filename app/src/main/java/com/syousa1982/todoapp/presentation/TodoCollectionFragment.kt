package com.syousa1982.todoapp.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.syousa1982.todoapp.databinding.FragmentTodoCollectionBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class TodoCollectionFragment : Fragment() {

    private lateinit var collectionPagerAdapter: CollectionPagerAdapter
    private lateinit var binding: FragmentTodoCollectionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        collectionPagerAdapter = CollectionPagerAdapter(childFragmentManager)
        binding.pager.adapter = collectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.pager)
    }

}
const val ARG_OBJECT = "object"

class CollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        val fragment = TodoFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "OBJECT ${position + 1}"
    }
}
