package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiceRollsDao {
    @Query("SELECT * FROM dice_rolls ORDER BY dateTime DESC")
    fun getAllRolls(): LiveData<List<DiceRoll>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(roll: DiceRoll)

    @Query("DELETE FROM dice_rolls")
    suspend fun clear()
}