package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.DiceRollApplication
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentBotonBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory


class BotonFragment : Fragment() {
    private var _binding: FragmentBotonBinding? = null
    private val binding get() = _binding!!

    //Instanciamos el sharedViewModel, que pertenecerá al ciclo de vida de la Actividad
    private val twoDicesModel: TwoDicesViewModel by activityViewModels<TwoDicesViewModel> {
        TwoDicesViewModelFactory(6, (activity?.application as DiceRollApplication).diceRollsRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBotonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Tiramos los dados al clickar en el botón
        binding.btnRoll.setOnClickListener {
            twoDicesModel.rollDices()
        }

        binding.btnClearHistory.setOnClickListener {
            clearRollsHistory()
        }
    }

    /**
     * Muestra un cuadro de diálogo que confirma si se quiere borrar el histórico de tiradas
     */
    private fun clearRollsHistory() {
        val dialog = ClearHistoryDialog()
        dialog.show(parentFragmentManager,null)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}