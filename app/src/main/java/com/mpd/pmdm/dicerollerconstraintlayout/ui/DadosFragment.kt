package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.DiceRollApplication
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDadosBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory

class DadosFragment : Fragment() {
    private var _binding: FragmentDadosBinding? = null
    private val binding get() = _binding!!

    private val twoDicesViewModel: TwoDicesViewModel by activityViewModels<TwoDicesViewModel> {
        TwoDicesViewModelFactory(6, (activity?.application as DiceRollApplication).diceRollsRepository )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDadosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Asignamos observadores a cada LiveData
        twoDicesViewModel.apply {
            dice1CurrentSide.observe(viewLifecycleOwner){
                refreshDiceImage(it, binding.ivDice1)
            }
            dice2CurrentSide.observe(viewLifecycleOwner){
                refreshDiceImage(it, binding.ivDice2)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun refreshDiceImage(currentSide: Int?, diceImageView: ImageView) {
        val diceImageResource = when (currentSide) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImageView.setImageResource(diceImageResource)
    }

}