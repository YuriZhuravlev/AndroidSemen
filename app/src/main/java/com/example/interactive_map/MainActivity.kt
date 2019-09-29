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
        writeinfile()
        val homepage = Intent(this, Home::class.java)
        startActivity(homepage)
    }
    fun gotowork1(view: View) {
        writeinfile()
        val workpage = Intent(this, Work::class.java)
        startActivity(workpage)
    }

    fun readfromfile(){
        val br = BufferedReader(InputStreamReader(openFileInput( "out.player")))
        player.set_name(br.toString())
        player.set_relax(br.read())
        player.set_hungry(br.read())
        player.set_rep(br.read())
        player.set_money(br.read())
        br.close()
    }

    fun writeinfile(){
        val bw = BufferedWriter(
            OutputStreamWriter(
                openFileOutput("out.player", MODE_PRIVATE))
        )
        bw.write(player.get_name())
        bw.newLine()
        bw.write(player.get_relax())
        bw.newLine()
        bw.write(player.get_hungry())
        bw.newLine()
        bw.write(player.get_rep())
        bw.newLine()
        bw.write(player.get_money())
        bw.newLine()
        bw.close()
    }

    fun exit_from_app(view: View) {
        writeinfile()
        finish()
    }

    fun new_player_text(){
        val newtext = Toast.makeText(this, "Внимание все достижения будут утеряны!", Toast.LENGTH_SHORT)
        newtext.show()
        return
    }

    fun home1(){
        if (player.relax1()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun home2(){
        if (player.relax2()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun home3(){
        if (player.eat1()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun home4(){
        if (player.eat2()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun relax1(){
        if (player.relax3()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun relax2(){
        if (player.relax4()){
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
    fun work1(){
        if (player.work1()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun work2(){
        if (player.work2()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun work3(){
        if (player.work3()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun work4(){
        if (player.work4()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }



}