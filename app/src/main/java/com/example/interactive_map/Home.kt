package com.example.interactive_map

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.interactive_map.player_class
import java.io.*

class Home : AppCompatActivity() {

    var player = player_class()

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
        val bw = BufferedWriter(OutputStreamWriter(openFileOutput("our.player", MODE_PRIVATE)))
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

    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
