package com.mpd.pmdm.dicerollerconstraintlayout

import android.app.Application
import com.mpd.pmdm.dicerollerconstraintlayout.data.DiceRollsRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.RollsDatabase

//Estsa clase servirá para exponer al resto de la aplicación la instancia del repositorio
class DiceRollApplication: Application() {
    //La base de datos puede ser privada, porque sólo la usamos para instanciar el repositorio
    private val rollsDatabase by lazy { RollsDatabase.getDataBase(this) }
    val diceRollsRepository by lazy { DiceRollsRepository(rollsDatabase.diceRollsDao()) }
}