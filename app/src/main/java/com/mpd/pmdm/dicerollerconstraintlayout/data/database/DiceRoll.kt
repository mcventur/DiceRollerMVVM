package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Esta tabla almacenará información histórica de las tiradas
 */
@Entity(tableName = "dice_rolls")
data class DiceRoll(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sideDice1: Int,
    val siceDice2: Int,
    val dateTime: Long
)