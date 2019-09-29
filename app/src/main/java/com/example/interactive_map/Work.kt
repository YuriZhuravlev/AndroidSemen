package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.io.*

class Work : AppCompatActivity() {

    var player = player_class()

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

    fun work1(view: View){
        if (player.work1()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun work2(view: View){
        if (player.work2()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun work3(view: View){
        if (player.work3()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun work4(view: View){
        if (player.work4()){
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
    }
}
