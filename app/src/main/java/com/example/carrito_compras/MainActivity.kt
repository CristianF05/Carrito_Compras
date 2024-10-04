package com.example.carrito_compras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCart = findViewById(R.id.buttonCart)

        buttonCart.setOnClickListener {
            // Iniciar CartActivity y pasar la lista del carrito
            val intent = Intent(this, CartActivity::class.java)
            // Suponiendo que ya tienes la lista del carrito en tu MainActivity
            val cartList = ArrayList<Product>() // Rellena esto con tus productos
            intent.putParcelableArrayListExtra("cart", cartList)
            startActivity(intent)
        }
    }
}
