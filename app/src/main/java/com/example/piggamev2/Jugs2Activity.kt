package com.example.piggamev2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.piggamev2.databinding.Jugs2ActivityBinding
import kotlin.random.Random

class Jugs2Activity : AppCompatActivity() {
    private var numJugador = 1
    private var numRondasInt = 1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jugs2_activity)

        val j1 = intent.getStringExtra("j1")
        val j2 = intent.getStringExtra("j2")
        val j3 = intent.getStringExtra("j3")
        val j4 = intent.getStringExtra("j4")
        val rondasMax = intent.getIntExtra("rondas", 5)

        val listaJugadoresPartida = mutableListOf<String>()

        val binding = Jugs2ActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (j1 != null && j2 != null && j3 != null && j4 != null) {
            listaJugadoresPartida.add(j1)
            listaJugadoresPartida.add(j2)
            listaJugadoresPartida.add(j3)
            listaJugadoresPartida.add(j4)
        }
        if (j3 == "A") {
            binding.tj3.visibility = View.GONE
            listaJugadoresPartida.remove(j3)
        }
        if (j4 == "A") {
            binding.tj4.visibility = View.GONE
            listaJugadoresPartida.remove(j4)
        }
        val newListaJugs = listaJugadoresPartida.shuffled()


        var p = Partida(newListaJugs)
        p.empezar()
        binding.turno.text = "Turno de ${p.jugadores[numJugador - 1].nombre}"
        try {
            binding.tj1.text = "${p.jugadores[0].nombre} : 0"
            binding.tj2.text = "${p.jugadores[1].nombre}: 0"
            binding.tj3.text = "${p.jugadores[2].nombre} : 0"
            binding.tj4.text = "${p.jugadores[3].nombre} : 0"
        } catch (e: Exception) {
            println("Menos Jugadores")
        }


        binding.botonTirar.setOnClickListener {
            println(p.jugadores.size)
            val rand = Random.nextInt(1, 7)

            when (rand) {
                1 -> {
                    binding.dado.setImageResource(R.drawable.dice_icon_1)
                    binding.dado.visibility = View.VISIBLE
                    p.jugadores[numJugador - 1].dado1()
                    binding.puntuacionPorRonda.text = 0.toString()
                    binding.botonRendirse.visibility = View.GONE
                    if (numJugador == 1) {
                        binding.tj1.text =
                            "${p.jugadores[numJugador - 1].nombre}: ${p.jugadores[numJugador - 1].puntuacionTotal}"
                    } else if (numJugador == 2) {
                        binding.tj2.text =
                            "${p.jugadores[numJugador - 1].nombre}: ${p.jugadores[numJugador - 1].puntuacionTotal}"
                    } else if (numJugador == 3) {
                        binding.tj3.text =
                            "${p.jugadores[numJugador - 1].nombre}: ${p.jugadores[numJugador - 1].puntuacionTotal}"
                    } else if (numJugador == 4) {
                        binding.tj4.text =
                            "${p.jugadores[numJugador - 1].nombre}: ${p.jugadores[numJugador - 1].puntuacionTotal}"
                    }
                    numJugador++
                    if (numJugador == p.jugadores.size + 1) {
                        numJugador = 1
                        numRondasInt++
                        binding.numRondas.text = "Numero de ronda: $numRondasInt"
                        binding.turno.text = "Turno de ${p.jugadores[numJugador - 1].nombre}"
                        if (numRondasInt > rondasMax) {
                           // numRondasInt = 1

                            val intentWinner = Intent(this, WinnerActivity::class.java)
                            intentWinner.putExtra("numjugadores", p.jugadores.size)
                            for (i in 1..p.jugadores.size) {
                                intentWinner.putExtra("jugador" + i, p.jugadores[i - 1].nombre)
                                intentWinner.putExtra("puntosjugador" + i, p.jugadores[i - 1].puntuacionTotal)

                            }
                            startActivity(intentWinner)

//                            binding.botonTirar.visibility = View.GONE
//                            binding.botonRendirse.visibility = View.GONE
//                            binding.numRondas.visibility = View.GONE
//                            binding.dado.visibility = View.GONE
//                            binding.puntuacionPorRonda.visibility = View.GONE
//
//                            binding.turno.text = p.elegirGanador()
                            // binding.botonVolverJugar.visibility = View.VISIBLE

                        }


                    } else {
                        binding.turno.text = "Turno de ${p.jugadores[numJugador - 1].nombre}"

                    }
                    Toast.makeText(
                        this,
                        "Mala suerte, salio el 1, pierdes tu racha",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                2 -> {
                    binding.dado.setImageResource(R.drawable.dice_icon_2)
                    binding.dado.visibility = View.VISIBLE
                    p.jugadores[numJugador - 1].puntosRonda += 2
                    binding.puntuacionPorRonda.text =
                        p.jugadores[numJugador - 1].puntosRonda.toString()
                    binding.botonRendirse.visibility = View.VISIBLE
                }

                3 -> {
                    binding.dado.setImageResource(R.drawable.dice_icon_3)
                    binding.dado.visibility = View.VISIBLE
                    p.jugadores[numJugador - 1].puntosRonda += 3
                    binding.puntuacionPorRonda.text =
                        p.jugadores[numJugador - 1].puntosRonda.toString()
                    binding.botonRendirse.visibility = View.VISIBLE
                }

                4 -> {
                    binding.dado.setImageResource(R.drawable.dice_icon_4)
                    binding.dado.visibility = View.VISIBLE
                    p.jugadores[numJugador - 1].puntosRonda += 4
                    binding.puntuacionPorRonda.text =
                        p.jugadores[numJugador - 1].puntosRonda.toString()
                    binding.botonRendirse.visibility = View.VISIBLE
                }

                5 -> {
                    binding.dado.setImageResource(R.drawable.dice_icon_5)
                    binding.dado.visibility = View.VISIBLE
                    p.jugadores[numJugador - 1].puntosRonda += 5
                    binding.puntuacionPorRonda.text =
                        p.jugadores[numJugador - 1].puntosRonda.toString()
                    binding.botonRendirse.visibility = View.VISIBLE
                }

                6 -> {
                    binding.dado.setImageResource(R.drawable.dice_icon_6)
                    binding.dado.visibility = View.VISIBLE
                    p.jugadores[numJugador - 1].puntosRonda += 6
                    binding.puntuacionPorRonda.text =
                        p.jugadores[numJugador - 1].puntosRonda.toString()
                    binding.botonRendirse.visibility = View.VISIBLE
                }

            }
        }
        binding.botonRendirse.setOnClickListener {
            binding.botonRendirse.visibility = View.GONE
            binding.dado.visibility = View.GONE
            p.jugadores[numJugador - 1].sumarPuntRondas()
            binding.puntuacionPorRonda.text = 0.toString()
            if (numJugador == 1) {
                binding.tj1.text =
                    "${p.jugadores[numJugador - 1].nombre}: ${p.jugadores[numJugador - 1].puntuacionTotal}"
            } else if (numJugador == 2) {
                binding.tj2.text =
                    "${p.jugadores[numJugador - 1].nombre}: ${p.jugadores[numJugador - 1].puntuacionTotal}"
            } else if (numJugador == 3) {
                binding.tj3.text =
                    "${p.jugadores[numJugador - 1].nombre} : ${p.jugadores[numJugador - 1].puntuacionTotal}"
            } else if (numJugador == 4) {
                binding.tj4.text =
                    "${p.jugadores[numJugador - 1].nombre} : ${p.jugadores[numJugador - 1].puntuacionTotal}"
            }

            numJugador++
            if (numJugador == p.jugadores.size + 1) {
                numJugador = 1
                numRondasInt++
                binding.numRondas.text = "Numero de ronda: $numRondasInt"
                binding.turno.text = "Turno de ${p.jugadores[numJugador - 1].nombre}"
                if (numRondasInt > rondasMax) {
                   // numRondasInt = 1
                    val intentWinner = Intent(this, WinnerActivity::class.java)
                    intentWinner.putExtra("numjugadores", p.jugadores.size)
                    for (i in 1..p.jugadores.size) {
                        intentWinner.putExtra("jugador" + i, p.jugadores[i - 1].nombre)
                        intentWinner.putExtra("puntosjugador" + i, p.jugadores[i - 1].puntuacionTotal)

                    }
                    startActivity(intentWinner)
//                    binding.botonTirar.visibility = View.GONE
//                    binding.botonRendirse.visibility = View.GONE
//                    binding.numRondas.visibility = View.GONE
//                    binding.dado.visibility = View.GONE
//                    binding.puntuacionPorRonda.visibility = View.GONE
//
//                    binding.turno.text = p.elegirGanador()

                }

            } else {
                binding.turno.text = "Turno de ${p.jugadores[numJugador - 1].nombre}"

            }


        }


    }



}

