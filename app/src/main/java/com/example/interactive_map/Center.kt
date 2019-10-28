package com.example.interactive_map

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_center.*
import java.io.IOException

class Center : AppCompatActivity() {

    var player = player_class()
    var scen_save = Array(4,{0})

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

    fun loadsave(){
        try {
            val br = openFileInput("save")
            for (i in 0..3){
                scen_save[i]=0
                scen_save[i]=(scen_save[i] shl 8) + br.read()
                scen_save[i]=(scen_save[i] shl 8) + br.read()
                scen_save[i]=(scen_save[i] shl 8) + br.read()
                scen_save[i]=(scen_save[i] shl 8) + br.read()
            }
            br.close()
        }catch (e: IOException){
            val bw = openFileOutput("save", Context.MODE_PRIVATE)
            for (i in 0..3) {
                bw.write(0)
                bw.write(0)
                bw.write(0)
                bw.write(0)
            }
            bw.close()
        }
        return
    }

    fun updatesave(){
        val bw = openFileOutput("save", Context.MODE_PRIVATE)
        for (i in 0..3) {
            bw.write(scen_save[i] shr 24)
            bw.write(scen_save[i] shr 16)
            bw.write(scen_save[i] shr 8)
            bw.write(scen_save[i])
        }
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
            if (scen_save[0]==7){
                val questpages = Intent(this, QuestActivity::class.java)
                questpages.putExtra("num", ++scen_save[0])
                updatesave()
                startActivity(questpages)
            }
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }

    fun go_library(view: View){
        if (player.go_library()){
            reload_stats()
            writeinfile()
            if ((0..4).random()==1) {
                val questpages = Intent(this, QuestActivity::class.java)
                questpages.putExtra("num", 100)
                startActivity(questpages)
                readfromfile()
                reload_stats()
            }
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
    fun show(str:String):Boolean{
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_center)
        button14.setOnLongClickListener { show(getString(R.string.theatre_show)) }
        button15.setOnLongClickListener { show(getString(R.string.square_show)) }
        button20.setOnLongClickListener { show(getString(R.string.go_library_show)) }
        button25.setOnLongClickListener { show(getString(R.string.relax3_show)) }

    }

    override fun onResume() {
        super.onResume()
        readfromfile()
        reload_stats()
        loadsave()

    }
}
