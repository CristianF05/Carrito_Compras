package com.example.carrito_compras

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerViewCart: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var cartList: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Obtener los productos del carrito desde el intent
        cartList = intent.getParcelableArrayListExtra<Product>("cart") ?: arrayListOf()

        // Configurar RecyclerView para mostrar el carrito
        recyclerViewCart = findViewById(R.id.recyclerViewCart)
        recyclerViewCart.layoutManager = LinearLayoutManager(this)

        productAdapter = ProductAdapter(cartList)
        recyclerViewCart.adapter = productAdapter
    }
}
