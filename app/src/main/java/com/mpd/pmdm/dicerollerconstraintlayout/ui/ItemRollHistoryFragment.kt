package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentRollHistoryListBinding

/**
 * A fragment representing a list of Items.
 */
class ItemRollHistoryFragment : Fragment() {

    private var _binding: FragmentRollHistoryListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRollHistoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerRollsHistoryList.layoutManager = LinearLayoutManager(context)
        // Set the adapter
        /*        if (view is RecyclerView) {
                    with(view) {
                        layoutManager = when {
                            columnCount <= 1 -> LinearLayoutManager(context)
                            else -> GridLayoutManager(context, columnCount)
                        }
                        adapter = MyItemRollHistoryRecyclerViewAdapter(PlaceholderContent.ITEMS)
                    }
                }
                return view*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}