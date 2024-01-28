package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.DiceRollApplication
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory

/*
Código basado en https://developer.android.com/guide/topics/ui/dialogs?hl=es-419#DialogFragment
 */

class ClearHistoryDialog: DialogFragment() {
    //Instanciamos el sharedViewModel, que pertenecerá al ciclo de vida de la Actividad
    private val twoDicesModel: TwoDicesViewModel by activityViewModels<TwoDicesViewModel> {
        TwoDicesViewModelFactory(6, (activity?.application as DiceRollApplication).diceRollsRepository)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.clearHistoryConfirmMessage)
                .setTitle(getString(R.string.clearHistoryConfirmTitle))
                .setPositiveButton(R.string.confirm,
                    //Como no usamos ninguno de los dos parámetros, los sustituimos con _
                    DialogInterface.OnClickListener { _,_ ->
                        twoDicesModel.clearHistory()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}