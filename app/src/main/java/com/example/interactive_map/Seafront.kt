package com.example.interactive_map

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_seafront.*
import java.io.IOException

class Seafront : AppCompatActivity() {

    var player = player_class()
    var flquest = true;
    var scen_save = Array<Int>(4,{0})

    fun reload_stats(){
        //val note = Toast.makeText(this, player.get_money().toString(), Toast.LENGTH_SHORT)
        //note.show()
        textView31.text = player.get_money().toString()
        progressBar11.progress = player.get_relax()
        progressBar12.progress = player.get_hungry()
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

    fun read_paper(view: View){
        if (player.read_paper()) {
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }

    fun kek(view: View){
        if (((0..20).random()<=10) && player.kek_on_seafront()) {
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно наслаждаться!!!", Toast.LENGTH_SHORT)
            note.show()
        }
    }

    fun eat3(view: View){
        if ((scen_save[3] and 0x00000001)==0 && (0..5).random() == 3) {
            val questpages = Intent(this, QuestActivity::class.java)
            questpages.putExtra("num", 97)
            updatesave()
            startActivity(questpages)
        }else {
                if (player.eat3()) {
                    reload_stats()
                    writeinfile()
                    if ((0..5).random() == 3 || (scen_save[3] xor 0x00000006 and 0x00000006) > 0){
                        val questpages = Intent(this, QuestActivity::class.java)
                        when (scen_save[3] xor 0x00000008){
                            0x00000002 -> {questpages.putExtra("num",98); scen_save[3] or 0x00000002}
                            0x00000004 -> {questpages.putExtra("num",99); scen_save[3] or 0x00000004}
                        }
                        updatesave()
                        startActivity(questpages)
                    }
                } else {
                    val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
                    note.show()
                }
            }
    }

    fun smoke(view: View){
        if (player.smoke()) {
            reload_stats()
            writeinfile()
            if (scen_save[0] == 5){
                val questpages = Intent(this, QuestActivity::class.java)
                questpages.putExtra("num", ++scen_save[0])
                updatesave()
                startActivity(questpages)
            }
        }else{
            val note = Toast.makeText(this, "Нечего курить ?_?!", Toast.LENGTH_SHORT)
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
        setContentView(R.layout.activity_seafront)
        button16.setOnLongClickListener { show(getString(R.string.read_paper_show)) }
        button17.setOnLongClickListener { show(getString(R.string.kek_show)) }
        button24.setOnLongClickListener { show(getString(R.string.eat3_show)) }
        button26.setOnLongClickListener { show(getString(R.string.smoke_show)) }
    }

    override fun onResume() {
        super.onResume()
        readfromfile()
        reload_stats()
        loadsave()

    }
}
