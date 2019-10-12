package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.interactive_map.player_class
import kotlinx.android.synthetic.main.activity_quest.*


class QuestActivity : AppCompatActivity() {

    var player = player_class()

    fun reload_stats(){
        //val note = Toast.makeText(this, player.get_money().toString(), Toast.LENGTH_SHORT)
        //note.show()
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
        textView32.text="Вы видите как в ресторан входит матрос"
        button35.text="Мне лучше отсюда уйти"
        button36.text="Наблюдать за происходящим"
        button35.setOnClickListener { this.finish() }
        button36.setOnClickListener { if ((0..4).random() <3) {
            textView32.text="К матросу подходит человек в очках. Они о чем-то разговаривают в полголоса..."
            button35.text="Прислушаться"
            button36.text="Пора домой"
        }else{
            textView32.text="В ресторан вбегает полицейский и арестовывает моряка"
            button35.text="Так ему и надо"
            button36.text="Хмм..."
        }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        readfromfile()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)
        reload_stats()
        quest1()

    }
}
