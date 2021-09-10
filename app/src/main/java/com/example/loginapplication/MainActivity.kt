package com.example.loginapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    //per prima cosa abbiamo aggiunto l'immagine andando in res/mipmap dove troviamo già ic_launcher, in quanro anche l'icona di lancio dell'applicazione ha bisogno di essere definita per ogni tipologia di densità dello schermo
    // creiamo un nuovo Image Asset facendo click destro sulla cartella mipmap> new> immage asset, andiamo ad inserire un immagine e in automatico creerà tutte le immagini per i vari tipi di schermi e troviamo anche le immagini con i bordi arrotondati
    //creata la mipmap la utilizziamo andando nel file res/layout/activity_main.xml, inseriamo una imageViw all'interno dell'activity e selezioniamo mipmap per poter importare l'immagine appena creata come mipmap.
    //ancoriamo i bordi, sia dalla finestra che dal menù di lato volendo
    //
    //Nel codice del file xml   android:layout_width="wrap_content" stanno a significare che la dimensione della imageView inserita, in altezza e larghezza, è uguale al suo contenuto, quindi alla dimensione effettiva dell'immagine
    //                          android:layout_height="wrap_content"
    //
    // mentre   app:layout_constraintEnd_toEndOf="parent"     sono i punti in cui abbiamo ancorato il nostro oggetto e il punto in cui è posizionato l'ungo lasse verticale dell'activity
    //          app:layout_constraintHorizontal_bias="0.5"
    //          app:layout_constraintStart_toStartOf="parent"
    //          app:layout_constraintTop_toTopOf="parent"

    //adesso andiamo ad inserire due editText, una button e una text View. PlainText per il nome utente e Password per la password. Ognuna ancorata, guardare il dile "activity_main.xml" per vdeere come fare

    //nel file activity_main.xml abbiamo inserito le stringhe, inserite a mano nella finerstra, all'interno del file strings.xml.
    //andare sulla stringa presente nel codice, fare tasto destro e selezionare dagli aiuti "extract string resource", fare ok ,e la stringa verrà inserita nel file strings.xml
    //aprendo il file xml, e facendo open editor, si può andare ad aggiungere per le varie stringhe la traduzione in altri linguaggi che poi verranno utilizzati dall'applicazione quando legge le impostazioni del dispositivo

    //per rimuovere la toolbar di default andare in res/values/themes/themes.xml e fare la stessa cosa anche in themes.xml(night) e modificare la riga 3 con --> <style name="Theme.LoginApplication" parent="Theme.MaterialComponents.DayNight.NoActionBar">

    //per aggiungere il doppio colore al bottone, che quando è premuto cambia colore, guardare il file res/drawable/two_state_button.xml, i colori andranno ridefiniti nel file res/values/color.xml
    //nel file activity_main.xml dove si definisce il bottone come layout al posto che "<button" bisosgna mettere "<android.widget.Button" per poter opersonalizzare il background e modificare lo stile del button

    //aggiungiamo il listener al button tramite la funzione checkLogin() che dovrà poi aggiunto nella sezione onClick del button

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun checkLogin(v:View){
        val email: String = editTextUsername.text.toString()                               //editTextUsername.getText().toString()
        if(!isValidEmail(email)){
            //Set error message for email field
            //editTextUserName.setError("Invalid email")
            editTextUsername.error = resources.getText(R.string.invalid_email)                 //editTextUsername.setError("Invalid email")
        }

        val password = editTextPassword.text.toString()                             //editTextPassword.getText().toString()
        if(!isValidPassword(password)){
            //Set error message for password field
            //editTextPassword.setError("Invalid password")
            editTextPassword.error = resources.getText(R.string.invalid_password)              //editTextUsername.setError("Invalid Password")
        }

        if(isValidEmail(email) && isValidPassword(password)){
            //validation complete, aprire una nuova activity e fare il finish() del login
        }
    }

    private fun isValidPassword(password: String): Boolean {
        return password != null && password.length >= 4
    }

    private fun isValidEmail(email: String): Boolean{
        val emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"                                                            //("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(email)
        return matcher.matches()  //se l'indirizzo email soddisfa la reg-ex ritornerà un valore true altrimenti false
    }
}