package com.example.interactive_map

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
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

    /*
    Дешевая съёмная комната 0x0100
Хорошая съёмная комната 0x0200
Дешевая съёмная квартира 0x0400
Своя квартира 0x0800
Обычная одежда 0x1000
Хороший костюм 0x2000
Отличный костюм 0x4000
Превосходное качество 0x8000
     */

    fun buy_cloth1(){
        if (!checkflag(0x1000)) {
            if (player.get_money() > 15) {
                player.set_money(player.get_money() - 15)
                reload_stats()
                writeinfile()
                newflag(0x1000)
                reload_button()
            } else
                Toast.makeText(this, "Net monet", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Вы уже купили", Toast.LENGTH_SHORT).show()
    }
    fun buy_cloth2(){
        if (!checkflag(0x2000)) {
            if (player.get_money() > 40) {
                player.set_money(player.get_money() - 40)
                reload_stats()
                writeinfile()
                newflag(0x2000)
                reload_button()
            } else
                Toast.makeText(this, "Net monet", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Вы уже купили", Toast.LENGTH_SHORT).show()
    }
    fun buy_cloth3(){
        if (!checkflag(0x4000)) {
            if (player.get_money() > 80) {
                player.set_money(player.get_money() - 80)
                reload_stats()
                writeinfile()
                newflag(0x4000)
                reload_button()
            } else
                Toast.makeText(this, "Net monet", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Вы уже купили", Toast.LENGTH_SHORT).show()
    }
    fun buy_cloth4(){
        if (!checkflag(0x8000)) {
            if (player.get_money() > 150) {
                player.set_money(player.get_money() - 150)
                reload_stats()
                writeinfile()
                newflag(0x8000)
                reload_button()
            } else
                Toast.makeText(this, "Net monet", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Вы уже купили", Toast.LENGTH_SHORT).show()
    }


    fun buy_flat1(){
        if (!checkflag(0x0100)) {
            if (player.get_money() > 15) {
                player.set_money(player.get_money() - 15)
                reload_stats()
                writeinfile()
                newflag(0x0100)
                reload_button()
            } else
                Toast.makeText(this, "Net monet", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Вы уже купили", Toast.LENGTH_SHORT).show()
    }
    fun buy_flat2(){
        if (!checkflag(0x0200)) {
            if (player.get_money() > 40) {
                player.set_money(player.get_money() - 40)
                reload_stats()
                writeinfile()
                newflag(0x0200)
                reload_button()
            } else
                Toast.makeText(this, "Net monet", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Вы уже купили", Toast.LENGTH_SHORT).show()
    }
    fun buy_flat3(){
        if (!checkflag(0x0400)) {
            if (player.get_money() > 80) {
                player.set_money(player.get_money() - 80)
                reload_stats()
                writeinfile()
                newflag(0x0400)
                reload_button()
            } else
                Toast.makeText(this, "Net monet", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Вы уже купили", Toast.LENGTH_SHORT).show()
    }
    fun buy_flat4(){
        if (!checkflag(0x0800)) {
            if (player.get_money() > 150) {
                player.set_money(player.get_money() - 150)
                reload_stats()
                writeinfile()
                newflag(0x0800)
                reload_button()
            } else
                Toast.makeText(this, "Net monet", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Вы уже купили", Toast.LENGTH_SHORT).show()
    }

    fun show(str:String):Boolean{
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        return true
    }

    fun reload_button(){
        var flat:String; var clot:String;
        when {
            checkflag(0x0800) -> {flat = getString(R.string.flat4) ; button19.isEnabled=false; button19.isVisible =false}
            checkflag(0x0400) -> {flat = getString(R.string.flat3) ; button19.text=getString(R.string.flat4) ; button19.setOnClickListener { buy_flat4() } }
            checkflag(0x0200) -> {flat = getString(R.string.flat2) ; button19.text=getString(R.string.flat3) ; button19.setOnClickListener { buy_flat3() } }
            checkflag(0x0100) -> {flat = getString(R.string.flat1) ; button19.text=getString(R.string.flat2) ; button19.setOnClickListener { buy_flat2() } }
            else -> {flat = getString(R.string.flat0) ; button19.text=getString(R.string.flat1) ; button19.setOnClickListener { buy_flat1() } }
        }
        when {
            checkflag(0x8000) -> { clot = getString(R.string.clothes4) ; button18.isEnabled=false; button18.isVisible =false }
            checkflag(0x4000) -> { clot = getString(R.string.clothes3) ; button18.text=getString(R.string.clothes4) ; button18.setOnClickListener { buy_cloth4() } }
            checkflag(0x2000) -> { clot = getString(R.string.clothes2) ; button18.text=getString(R.string.clothes3) ; button18.setOnClickListener { buy_cloth3() } }
            checkflag(0x1000) -> { clot = getString(R.string.clothes1) ; button18.text=getString(R.string.clothes2) ; button18.setOnClickListener { buy_cloth2() } }
            else -> { clot = getString(R.string.clothes0) ; button18.text=getString(R.string.clothes1) ; button18.setOnClickListener { buy_cloth1() } }
        }
        textView53.text = "$flat твой дом. $clot твой наряд."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops)
    }

    override fun onResume() {
        super.onResume()
        readfromfile()
        reload_stats()
        reload_button()
        //button19.setOnClickListener { buy_flat1() }
        //button19.setOnLongClickListener { show("15 monet") }

    }
}