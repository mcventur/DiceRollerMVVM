package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DiceRoll::class], version = 1)
abstract class RollsDatabase: RoomDatabase() {
    abstract fun diceRollsDao(): DiceRollsDao

    //Para asegurar que sólo se genere una única instancia
    companion object{
        //Marcamos como volatile para que cualquier hilo vea el valor actual del dato
        @Volatile
        private var INSTANCE: RollsDatabase? = null

        //Nótese el uso del operador Elvis
        fun getDataBase(context: Context): RollsDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    RollsDatabase::class.java,
                    "rolls_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}