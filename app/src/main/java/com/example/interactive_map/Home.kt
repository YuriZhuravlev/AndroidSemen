package com.example.interactive_map

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.interactive_map.player_class
import kotlinx.android.synthetic.main.activity_home.*
import java.io.*

class Home : AppCompatActivity() {

    var player = player_class()
    var scen_save = Array(4,{0})

    fun reload_stats(){
        textView7.text = player.get_money().toString()
        progressBar.progress = player.get_relax()
        progressBar2.progress = player.get_hungry()
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

    fun home1(view: View){
        if (player.relax1()) {
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun home2(view: View){
        if (player.relax4()){
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun home3(view: View){
        if (player.eat1()){
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun home4(view: View){
        if (player.eat2()){
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun eat2(view: View){
        if (player.eat2()){
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun eat4(view: View){
        if (player.eat4()){
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Невозможно выполнить!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun smoke(view: View){
        if (player.smoke()) {
            reload_stats()
            writeinfile()
        }else{
            val note = Toast.makeText(this, "Нечего курить ?_?!", Toast.LENGTH_SHORT)
            note.show()
        }
    }
    fun go_to_map(view: View){
        this.finish()
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

    fun show(str:String):Boolean{
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onResume() {
        super.onResume()
        readfromfile()
        reload_stats()
        loadsave()
        if (checkflag(0x0400) || checkflag(0x0800))
            imageView2.setImageResource(R.drawable.flat2)
//            imageView2.drawable = getDrawable(R.drawable.flat2)
        button4.setOnLongClickListener { show(getString(R.string.relax1_show)) }
        button5.setOnLongClickListener { show(getString(R.string.relax4_show)) }
        button6.setOnLongClickListener { show(getString(R.string.eat1_show)) }
        button7.setOnLongClickListener { show(getString(R.string.eat2_show)) }
        button22.setOnLongClickListener { show(getString(R.string.eat4_show)) }
        button23.setOnLongClickListener { show(getString(R.string.eat4_show)) }
        button30.setOnLongClickListener { show(getString(R.string.smoke_show)) }
        if ((scen_save[3] and 0x08000000) == 0) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle(R.string.hello_gamer_title)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.dialog_hello)
                //.setMessage(R.string.hello_gamer)
                .setCancelable(false)
                .setPositiveButton(R.string.okey, DialogInterface.OnClickListener(){
                    dialog,id -> dialog.cancel()
                })
            val note = alert.create()
            scen_save[3] = scen_save[3] or 0x08000000
            updatesave()
            note.show()
            note.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getColor(R.color.text))
        }
    }
}