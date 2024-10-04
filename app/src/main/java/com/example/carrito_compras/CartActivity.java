package com.example.carrito_compras;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private ProductAdapter productAdapter;
    private ArrayList<Product> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Obtener los productos del carrito desde el intent
        cartList = getIntent().getParcelableArrayListExtra("cart");
        if (cartList == null) {
            cartList = new ArrayList<>();
        }

        // Configurar RecyclerView para mostrar el carrito
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el adaptador de productos con la lista del carrito
        productAdapter = new ProductAdapter(cartList);
        recyclerViewCart.setAdapter(productAdapter);
    }
}

