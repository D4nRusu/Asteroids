package com.example.entities;

import com.example.random.AsteroidGenerator;

import javafx.scene.paint.Color;

public class Asteroid extends Entity{

    public Asteroid(int x, int y) {
        super(x, y, AsteroidGenerator.generate());
        this.getEntity().setFill(Color.YELLOW);
    }
    
}
