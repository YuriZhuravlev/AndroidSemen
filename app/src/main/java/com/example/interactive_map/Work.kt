package com.example.interactive_map

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_work.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.*

class Work : AppCompatActivity() {

    var player = player_class()
    var scen_save = Array(4,{0})

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

    fun work1(view: View){
        if (player.work1()){
            reload_stats()
            writeinfile()
            if ( scen_save[0] in 0..2) {
                val questpages = Intent(this, QuestActivity::class.java)
                questpages.putExtra("num",++scen_save[0])
                updatesave()
                startActivity(questpages)
            } else if (scen_save[0]==3 && player.get_relax()>5) {
                val questpages = Intent(this, QuestActivity::class.java)
                questpages.putExtra("num",++scen_save[0])
                updatesave()
                startActivity(questpages)
            }
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun work2(view: View){
        if (scen_save[0]==4 || scen_save[0]==6 || (0..20).random() <= 15) {
            if (player.work2()) {
                reload_stats()
                writeinfile()
                if (scen_save[0] == 4 || scen_save[0]==6){
                    val note = Toast.makeText(this, "quest5", Toast.LENGTH_SHORT)
                    note.show()
                    val questpages = Intent(this,QuestActivity::class.java)
                    questpages.putExtra("num",++scen_save[0])
                    updatesave()
                    startActivity(questpages)
                }
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

    fun show(str:String):Boolean{
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
        button8.setOnLongClickListener { show(getString(R.string.work1_show)) }
        button9.setOnLongClickListener { show(getString(R.string.work2_show)) }
        button10.setOnLongClickListener { show(getString(R.string.work3_show)) }
        button11.setOnLongClickListener { show(getString(R.string.work4_show)) }
    }

    override fun onResume() {
        super.onResume()
        readfromfile()
        reload_stats()
        loadsave()
        /*val k = scen_save[0];val k1 = scen_save[1]; val k2 = scen_save[2]; val k3 = scen_save[3]
        val note = Toast.makeText(this, "scen_save[0] $k, scen_save[1] $k1, scen_save[2] $k2, scen_save[3] $k3", Toast.LENGTH_SHORT)
        note.show()*/
    }
}
