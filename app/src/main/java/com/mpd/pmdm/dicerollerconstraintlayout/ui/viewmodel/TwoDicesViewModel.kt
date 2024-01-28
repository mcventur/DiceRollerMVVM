package com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mpd.pmdm.dicerollerconstraintlayout.data.DiceRollsRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRoll
import com.mpd.pmdm.dicerollerconstraintlayout.model.Dice
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TwoDicesViewModel(numSides: Int, private val diceRollsRepository: DiceRollsRepository): ViewModel() {

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
        //Podríamos exponer una función para que los inserts se hagan desde las actividades o fragmentos
        //pero en este caso opto por hacerlo al momento de hacer las tiradas, internamente
        saveRollInDatabase()
    }

    private fun saveRollInDatabase() {
        viewModelScope.launch {
            val cara1 = dice1CurrentSide.value ?: 0
            val cara2 = dice2CurrentSide.value ?: 0
            val diceRoll = DiceRoll(
                sideDice1 = cara1,
                siceDice2 = cara2,
                dateTime = System.currentTimeMillis()
            )
            diceRollsRepository.insert(diceRoll)
        }
    }

    //Exponemos una función para recuperar el histórico de tiradas
    fun getAllDiceRolls(): LiveData<List<DiceRoll>> = diceRollsRepository.allDiceRolls

    fun clearHistory() = viewModelScope.launch { diceRollsRepository.clear() }

}

class TwoDicesViewModelFactory(
    private val numSides: Int,
    private val diceRollsRepository: DiceRollsRepository):
    ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TwoDicesViewModel::class.java))
            return TwoDicesViewModel(numSides, diceRollsRepository) as T
        throw IllegalArgumentException("Factory cannot instantiate ViewModel from class ${modelClass.name}")
    }
}