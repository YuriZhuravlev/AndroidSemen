package com.example.interactive_map

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.content.Intent
import android.view.View
import com.example.interactive_map.ui.main.MainFragment
import com.example.interactive_map.player_class
import java.io.*


class MainActivity : AppCompatActivity() {

    var player = player_class()

    fun gotohome1(view: View) {
        val homepage = Intent(this, Home::class.java)
        startActivity(homepage)
    }
    fun gotowork1(view: View) {
        val workpage = Intent(this, Work::class.java)
        startActivity(workpage)
    }
    fun gotodacha(view: View) {
        val dachapage = Intent(this, Dacha::class.java)
        startActivity(dachapage)
    }
    fun gotocenter(view: View) {
        val centerpage = Intent(this, Center::class.java)
        startActivity(centerpage)
    }
    fun gotoseafront(view: View) {
        val seafrontpage = Intent(this, Seafront::class.java)
        startActivity(seafrontpage)
    }
    fun gotoshops(view: View) {
        val shopspage = Intent(this, Shops::class.java)
        startActivity(shopspage)
    }

    fun readfromfile(){
        val br = openFileInput("our.player")
        player.set_relax(br.read())
        player.set_hungry(br.read())
        player.set_rep(br.read())
        player.set_money(br.read())
        br.close()
    }

    fun writeinfile(){
        val bw = openFileOutput("our.player", MODE_PRIVATE)
        bw.write(player.get_relax())
        bw.write(player.get_hungry())
        bw.write(player.get_rep())
        bw.write(player.get_money())
        bw.close()
    }

    fun exit_from_app(view: View) {
        writeinfile()
        finish()
    }

    fun new_player_text(view: View){
        player.set_relax(50)
        player.set_hungry(50)
        player.set_rep(0)
        player.set_money(200)
        writeinfile()
        /*val newtext = Toast.makeText(this, "Внимание все достижения будут утеряны!", Toast.LENGTH_SHORT)
        newtext.show()
        return*/
    }

    fun relax1(){
        if (player.relax3()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun relax2(){
        if (player.relax2()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun relax3(){
        if (player.eat3()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun relax4(){
        if (player.eat4()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}