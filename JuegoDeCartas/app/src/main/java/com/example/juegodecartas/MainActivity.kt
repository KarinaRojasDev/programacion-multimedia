package com.example.juegodecartas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.juegodecartas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button.setOnClickListener(this)
        limpiar()

    }

    private fun limpiar() {
        binding.editText.text.clear()
    }

    override fun onClick(p0: View?) {

        when(p0?.id){

            binding.button.id -> {
                if(binding.editText.text.isNotEmpty()){
                    Snackbar.make(binding.root,"Perfecto ${binding.editText.text} quieres empezar?",Snackbar.LENGTH_INDEFINITE).setAction("OK"){
                        val intent : Intent = Intent (applicationContext,SecondActivity ::class.java)
                        val bundle :Bundle = Bundle()
                        bundle.putString("nombreUsuario",binding.editText.text.toString())
                        intent.putExtra("datos",bundle)
                        startActivity(intent)
                    }.show()
                    //origen -destino MUT IMPORTANTE
                } else{
                    Snackbar.make(binding.root,"Introduzca su nombre",Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}