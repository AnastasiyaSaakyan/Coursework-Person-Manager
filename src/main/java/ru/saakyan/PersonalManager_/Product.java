package ru.saakyan.PersonalManager_;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Product {
    private final String title;
    private final LocalDate date;
    private final double sum;

    public Product(String title, String date, Double sum) {
        this.title = title;
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.sum = sum;
    }

    public static Product loadJson(String jsonToString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonToString, Product.class);
    }

    public String getTitle() {

        return title;
    }

    public LocalDate getDate() {

        return date;
    }

    public Double getSum() {

        return sum;
    }

    public String saveToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {

        return "наименование продукта: " + title + " дата: " + date + " сумма: " + sum;
    }
}