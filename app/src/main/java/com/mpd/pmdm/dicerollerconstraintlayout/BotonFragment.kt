package com.mpd.pmdm.dicerollerconstraintlayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentBotonBinding

class BotonFragment : Fragment() {
    private var _binding: FragmentBotonBinding? = null
    private val binding get() = _binding!!

    //Instanciamos el sharedViewModel, que pertenecerá al ciclo de vida de la Actividad
    private val twoDicesModel: TwoDicesViewModel by activityViewModels<TwoDicesViewModel> {
        TwoDicesViewModelFactory(6)
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}