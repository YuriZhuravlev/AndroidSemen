package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shops.*

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
        br.close()
    }

    fun writeinfile(){
        val bw = openFileOutput("our.player", MODE_PRIVATE)
        bw.write(player.get_relax())
        bw.write(player.get_hungry())
        bw.write(player.get_rep())
        bw.write(player.get_money() shr 8)
        bw.write(player.get_money())
        bw.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops)
        reload_stats()
    }
}
