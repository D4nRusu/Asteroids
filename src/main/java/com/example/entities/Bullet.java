package com.example.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Bullet extends Entity{

    public Bullet(int x, int y) {
        super(x, y, new Polygon(-3, -3, 3, -3, 12, 0, 3, 3, -3, 3, -12, 0));
        this.getEntity().setFill(Color.LIME);
    }
    
}
