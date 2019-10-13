package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
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
        textView32.text="Уютный и тихий ресторанчик, коих много можно найти в этом районе. Получив, заказанную пищу вы осматриваетесь... Вы видите как в ресторан входит матрос."
        button35.text="Мне лучше отсюда уйти"
        button36.text="Наблюдать за происходящим"
        button35.setOnClickListener { this.finish() }
        button36.setOnClickListener { if ((0..4).random() <3) {
            textView32.text="К матросу подходит человек в очках. Они о чем-то разговаривают в полголоса..."
            button35.text="Прислушаться"
            button36.text="Пора домой"
            button35.setOnClickListener { if ((0..5).random()<3){
                textView32.text="В зал входит чиновник... Матрос молниеносно достает револьвер и выпускает несколько пуль в чиновника прежде, чем врывается полиция и обезвреживает его."
                button35.text="Неловко получилось"
                button36.text="Пора домой"
                button35.setOnClickListener { this.finish() }
                button36.setOnClickListener { this.finish() }
            }else{
                textView32.text="Кажется они говорят что-то про подполье"
                button35.text="Интересно..."
                button36.text="Пора домой"
                button35.setOnClickListener { textView32.text="Почти сразу они разошлись. Вы даже не успели что-либо предпринять..." }
                button36.setOnClickListener { this.finish() }
            }
            }
        }else{
            textView32.text="В ресторан вбегает полицейский и арестовывает моряка"
            button35.text="Так ему и надо"
            button36.text="Хмм..."
            button35.setOnClickListener { this.finish() }
            button36.setOnClickListener { this.finish() }
        }
        }
    }

    fun quest2(){
        textView32.text="Кажется сегодня этот ресторан не работает, вокруг много полиции, где-то в отдалении слышен плач."
        button35.text="Пойти узнать что произошло"
        button36.text="Уйти"
        button35.setOnClickListener { textView32.text="Спросив несколько прохожих, вы узнаете что сегодня утром кто-то кинул бомбу в карету генерала, проводятся какие-то разыскные мероприятия и ждать снятия оцепления будет бесполезно"
            button35.text="Уйти"
            button36.isEnabled=false
            button36.isVisible=false
            button35.setOnClickListener { this.finish() }
        }
        button36.setOnClickListener {  }
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
