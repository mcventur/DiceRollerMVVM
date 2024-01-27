package com.mpd.pmdm.dicerollerconstraintlayout.data

/**
 * Clase que modela un dado con un número de caras configurable
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}