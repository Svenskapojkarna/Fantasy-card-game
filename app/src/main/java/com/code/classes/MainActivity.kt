package com.code.classes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

//Käynnistysruutu
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        //Pelin aloitusnappi
        startGame.setOnClickListener {_->
            val startIntent = Intent(this, SelectScreen::class.java)
            startActivity(startIntent)
        }

        //Asetusten nappi
        settings.setOnClickListener { _ ->
            val settingsIntent = Intent(this, com.code.classes.Settings::class.java)
            startActivity(settingsIntent)
        }

        //Pelin lopetusnappi
        quitGame.setOnClickListener { _ ->
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Lopeta Peli")
            builder.setMessage("Oletko varma, että haluat lopettaa pelin?")
            builder.setPositiveButton("Kyllä"){_, _ ->
                finishAffinity()
            }
            builder.setNegativeButton("Peruuta"){_, _ ->

            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}