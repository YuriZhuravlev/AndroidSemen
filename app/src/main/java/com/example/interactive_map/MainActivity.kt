package com.example.interactive_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.example.interactive_map.ui.main.MainFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_quest.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    var player = player_class()

    fun gotohome1(view: View) {
        val homepage = Intent(this, Home::class.java)
        startActivity(homepage)
    }
    fun gotowork1(view: View) {
        val workpage = Intent(this, Work::class.java)
        startActivity(workpage)
    }
    fun gotodacha(view: View) {
        val dachapage = Intent(this, Dacha::class.java)
        startActivity(dachapage)
    }
    fun gotocenter(view: View) {
        val centerpage = Intent(this, Center::class.java)
        startActivity(centerpage)
    }
    fun gotoseafront(view: View) {
        val seafrontpage = Intent(this, Seafront::class.java)
        startActivity(seafrontpage)
    }
    fun gotoshops(view: View) {
        val shopspage = Intent(this, Shops::class.java)
        startActivity(shopspage)
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

    fun reload_stats(){
        textView48.text = player.get_money().toString()
        progressBar15.progress = player.get_relax()
        progressBar16.progress = player.get_hungry()
        textView42.text = player.get_intellect().toString()
        textView41.text = player.get_rep().toString()
    }

    fun exit(view: View) {
        this.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
        readfromfile()
        reload_stats()
    }
}