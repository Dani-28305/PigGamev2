package com.example.piggamev2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.piggamev2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jugs =
            listOf("Selecciona cantidad de jugadores", "2 Jugadores", "3 Jugadores", "4 Jugadores")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, jugs)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerList.adapter = adapter


        binding.spinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View,
                pos: Int,
                id: Long
            ) {
                if (pos == 0) return

                val rondas = binding.rondas.text.toString().toInt()
                var jugadores= 0

                val moverView = when (pos) {
                    1 -> {
                        jugadores = 2
                        Intent(this@MainActivity, NombresDinamicosActivity::class.java)
                    }
                    2 -> {
                        jugadores = 3
                        Intent(this@MainActivity, NombresDinamicosActivity::class.java)
                    }
                    3 -> {
                        jugadores = 4
                        Intent(this@MainActivity, NombresDinamicosActivity::class.java)
                    }
                    else -> null
                }


                moverView?.putExtra("nRondas", rondas)
                moverView?.putExtra("nJugadores", jugadores)
                startActivity(moverView)
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}