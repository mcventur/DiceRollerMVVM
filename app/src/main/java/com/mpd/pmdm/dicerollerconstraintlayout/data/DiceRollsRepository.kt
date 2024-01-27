package com.mpd.pmdm.dicerollerconstraintlayout.data

import androidx.lifecycle.LiveData
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRoll
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRollsDao

/**
 * Esta clase hará de capa de abstraccíón entre el ViewModel y el DAO
 * Simplemente necesitamos tener acceso aquí a todas las funciones de nuestros DAO
 */
class DiceRollsRepository(private val diceRollsDao: DiceRollsDao) {
    val allDiceRolls: LiveData<List<DiceRoll>> = diceRollsDao.getAllRolls()

    suspend fun insert(diceRoll: DiceRoll){
        diceRollsDao.insert(diceRoll)
    }
}