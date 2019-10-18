package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_center.*

class Center : AppCompatActivity() {

    var player = player_class()

    fun reload_stats(){
        textView28.text = player.get_money().toString()
        progressBar5.progress = player.get_relax()
        progressBar6.progress = player.get_hungry()
    }

    fun readfromfile(){
        val br = openFileInput("our.player")
        player.set_relax(br.read())
        player.set_hungry(br.read())
        player.set_rep(br.read())
        var count : Int
        count = br.read()
        count = (count shl 8) or br.read()
        player.set_money(count)
        player.set_intellect(br.read())
        br.close()
    }

    fun writeinfile(){
        val bw = openFileOutput("our.player", MODE_PRIVATE)
        bw.write(player.get_relax())
        bw.write(player.get_hungry())
        bw.write(player.get_rep())
        bw.write(player.get_money() shr 8)
        bw.write(player.get_money())
        bw.write(player.get_intellect())
        bw.close()
    }

    fun theatre(view: View){
        if (player.theatre()) {
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun square(view: View){
        if (player.square()) {
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }

    fun go_library(view: View){
        if (player.go_library()){
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this,"Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun relax3(view: View){
        if (player.relax3()){
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this,"Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun go_to_map(view: View){
        this.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_center)
    }

    override fun onResume() {
        super.onResume()
        readfromfile()
        reload_stats()
    }
}
