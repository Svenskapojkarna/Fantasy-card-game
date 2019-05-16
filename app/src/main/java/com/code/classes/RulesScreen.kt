package com.code.classes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View

import kotlinx.android.synthetic.main.content_rules_screen.*
import android.text.method.ScrollingMovementMethod


//Sääntöruutu
class RulesScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_rules_screen)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        //Takaisin-painike
        backButton2.setOnClickListener { _ ->
            val backIntent = Intent(this, Settings::class.java)
            startActivity(backIntent)
        }

        //Asetetaan tekstilaatikko "scrollattavaksi"
        saantoTeksti.movementMethod = ScrollingMovementMethod()
    }

}
