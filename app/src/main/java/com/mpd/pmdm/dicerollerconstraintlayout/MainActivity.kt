package com.mpd.pmdm.dicerollerconstraintlayout

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Instanciamos el ViewModel pasándole las caras totales de los dados que gestiona
    private val twoDices: TwoDicesViewModel by viewModels {
        TwoDicesViewModelFactory(6)
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Asignamos al botón comportamiento para el evento onClick
        binding.btnRoll.setOnClickListener {
            rollDices()
        }

        //Configuramos los observadores de los LiveData con las caras
        //Aunque sabemos que cuando cambie un dado también cambiará el otro, vamos a hacer
        //como que no lo sabemos.
        twoDices.dice1CurrentSide.observe(this) {
            refreshDiceImage(twoDices.dice1CurrentSide.value, binding.ivDice1)
        }

        twoDices.dice2CurrentSide.observe(this) {
            refreshDiceImage(twoDices.dice2CurrentSide.value, binding.ivDice2)
        }


    }

    private fun refreshDiceImage(currentSide: Int?, diceImageView: ImageView) {
        val diceImageResource = when (currentSide) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImageView.setImageResource(diceImageResource)
    }

    private fun rollDices() {
        twoDices.rollDices()
    }


}

