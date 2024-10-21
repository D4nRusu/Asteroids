package com.example.entities;

import javafx.scene.shape.Polygon;

public class Ship extends Entity{

    public Ship(int x, int y){
        super(x, y, new Polygon(-5, -10, 15, 0, -5, 10, 2, 0));
    }

}
