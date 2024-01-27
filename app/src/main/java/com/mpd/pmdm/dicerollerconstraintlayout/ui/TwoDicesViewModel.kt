package com.mpd.pmdm.dicerollerconstraintlayout.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpd.pmdm.dicerollerconstraintlayout.data.Dice
import java.lang.IllegalArgumentException

class TwoDicesViewModel(numSides: Int): ViewModel() {

    //Declaramos las dos instancias de Dice
    private val dice1 = Dice(numSides)
    private val dice2 = Dice(numSides)

    //Lo que es mutable y observable en nuestra aplicación son las caras actuales de cada dado
    private val _dice1CurrentSide = MutableLiveData<Int>(dice1.roll())
    val dice1CurrentSide: LiveData<Int> = _dice1CurrentSide

    private val _dice2CurrentSide = MutableLiveData<Int>(dice2.roll())
    val dice2CurrentSide: LiveData<Int> = _dice2CurrentSide

    /**
     * Tira los dados y actualiza los LiveData con la cara actual
     * Esta función será la que se llame desde la UI
     */
    fun rollDices(){
        _dice1CurrentSide.value = dice1.roll()
        _dice2CurrentSide.value = dice2.roll()
    }
}

class TwoDicesViewModelFactory(private val numSides: Int): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TwoDicesViewModel::class.java))
            return TwoDicesViewModel(numSides) as T
        throw IllegalArgumentException("Factory cannot instantiate ViewModel from class ${modelClass.name}")
    }
}