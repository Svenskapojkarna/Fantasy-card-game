package com.code.classes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import kotlinx.android.synthetic.main.content_select_screen.*

//Korttien valintaruutu
class SelectScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_select_screen)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        //Muutetaan aluksi kortteja osoittavat nuolet näkymättömiksi
        sotilasNuoli.visibility = View.GONE
        taikuriNuoli.visibility = View.GONE
        jousimiesNuoli.visibility = View.GONE

        //Takaisin-painike
        backButton3.setOnClickListener { _->
            User.cards.clear()
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
        }

        //Jos sotilaskortti valitaan, lisätään se pelaajan kokoelmaan ja asetetaan nuoli näkyväksi
        sotilas.setOnClickListener{_->
            if(sotilasNuoli.visibility == View.GONE){
                sotilasNuoli.visibility = View.VISIBLE
                val soldier = Card(2, 3, "sotilas")
                User.addCard(soldier)
            } else{
                sotilasNuoli.visibility = View.GONE
                User.removeCard("sotilas")
            }
        }

        //Jos taikurikortti valitaan, lisätään se pelaajalle ja asetetaan nuoli näkyväksi
        taikuri.setOnClickListener{_->
            if(taikuriNuoli.visibility == View.GONE){
                taikuriNuoli.visibility = View.VISIBLE
                val mage = Card(1, 4, "taikuri")
                User.addCard(mage)
            } else{
                taikuriNuoli.visibility = View.GONE
                User.removeCard("taikuri")
            }
        }

        //Jos jousimieskortti valitaan, lisätään se pelaajalle ja nuoli asetetaan näkyväksi
        jousimies.setOnClickListener{_->
            if(jousimiesNuoli.visibility == View.GONE){
                jousimiesNuoli.visibility = View.VISIBLE
                val ranger = Card(3, 2, "jousimies")
                User.addCard(ranger)
            } else{
                jousimiesNuoli.visibility = View.GONE
                User.removeCard("jousimies")
            }
        }

        //Nappi, joka aloittaa varsinaisen pelin
        aloitusNappi.setOnClickListener { _->
            if(User.cards.isEmpty()){

            } else{
                val gameIntent = Intent(this, Game::class.java)
                startActivity(gameIntent)
            }
        }
    }

}
