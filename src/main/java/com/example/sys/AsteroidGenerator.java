package com.example.sys;

import java.util.Random;
import javafx.scene.shape.Polygon;

public class AsteroidGenerator {
    public static Polygon generate(){
        Polygon octagon = new Polygon();
        octagon.getPoints().addAll(-4.0,0.0,-2.0,2.0,4.0,0.0,4.0,-4.0,2.0,-6.0,-2.0,-6.0,-4.0,-4.0);

        Random random = new Random();
        for(int i = 0; i < octagon.getPoints().size(); ++i){
            int variance = random.nextInt(10) - 2;
            int size = random.nextInt(8) + 4;
            octagon.getPoints().set(i, octagon.getPoints().get(i) * size + variance);
        }
        return octagon;
    }
}
