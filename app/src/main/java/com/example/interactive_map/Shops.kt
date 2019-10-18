package com.example.interactive_map

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shops.*
import java.io.IOException

class Shops : AppCompatActivity() {

    var player = player_class()

    fun reload_stats(){
        //val note = Toast.makeText(this, player.get_money().toString(), Toast.LENGTH_SHORT)
        //note.show()
        textView30.text = player.get_money().toString()
        progressBar9.progress = player.get_relax()
        progressBar10.progress = player.get_hungry()
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

    fun checkflag(x: Int):Boolean{
        var y:Int = 0
        try {
            val br = openFileInput("flags")
            y = (y shl 8) + br.read()
            y = (y shl 8) + br.read()
            y = (y shl 8) + br.read()
            y = (y shl 8) + br.read()
            br.close()
        }catch (e:IOException){
            val bw = openFileOutput("flags", Context.MODE_PRIVATE)
            bw.write(0)
            bw.write(0)
            bw.write(0)
            bw.write(0)
            bw.close()
        }
        y = y and x
        return (y==x)
    }

    fun newflag(x: Int){
        var y:Int = 0
        try {
            val br = openFileInput("flags")
            y = (y shl 8) + br.read()
            y = (y shl 8) + br.read()
            y = (y shl 8) + br.read()
            y = (y shl 8) + br.read()
            br.close()
        }catch (e:IOException){
            }
        y = y or x
        val bw = openFileOutput("flags", Context.MODE_PRIVATE)
        bw.write(y shr 24)
        bw.write(y shr 16)
        bw.write(y shr 8)
        bw.write(y)
        bw.close()
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
    fun go_to_map(view: View){
        this.finish()
    }

    fun shopping(view:View) {
        val a = player.get_money()
        if (a > 10) {
            player.set_money(a - 10)
            reload_stats()
            writeinfile()
        }
        else {
            val note = Toast.makeText(this, "Последние деньги спускает...", Toast.LENGTH_SHORT)
            note.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops)
    }

    override fun onResume() {
        super.onResume()
        readfromfile()
        reload_stats()
    }
}