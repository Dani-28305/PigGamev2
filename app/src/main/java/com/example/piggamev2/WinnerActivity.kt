package com.example.piggamev2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.piggamev2.databinding.WinnerBinding

class WinnerActivity : AppCompatActivity() {
    var jugadoresfinal: ArrayList<Jugadores> = ArrayList()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.winner)
        val binding = WinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var numjugadores = intent.getIntExtra("numjugadores", 0)
        for (i in 1..numjugadores) {
            var nombrejug: String = intent.getStringExtra("jugador" + i).toString()
            var puntosjug = intent.getIntExtra("puntosjugador" + i, 0)
            jugadoresfinal.add(Jugadores(nombrejug, i, puntosjug, 0))
        }

        if (jugadoresfinal.size == 2) {
            binding.winner3.visibility = View.GONE
            binding.winner4.visibility = View.GONE
        } else if (jugadoresfinal.size == 3) {
            binding.winner4.visibility = View.GONE
        }

        val puntosOrdenados = mutableListOf<Int>()
        for (jugador in jugadoresfinal) {
            puntosOrdenados.add(jugador.puntuacionTotal)
        }

        puntosOrdenados.sortDescending()
        println(puntosOrdenados.toString())
        var primeroL =false
        var segundoL=false
        var tercerL=false
        for (jugador in jugadoresfinal) {
            if (jugador.puntuacionTotal == puntosOrdenados[0] && !primeroL) {
                val nomGanador = jugador.nombre
                binding.winner.text = "$nomGanador : ${puntosOrdenados[0]}"
                if (puntosOrdenados[0]==puntosOrdenados[1]){
                    binding.textWinner.text="Empate del primero y el segundo"
                }else binding.textWinner.text="El ganador de la partida a sido $nomGanador con un total de ${puntosOrdenados[0]}"
                primeroL=true
            }
             else if (jugador.puntuacionTotal == puntosOrdenados[1] &&!segundoL) {
                val nomSegun = jugador.nombre
                binding.winner2.text = "$nomSegun : ${puntosOrdenados[1]}"
                segundoL=true
            }
            else if(puntosOrdenados.size>=3){
                if (jugador.puntuacionTotal == puntosOrdenados[2]&&!tercerL) {
                    val nomSegun = jugador.nombre
                    binding.winner3.text = "$nomSegun : ${puntosOrdenados[2]}"
                    tercerL=true
                }
                else if(puntosOrdenados.size>=4){
                    if (jugador.puntuacionTotal == puntosOrdenados[3]) {
                        val nomSegun = jugador.nombre
                        binding.winner4.text = "$nomSegun : ${puntosOrdenados[3]}"
                    }
                }
            }
        }
    }
}