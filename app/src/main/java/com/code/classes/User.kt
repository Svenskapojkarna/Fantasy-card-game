package com.code.classes

//Pelaaja-luokka. Singleton, koska vain yksi pelaaja

object User {
    val cards = HashMap<String, Card>()
    var username = "Skipidiboiiii"

    fun addCard(card:Card){
        cards[card.name] = card
    }
    fun removeCard(name:String){
        cards.remove(name)
    }
}