class Jugadores(val nombre: String, val id: Int, var puntuacionTotal: Int, var puntosRonda: Int) {

    fun sumarPuntRondas() {
        this.puntuacionTotal += this.puntosRonda
        this.puntosRonda = 0
    }

    fun dado1() {
        this.puntosRonda = 0
    }


}

class Partida(var listaJugadores: List<String>) {
    val jugadores = arrayListOf<Jugadores>()
    val puntuacionesFinales = mutableListOf<Int>()

    fun empezar() {
        listaJugadores = listaJugadores.shuffled()
        repeat(listaJugadores.size) { index ->
            val j = Jugadores(listaJugadores[index], index + 1, 0, 0)
            jugadores.add(j)

        }

    }

    fun elegirGanador(): String {
        for (jugador in jugadores) {
            puntuacionesFinales.add(jugador.puntuacionTotal)
        }
        val puntuacionesOrdenadas = puntuacionesFinales.sortedDescending()
        if (puntuacionesOrdenadas[0] == puntuacionesOrdenadas[1]) return "Empate"

        for (i in 0..<jugadores.size) {
            if (puntuacionesOrdenadas[0] == jugadores[i].puntuacionTotal) {
                return "El ganador es el Jugador número " + jugadores[i].id.toString() + " con " + puntuacionesOrdenadas[0] + " puntos"
            }
        }
        return "No hay ganador"
    }

    fun pantallaGanador() {


    }
}