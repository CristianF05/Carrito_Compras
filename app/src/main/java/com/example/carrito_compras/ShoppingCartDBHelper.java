package com.example.carrito_compras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class ShoppingCartDBHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "shoppingCart.db";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla y columnas
    private static final String TABLE_CART = "cart";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_IMAGE_RES_ID = "image_res_id"; // Columna para el ID de la imagen

    // Constructor para crear la base de datos
    public ShoppingCartDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método para crear la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_QUANTITY + " INTEGER,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_IMAGE_RES_ID + " INTEGER" + ")"; // Añadir la columna de la imagen
        db.execSQL(CREATE_CART_TABLE);

        // Inicializar datos de ejemplo si el carrito está vacío
        if (isCartEmpty()) {
            initializeData();
        }
    }

    // Método para actualizar la tabla si se cambia la versión de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    // Verifica si la tabla del carrito está vacía
    public boolean isCartEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART, null);
        boolean empty = (cursor.getCount() == 0);
        cursor.close();
        return empty;
    }

    // Inicializa datos de ejemplo
    public void initializeData() {
        // Crear algunos productos de ejemplo con imágenes
        addProductToCart("Producto 1", 2, 10.99, R.drawable.image1);
        addProductToCart("Producto 2", 1, 15.49, R.drawable.image2);
        addProductToCart("Producto 3", 5, 7.99, R.drawable.image3);
        addProductToCart("Producto 4", 3, 20.00, R.drawable.image4);
        addProductToCart("Producto 5", 4, 5.49, R.drawable.image5);
    }

    // Métodos para interactuar con la base de datos
    // Agregar un producto al carrito
    public void addProductToCart(String productName, int quantity, double price, int imageResId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, productName);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_IMAGE_RES_ID, imageResId); // Añadir el ID de la imagen
        db.insert(TABLE_CART, null, values);
        db.close();
    }

    // Obtener todos los productos del carrito
    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CART, null);
    }

    // Eliminar un producto del carrito
    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Limpiar el carrito
    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CART);
        db.close();
    }
}

