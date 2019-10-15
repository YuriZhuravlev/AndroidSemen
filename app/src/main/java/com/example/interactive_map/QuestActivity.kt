package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.interactive_map.player_class
import kotlinx.android.synthetic.main.activity_quest.*
import com.example.interactive_map.R.string.*


class QuestActivity : AppCompatActivity() {

    var player = player_class()

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

    fun quest1(){
        textView32.text=getString(R.string.quest1_begin)
        button35.text=getString(R.string.quest1_begin_var1)
        button36.text=getString(R.string.quest1_begin_var2)
        button35.setOnClickListener { this.finish() }
        button36.setOnClickListener { if ((0..4).random() <3) {
            textView32.text=getString(R.string.quest1_2_text)
            button35.text=getString(R.string.quest1_2_var1)
            button36.text=getString(R.string.quest1_2_var2)
            button35.setOnClickListener { if ((0..5).random()<3){
                textView32.text=getString(R.string.quest1_2_1_text)
                button35.text=getString(R.string.quest1_2_1_var1)
                button36.text=getString(R.string.quest1_2_1_var2)
                button35.setOnClickListener { this.finish() }
                button36.setOnClickListener { this.finish() }
            }else{
                textView32.text=getString(R.string.quest1_2_2_text)
                button35.text=getString(R.string.quest1_2_2_var1)
                button36.text=getString(R.string.quest1_2_2_var2)
                button35.setOnClickListener { textView32.text=getString(R.string.quest1_2_2_1_text)
                    button35.text=getString(R.string.quest1_2_2_1_var1)
                    button36.text=getString(R.string.quest1_2_2_1_var2)
                    button35.setOnClickListener { this.finish() }}
                button36.setOnClickListener { this.finish() }
            }
            }
        }else{
            textView32.text=getString(R.string.quest1_3_text)
            button35.text=getString(R.string.quest1_3_var1)
            button36.text=getString(R.string.quest1_3_var2)
            button35.setOnClickListener { this.finish() }
            button36.setOnClickListener { this.finish() }
        }
        }
    }

    fun quest2(){
        textView32.text=getString(R.string.quest2_text)
        button35.text=getString(R.string.quest2_var1)
        button36.text=getString(R.string.quest2_var2)
        button35.setOnClickListener { textView32.text=getString(R.string.quest2_1_text)
            button35.text=getString(R.string.quest2_1_var1)
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener { this.finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)
        reload_stats()
        if ((0..2).random() == 1)
            quest1()
        else
            quest2()

    }
}