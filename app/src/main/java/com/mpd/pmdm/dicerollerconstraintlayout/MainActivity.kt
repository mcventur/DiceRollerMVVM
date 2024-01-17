package com.mpd.pmdm.dicerollerconstraintlayout

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Código añadido
        //Creo aquí los dos dados, y se los paso a la función
        val dice1 = Dice(6)
        val dice2 = Dice(6)

        //Apunto desde aquí a los dos ImageView
        val dice1Image:ImageView = findViewById(R.id.ivDice1)
        val dice2Image:ImageView = findViewById(R.id.ivDice2)

        //Le paso a la función el objeto dado, y la imagen de la UI correspondiente a actualizar
        //cuando carga la aplicación
        rollDice(dice1, dice1Image)
        rollDice(dice2, dice2Image)

        //asignamos el mismo comportamiento en el onClick
        val rollButton: Button = findViewById(R.id.btnRoll);
        rollButton.setOnClickListener {
            rollDice(dice1, dice1Image);
            rollDice(dice2, dice2Image);
        }
    }


    /**
     * Función que crea un dado, lo tira, y muestra su valor en la IU
     */
    private fun rollDice(dice: Dice, imageViewDice: ImageView) {
        val diceValue = dice.roll()


        val imgDiceResource = when(diceValue){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            //Esto no se debería dar, pero me obliga al usar when como expresión
            else -> R.drawable.dice_6
        }

        imageViewDice.setImageResource(imgDiceResource)
        //Le damos una descripción a la imagen para aportar accesibilidad
        imageViewDice.contentDescription = diceValue.toString()
    }
}

