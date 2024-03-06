package com.iic.rentit.domain;

import android.graphics.Bitmap;

import java.io.Serializable;

public class carDomain implements Serializable {
    public carDomain(String orderno, String title, String description, String price, String engine, String type, String kilo, String acc, String speed, String gear, Bitmap image) {
        this.orderno = orderno;
        this.title = title;
        this.description = description;
        this.price = price;
        this.engine = engine;
        this.type = type;
        this.kilo = kilo;
        this.acc = acc;
        this.speed = speed;
        this.gear = gear;
        this.image = image;
    }

    private String orderno;
    private String title;
    private String description;
    private String price;
    private String engine;
    private String type;
    private String kilo;
    private String acc;
    private String speed;

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    private String gear;
    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKilo() {
        return kilo;
    }

    public void setKilo(String kilo) {
        this.kilo = kilo;
    }

    /*public carDomain(String orderno, String title, String description, String price, String kilo, Bitmap image) {
        this.orderno = orderno;
        this.title = title;
        this.description = description;
        this.price = price;
        this.kilo = kilo;
        this.image = image;
    }*/

    public carDomain() {
        // Default constructor required for carDomain
    }
}
