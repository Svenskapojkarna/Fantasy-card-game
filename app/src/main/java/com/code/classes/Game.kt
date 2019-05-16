package com.code.classes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.game.*

//Pääpelin toiminta
class Game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        //Asetetaan aluksi kaikki piilokuvakkeet
        nuoli1.visibility = View.GONE
        nuoli2.visibility = View.GONE
        nuoli3.visibility = View.GONE
        sotilas.visibility = View.GONE
        taikuri.visibility = View.GONE
        jousimies.visibility = View.GONE
        kohde.visibility = View.GONE
        vihuSotilasNappi.isClickable = false
        vihuJousimiesNappi.isClickable = false
        vihuTaikuriNappi.isClickable = false

        //Laitetaan pelaajan valitsemat kortit näkyville

        if(User.cards.containsKey("sotilas")){
            sotilas.visibility = View.VISIBLE
        }

        if(User.cards.containsKey("taikuri")){
            taikuri.visibility = View.VISIBLE
        }

        if(User.cards.containsKey("jousimies")){
            jousimies.visibility = View.VISIBLE
        }

        //Luodaan vihollisia yhtä paljon, kuin pelaajalla on kortteja

        if(User.cards.size == 2){
            vihuSotilas.visibility = View.GONE
        }
        if(User.cards.size == 1){
            vihuSotilas.visibility = View.GONE
            vihuJousimies.visibility = View.GONE
        }

        //Sotilaan painamisesta aiheutuvat toimenpiteet
        sotilasNappi.setOnClickListener { _->
            if(sotilas.visibility == View.VISIBLE) {
                if (nuoli1.visibility == View.VISIBLE) {
                    nuoli1.visibility = View.GONE
                    kohde.visibility = View.GONE
                    hyokkaysKortti.visibility = View.VISIBLE
                    taikuriNappi.isClickable = true
                    jousimiesNappi.isClickable = true
                    vihuSotilasNappi.isClickable = false
                    vihuJousimiesNappi.isClickable = false
                    vihuTaikuriNappi.isClickable = false
                } else {
                    hyokkaysKortti.visibility = View.GONE
                    kohde.visibility = View.VISIBLE
                    nuoli1.visibility = View.VISIBLE
                    taikuriNappi.isClickable = false
                    jousimiesNappi.isClickable = false
                    vihuSotilasNappi.isClickable = true
                    vihuJousimiesNappi.isClickable = true
                    vihuTaikuriNappi.isClickable = true
                }
            }
        }

        //Taikurin painamisesta aiheutuvat toimenpiteet
        taikuriNappi.setOnClickListener { _->
            if(taikuri.visibility == View.VISIBLE) {
                if (nuoli2.visibility == View.VISIBLE) {
                    nuoli2.visibility = View.GONE
                    kohde.visibility = View.GONE
                    hyokkaysKortti.visibility = View.VISIBLE
                    sotilasNappi.isClickable = true
                    jousimiesNappi.isClickable = true
                    vihuSotilasNappi.isClickable = false
                    vihuJousimiesNappi.isClickable = false
                    vihuTaikuriNappi.isClickable = false
                } else {
                    nuoli2.visibility = View.VISIBLE
                    hyokkaysKortti.visibility = View.GONE
                    kohde.visibility = View.VISIBLE
                    sotilasNappi.isClickable = false
                    jousimiesNappi.isClickable = false
                    vihuSotilasNappi.isClickable = true
                    vihuJousimiesNappi.isClickable = true
                    vihuTaikuriNappi.isClickable = true
                }
            }
        }

        //Jousimiehen painamisesta aiheutuvat toimenpiteet
        jousimiesNappi.setOnClickListener { _->
            if(jousimies.visibility == View.VISIBLE) {
                if (nuoli3.visibility == View.VISIBLE) {
                    nuoli3.visibility = View.GONE
                    kohde.visibility = View.VISIBLE
                    hyokkaysKortti.visibility = View.GONE
                    sotilasNappi.isClickable = true
                    taikuriNappi.isClickable = true
                    vihuSotilasNappi.isClickable = false
                    vihuJousimiesNappi.isClickable = false
                    vihuTaikuriNappi.isClickable = false
                } else {
                    nuoli3.visibility = View.VISIBLE
                    hyokkaysKortti.visibility = View.GONE
                    kohde.visibility = View.VISIBLE
                    sotilasNappi.isClickable = false
                    taikuriNappi.isClickable = false
                    vihuSotilasNappi.isClickable = true
                    vihuJousimiesNappi.isClickable = true
                    vihuTaikuriNappi.isClickable = true
                }
            }
        }

        //Vihollisen sotilaan painamisen hoitaminen
        vihuSotilasNappi.setOnClickListener { _->
            vihuSotilas.visibility = View.GONE
            tarkistus()
            hyokkaysKortti.visibility = View.VISIBLE
            kohde.visibility = View.GONE
            nuoli1.visibility = View.GONE
            nuoli2.visibility = View.GONE
            nuoli3.visibility = View.GONE
            vihuSotilasNappi.isClickable = false
            vihuJousimiesNappi.isClickable = false
            vihuTaikuriNappi.isClickable = false
            sotilasNappi.isClickable = false
            taikuriNappi.isClickable = false
            jousimiesNappi.isClickable = false
            if(vihuJousimies.visibility == View.VISIBLE || vihuTaikuri.visibility == View.VISIBLE || vihuSotilas.visibility == View.VISIBLE) {
                when (View.VISIBLE) {
                    sotilas.visibility -> sotilas.visibility = View.GONE
                    taikuri.visibility -> taikuri.visibility = View.GONE
                    jousimies.visibility -> jousimies.visibility = View.GONE
                }
            }
            tarkistus()
            sotilasNappi.isClickable = true
            taikuriNappi.isClickable = true
            jousimiesNappi.isClickable = true
        }

        //Vihollisen taikurin painamisen toimenpiteet
        vihuTaikuriNappi.setOnClickListener { _->
            vihuTaikuri.visibility = View.GONE
            tarkistus()
            hyokkaysKortti.visibility = View.VISIBLE
            kohde.visibility = View.GONE
            nuoli1.visibility = View.GONE
            nuoli2.visibility = View.GONE
            nuoli3.visibility = View.GONE
            vihuSotilasNappi.isClickable = false
            vihuJousimiesNappi.isClickable = false
            vihuTaikuriNappi.isClickable = false
            sotilasNappi.isClickable = false
            taikuriNappi.isClickable = false
            jousimiesNappi.isClickable = false
            if(vihuJousimies.visibility == View.VISIBLE || vihuTaikuri.visibility == View.VISIBLE || vihuSotilas.visibility == View.VISIBLE) {
                when (View.VISIBLE) {
                    sotilas.visibility -> sotilas.visibility = View.GONE
                    taikuri.visibility -> taikuri.visibility = View.GONE
                    jousimies.visibility -> jousimies.visibility = View.GONE
                }
            }
            tarkistus()
            sotilasNappi.isClickable = true
            taikuriNappi.isClickable = true
            jousimiesNappi.isClickable = true
        }

        //Vihollisen jousimiehen painamisen toimenpiteet
        vihuJousimiesNappi.setOnClickListener { _->
            vihuJousimies.visibility = View.GONE
            tarkistus()
            hyokkaysKortti.visibility = View.VISIBLE
            kohde.visibility = View.GONE
            nuoli1.visibility = View.GONE
            nuoli2.visibility = View.GONE
            nuoli3.visibility = View.GONE
            vihuSotilasNappi.isClickable = false
            vihuJousimiesNappi.isClickable = false
            vihuTaikuriNappi.isClickable = false
            sotilasNappi.isClickable = false
            taikuriNappi.isClickable = false
            jousimiesNappi.isClickable = false
            if(vihuJousimies.visibility == View.VISIBLE || vihuTaikuri.visibility == View.VISIBLE || vihuSotilas.visibility == View.VISIBLE) {
                when (View.VISIBLE) {
                    sotilas.visibility -> sotilas.visibility = View.GONE
                    taikuri.visibility -> taikuri.visibility = View.GONE
                    jousimies.visibility -> jousimies.visibility = View.GONE
                }
            }
            tarkistus()
            sotilasNappi.isClickable = true
            taikuriNappi.isClickable = true
            jousimiesNappi.isClickable = true
        }
    }

    //Funktio, joka tarkistaa, voittaako vai häviääkö pelaaja. Ei tee mitään, jos molemmilla pelaajilla on kortteja jäljellä.
    fun tarkistus(){
        if(vihuSotilas.visibility == View.GONE && vihuTaikuri.visibility == View.GONE && vihuJousimies.visibility == View.GONE){
            val voitto = Intent(this, Voitto::class.java)
            startActivity(voitto)
        }
        if(sotilas.visibility == View.GONE && taikuri.visibility == View.GONE && jousimies.visibility == View.GONE){
            val havio = Intent(this, Tappio::class.java)
            startActivity(havio)
        }
    }
}