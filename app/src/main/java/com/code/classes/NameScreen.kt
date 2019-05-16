package com.code.classes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.view.View
import kotlinx.android.synthetic.main.activity_name_screen.*

//Pelaajan nimen vaihtaminen
var save = false

class NameScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_screen)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        nameText.text = User.username

        //Takaisin-nappi
        backButton4.setOnClickListener { _->
            val builder = AlertDialog.Builder(this@NameScreen)
            if(tallennusKentta.text.toString().isEmpty()){
                val settingsIntent = Intent(this, Settings::class.java)
                startActivity(settingsIntent)
            }else if (!save){
                builder.setTitle("Tallenna")
                builder.setMessage("Haluatko tallentaa uuden nimen?")
                builder.setPositiveButton("Kyllä"){_, _ ->
                    User.username = tallennusKentta.text.toString()
                    val settingsIntent = Intent(this, Settings::class.java)
                    startActivity(settingsIntent)
                }
                builder.setNegativeButton("En"){_, _ ->
                    val settingsIntent = Intent(this, Settings::class.java)
                    startActivity(settingsIntent)
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }else{
                val settingsIntent = Intent(this, Settings::class.java)
                startActivity(settingsIntent)
            }
        }

        //Tallennus-nappi
        tallennus.setOnClickListener { _ ->
            save = true
            User.username = tallennusKentta.text.toString()
            nameText.text = User.username
            tallennusKentta.text.clear()
        }
    }

    /*Mikäli pelaaja painaa puhelimen omaa takaisin-painiketta, muutetaan pelaajan nimeksi merkkijono,
    joka on kirjoitettu tekstikenttään. */
    override fun onPause() {
        super.onPause()
        val builder = AlertDialog.Builder(this@NameScreen)
        if(tallennusKentta.text.toString().isEmpty()){

        } else{
            User.username = tallennusKentta.text.toString()
        }
    }
}
