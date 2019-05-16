package com.code.classes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_settings.*

//Asetusruutu
class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        //Takaisin-nappi
        backButton1.setOnClickListener{_ ->
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
        }

        //Säännöt-nappi
        rulesButton.setOnClickListener { _ ->
            val rulesIntent = Intent(this, RulesScreen::class.java)
            startActivity(rulesIntent)
        }

        //Nimen vaihto-nappi
        changeName.setOnClickListener{_ ->
            val nameIntent = Intent(this, NameScreen::class.java)
            startActivity(nameIntent)
        }
    }
}
