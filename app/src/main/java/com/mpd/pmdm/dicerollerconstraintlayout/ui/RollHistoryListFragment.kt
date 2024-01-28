package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpd.pmdm.dicerollerconstraintlayout.DiceRollApplication
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRoll
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentRollHistoryListBinding

/**
 * A fragment representing a list of Items.
 */
class RollHistoryListFragment : Fragment() {

    private var _binding: FragmentRollHistoryListBinding? = null
    private val binding get() = _binding!!

    private val twoDicesViewModel: TwoDicesViewModel by activityViewModels {
        TwoDicesViewModelFactory(6, (activity?.application as DiceRollApplication).diceRollsRepository)
    }

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
        val adapter = DiceRollHistoryRecyclerAdapter()
        binding.recyclerRollsHistoryList.adapter = adapter

        twoDicesViewModel.getShoppingLists().observe(viewLifecycleOwner){
            adapter.updateList(it)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}