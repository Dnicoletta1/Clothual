package com.example.clothual.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Clothual {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String type;
    private String brand;
    private String description;
    private String color;
    private String template;
    private int idImage;

    public Clothual(String type, String brand, String description, String color, String template, int idImage) {
        this.type = type;
        this.brand = brand;
        this.description = description;
        this.color = color;
        this.template = template;
        this.idImage = idImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
