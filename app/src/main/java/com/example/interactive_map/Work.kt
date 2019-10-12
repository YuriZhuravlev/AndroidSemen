package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_work.*
import java.io.*

class Work : AppCompatActivity() {

    var player = player_class()

    fun reload_stats(){
        //val note = Toast.makeText(this, player.get_money().toString(), Toast.LENGTH_SHORT)
        //note.show()
        textView15.text = player.get_money().toString()
        progressBar4.progress = player.get_relax()
        progressBar3.progress = player.get_hungry()
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

    fun work1(view: View){
        if (player.work1()){
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun work2(view: View){
        if ((0..20).random() <= 15) {
            if (player.work2()) {
                reload_stats()
                writeinfile()
            } else {
                val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
                note.show()
            }
        }else{
            val note = Toast.makeText(this, "Вам неудалось найти шабашку  :(", Toast.LENGTH_SHORT)
            note.show()
            player.worktrouble()
        }
    }
    fun work3(view: View){
        if ((0..20).random() <= 10) {
            if (player.work3()) {
                reload_stats()
                writeinfile()
            } else {
                val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
                note.show()
            }
        }else{
            val note = Toast.makeText(this, "Вам неудалось найти работу  :(", Toast.LENGTH_SHORT)
            note.show()
            player.worktrouble()
        }
    }
    fun work4(view: View){
        if ((0..20).random() <= 13) {
            if (player.work4()) {
                reload_stats()
                writeinfile()
            } else {
                val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
                note.show()
            }
        }else{
            val note = Toast.makeText(this, "Вам неудалось найти подработку  :(", Toast.LENGTH_SHORT)
            note.show()
            player.worktrouble()
        }
    }
    fun go_to_map(view: View){
        this.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
        reload_stats()
    }
}
