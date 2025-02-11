package com.example.juegodecartas

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegodecartas.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class SecondActivity : AppCompatActivity() ,OnClickListener{

    private lateinit var binding: ActivitySecondBinding

    private var bundleRecuperado : Bundle? = null
    private lateinit var nombreUsuarioRecuperado : String

    //cuando NO CREAMOS imagenes pero las descargamos  solo llamamos con R.NOMBEARCHIVO.NOMBREIMAGEN
    val arrayCartas = arrayOf(R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
        R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13)
    private var contador = 0
    private var clickUsuario= 0
    private var cartaAleatorio= Random.nextInt(0,13)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TRABAJAMOS AQUI
        //BUNDLE
        bundleRecuperado = intent.extras?.getBundle("datos")

        nombreUsuarioRecuperado = bundleRecuperado?.getString("nombre") ?:"sin nombre"

        val sanckbar = Snackbar.make(binding.root,"Bienvenido ${nombreUsuarioRecuperado}",Snackbar.LENGTH_INDEFINITE)
        sanckbar.setAction("Empezar"){
            sanckbar.dismiss()
            aleatorios()
            imagenes()
            binding.Arriba.setOnClickListener(this)
            binding.Abajo.setOnClickListener(this)

        }.show()
    }

    private fun imagenes() {
        binding.cartaActual.setImageResource(arrayCartas[cartaAleatorio])
    }

    private fun aleatorios() {
        clickUsuario = Random.nextInt(0,13)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.Arriba.id ->{
                if(clickUsuario > cartaAleatorio){
                    contador++
                    cartaAleatorio = clickUsuario // CARTA ACTUAL PASA A SER LA QUE ADIVINO OSEA LA ALEATORIA HASTA QUE SE EQUIBOQUE
                    imagenes()
                }else{
                    sanckbar()
                }
            }
            binding.Abajo.id -> {
                if(clickUsuario < cartaAleatorio){
                    contador++
                    cartaAleatorio = clickUsuario
                    imagenes()
                }else{
                    sanckbar()
                }
            }
        }
    }

    private fun sanckbar() {
        val snackbar = Snackbar.make(
            binding.root, "Ha perdido el juego, ha cosegido una puntuación de ${contador} puntos",
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction("Desea salir del juego?") {
            snackbar.dismiss()
            finish()}.show()
    }
}