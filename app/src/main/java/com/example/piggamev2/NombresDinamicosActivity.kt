package com.example.piggamev2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.piggamev2.databinding.NombresDinamicosActivityBinding

class NombresDinamicosActivity : AppCompatActivity() {

    private var nom1: String? = null
    private var nom2: String? = null
    private var nom3: String? = null
    private var nom4: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nombres_dinamicos_activity)

        val numJugadores = intent.getIntExtra("nJugadores", 2)
        val numRondas = intent.getIntExtra("nRondas", 5)

        val binding = NombresDinamicosActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listaNombres = listOf(
            "Aitor Tilla",
            "Ana Conda",
            "Armando Broncas",
            "Aurora Boreal",
            "Bartolo Mesa",
            "Carmen Mente",
            "Dolores Delirio",
            "Elsa Pato",
            "Enrique Cido",
            "Esteban Dido",
            "Elba Lazo",
            "Fermin Tado",
            "Lola Mento",
            "Luz Cuesta",
            "Margarita Flores",
            "Paco Tilla",
            "Pere Gil",
            "Pío Nono",
            "Salvador Tumbado",
            "Zoila Vaca"
        )

        val recycler1: RecyclerView = binding.rv1
        val recycler2: RecyclerView = binding.rv2
        val recycler3: RecyclerView = binding.rv3
        val recycler4: RecyclerView = binding.rv4

        recycler1.layoutManager = LinearLayoutManager(this)
        recycler2.layoutManager = LinearLayoutManager(this)
        recycler3.layoutManager = LinearLayoutManager(this)
        recycler4.layoutManager = LinearLayoutManager(this)
        recycler1.adapter = CustomAdapter(listaNombres){ selectedName ->
            nom1= selectedName
            nombresLlenos(numRondas)
        }
        recycler2.adapter = CustomAdapter(listaNombres){ selectedName ->
            nom2= selectedName
            nombresLlenos(numRondas)
        }
        recycler3.adapter = CustomAdapter(listaNombres){ selectedName ->
            nom3= selectedName
            nombresLlenos(numRondas)
        }
        recycler4.adapter = CustomAdapter(listaNombres){ selectedName ->
            nom4= selectedName
            nombresLlenos(numRondas)
        }
        if (numJugadores >= 2) {
            binding.nj1.visibility = View.VISIBLE
            binding.nj2.visibility = View.VISIBLE
            binding.rv1.visibility = View.VISIBLE
            binding.rv2.visibility = View.VISIBLE
        }
        if (numJugadores >= 3) {
            binding.nj3.visibility = View.VISIBLE
            binding.rv3.visibility = View.VISIBLE
        }
        if (numJugadores >= 4) {
            binding.nj4.visibility = View.VISIBLE
            binding.rv4.visibility = View.VISIBLE
        }

        if(numJugadores==2){
            nom3="A"
            nom4="A"
        }
        if (numJugadores==3){
            nom4="A"
        }

    }
    private fun nombresLlenos(rondas: Int) {
        if (nom1 != null && nom2 != null && nom3 != null && nom4 != null) {
            val intent = Intent(this, Jugs2Activity::class.java)

            intent.putExtra("j1", nom1)
            intent.putExtra("j2", nom2)
            intent.putExtra("j3", nom3)
            intent.putExtra("j4", nom4)
            intent.putExtra("rondas", rondas)


            startActivity(intent)
        }
    }

}