package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File

class Work : AppCompatActivity() {

    var player = player_class()

    fun readfromfile(){
        val FILE = File("our.player").bufferedReader()
        player.set_name(FILE.readLine())
        player.set_relax(FILE.readLine().toInt())
        player.set_hungry(FILE.readLine().toInt())
        player.set_rep(FILE.readLine().toInt())
        player.set_money(FILE.readLine().toInt())
        FILE.close()
    }

    fun writeinfile(){
        val FILE = File("our.player").bufferedWriter()
        FILE.write(player.get_name())
        FILE.write(player.get_relax())
        FILE.write(player.get_hungry())
        FILE.write(player.get_rep())
        FILE.write(player.get_money())
        FILE.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
    }
}
