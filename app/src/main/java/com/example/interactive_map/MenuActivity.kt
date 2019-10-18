package com.example.interactive_map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.interactive_map.player_class
import kotlinx.android.synthetic.main.activity_menu.*
import java.io.IOException

class MenuActivity : AppCompatActivity() {

    var player = player_class()

    fun new_player_text(view: View){
        player.set_relax(50)
        player.set_hungry(50)
        player.set_rep(5)
        player.set_money(200)
        player.set_intellect(1)
        player.set_name("Семён Манзырёв")
        writeinfile()
        startActivity(Intent(this,MainActivity::class.java))
        startActivity(Intent(this,Home::class.java))
        //button37.isEnabled = true
    }

    fun load_game(view: View){
        startActivity(Intent(this,MainActivity::class.java))
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

    fun exit(view: View) {
        this.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        try {
            val br = openFileInput("our.player")
            br.read()
            br.close()
        }catch (e : IOException){
            button37.isEnabled = false
        }

    }
}
