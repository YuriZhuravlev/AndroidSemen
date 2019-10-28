package com.example.interactive_map

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.interactive_map.player_class
import kotlinx.android.synthetic.main.activity_quest.*
import com.example.interactive_map.R.string.*
import java.io.IOException


class QuestActivity : AppCompatActivity() {

    var player = player_class()
    var scen_save = Array<Int>(4,{0})

    fun reload_stats(){
        textView36.text = player.get_money().toString()
        progressBar13.progress = player.get_relax()
        progressBar14.progress = player.get_hungry()
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

    fun quest1(){
        textView32.text=getString(R.string.quest1_text)
        button35.text=getString(R.string.quest1_var1)
        button36.text=getString(R.string.quest1_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest1_1_text)
            button35.text=getString(R.string.quest1_1_var1)
            player.set_rep(player.get_rep()+1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }

    fun quest2(){
        textView32.text=getString(R.string.quest2_text)
        button35.text=getString(R.string.quest2_var1)
        button36.text=getString(R.string.quest2_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest2_1_text)
            button35.text=getString(R.string.quest2_1_var1)
            player.set_rep(player.get_rep()+1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }
    fun quest3(){
        textView32.text=getString(R.string.quest3_text)
        button35.text=getString(R.string.quest3_var1)
        button36.text=getString(R.string.quest3_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest3_1_text)
            button35.text=getString(R.string.quest3_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }
    fun quest4(){
        textView32.text=getString(R.string.quest4_text)
        button35.text=getString(R.string.quest4_var1)
        button36.isEnabled=false
        button36.isVisible=false
        button35.setOnClickListener { textView32.text=getString(R.string.quest4_1_text)
            button35.text=getString(R.string.quest4_1_var1)
            button35.setOnClickListener { this.finish() }
        }
    }
    fun quest5(){
        textView32.text=getString(R.string.quest5_text)
        button35.text=getString(R.string.quest5_var1)
        button36.isEnabled=false
        button36.isVisible=false
        button35.setOnClickListener { textView32.text=getString(R.string.quest5_1_text)
            button35.text=getString(R.string.quest5_1_var1)
            button35.setOnClickListener { this.finish() }
        }
    }
    fun quest6(){
        textView32.text=getString(R.string.quest6_text)
        button35.text=getString(R.string.quest6_var1)
        button36.text=getString(R.string.quest6_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest6_1_text)
            button35.text=getString(R.string.quest6_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener {player.set_rep(player.get_rep()+1)
            writeinfile()
            this.finish() }
    }
    fun quest7(){
        textView32.text=getString(R.string.quest7_text)
        button35.text=getString(R.string.quest7_var1)
        button36.isEnabled=false
        button36.isVisible=false
        button35.setOnClickListener { textView32.text=getString(R.string.quest7_1_text)
            button35.text=getString(R.string.quest7_1_var1)
            button35.setOnClickListener { this.finish() }
        }
    }
    fun quest8(){
        textView32.text=getString(R.string.quest8_text)
        button35.text=getString(R.string.quest8_var1)
        button36.text=getString(R.string.quest8_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest8_1_text)
            button35.text=getString(R.string.quest8_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }
    fun quest9(){
        textView32.text=getString(R.string.quest9_text)
        button35.text=getString(R.string.quest9_var1)
        button36.text=getString(R.string.quest9_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest9_1_text)
            button35.text=getString(R.string.quest9_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }
    fun quest10(){
        textView32.text=getString(R.string.quest10_text)
        button35.text=getString(R.string.quest10_var1)
        button36.text=getString(R.string.quest10_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest10_1_text)
            button35.text=getString(R.string.quest10_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }

    fun quest11(){
        textView32.text=getString(R.string.quest11_text)
        button35.text=getString(R.string.quest11_var1)
        button36.text=getString(R.string.quest11_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest11_1_text)
            button35.text=getString(R.string.quest11_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }

    fun quest12(){
        textView32.text=getString(R.string.quest12_text)
        button35.text=getString(R.string.quest12_var1)
        button36.text=getString(R.string.quest12_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest12_1_text)
            button35.text=getString(R.string.quest12_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }
    fun quest13(){
        textView32.text=getString(R.string.quest13_text)
        button35.text=getString(R.string.quest13_var1)
        button36.text=getString(R.string.quest13_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest13_1_text)
            button35.text=getString(R.string.quest13_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }
    fun quest14(){
        textView32.text=getString(R.string.quest14_text)
        button35.text=getString(R.string.quest14_var1)
        button36.text=getString(R.string.quest14_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest14_1_text)
            button35.text=getString(R.string.quest14_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }
    fun quest15(){
        textView32.text=getString(R.string.quest15_text)
        button35.text=getString(R.string.quest15_var1)
        button36.text=getString(R.string.quest15_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest15_1_text)
            button35.text=getString(R.string.quest15_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }

    fun quest97(){
        textView32.text=getString(R.string.quest97_text)
        button35.text=getString(R.string.quest97_var1)
        button36.text=getString(R.string.quest97_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest97_1_text)
            button35.text=getString(R.string.quest97_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }

    fun quest98(){
        textView32.text=getString(R.string.quest98_text)
        button35.text=getString(R.string.quest98_var1)
        button36.text=getString(R.string.quest98_var2)
        button35.setOnClickListener { this.finish() }
        button36.setOnClickListener {
            textView32.text=getString(R.string.quest98_2_text)
            button35.text=getString(R.string.quest98_2_var1)
            button36.text=getString(R.string.quest98_2_var2)
            button36.setOnClickListener {
                textView32.text = getString(R.string.quest98_2_2_text)
                button35.text = getString(R.string.quest98_2_2_var1)
                button36.text = getString(R.string.quest98_2_2_var2)
                button35.setOnClickListener {
                    textView32.text = getString(R.string.quest98_2_2_1_text)
                    button35.text = getString(R.string.quest98_2_2_1_var1)
                    button36.text = getString(R.string.quest98_2_2_var2)
                    button35.setOnClickListener { this.finish() }
                }
                button36.setOnClickListener { this.finish() }
            }
        }
    }

    fun quest99(){
        textView32.text=getString(R.string.quest99_text)
        button35.text=getString(R.string.quest99_var1)
        button35.text=getString(R.string.quest99_var2)
        button36.setOnClickListener { this.finish() }
        button35.setOnClickListener {
            textView32.text=getString(R.string.quest99_2_1_text)
            button35.text=getString(R.string.quest99_2_1_var1)
            button36.text=getString(R.string.quest99_2_1_var2)
            button35.setOnClickListener { this.finish() }
            button36.setOnClickListener { this.finish() }
        }
    }

    fun quest100(){
        textView32.text=getString(R.string.quest100_text)
        button35.text=getString(R.string.quest100_var1)
        button36.text=getString(R.string.quest100_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest100_1_text)
            button35.text=getString(R.string.quest100_1_var1)
            button35.setOnClickListener { this.finish() }
            button36.text=getString(R.string.quest100_1_var2)
            button36.setOnClickListener {
                player.set_rep(player.get_rep()+1)
                writeinfile()
                this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)
        reload_stats()
        loadsave()


        val arguments = intent.extras
        if (arguments != null){
            val num = arguments.getInt("num")
            when (num) {
                1 -> quest1()
                2 -> quest2()
                3 -> quest3()
                4 -> quest4()
                5 -> quest5()
                6 -> quest6()
                7 -> quest7()
                8 -> quest8()
                9 -> quest9()
                10 -> quest10()
                11 -> quest11()
                12 -> quest12()
                13 -> quest13()
                14 -> quest14()
                15 -> quest15()
                97 -> quest97()
                98 -> quest98()
                99 -> quest99()
                100 -> quest100()
            else ->{

            }
            }
        }
    }
}