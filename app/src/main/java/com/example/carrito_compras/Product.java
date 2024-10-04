package com.example.carrito_compras;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    // Atributos
    private String name;
    private int quantity;
    private double price;
    private int imageResId; // Identificador de la imagen

    // Constructor
    public Product(String name, int quantity, double price, int imageResId) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imageResId = imageResId; // Asignación de la imagen
    }

    // Constructor para Parcelable
    protected Product(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
        price = in.readDouble();
        imageResId = in.readInt();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId; // Getter para la imagen
    }

    // Método para mostrar información del producto
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imageResId=" + imageResId +
                '}';
    }

    // Métodos necesarios para implementar Parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(quantity);
        dest.writeDouble(price);
        dest.writeInt(imageResId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
