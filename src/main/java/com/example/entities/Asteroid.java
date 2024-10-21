package com.example.entities;

import java.util.Random;

import com.example.sys.AsteroidGenerator;

import javafx.scene.paint.Color;

public class Asteroid extends Entity{

    private double angularMomentum;

    public Asteroid(int x, int y) {
        super(x, y, AsteroidGenerator.generate());
        this.getEntity().setFill(Color.YELLOW);
        this.setExists(true);

        Random rand = new Random();
        super.getEntity().setRotate(rand.nextInt(360));
        this.accelerate(1 + rand.nextInt(1));
        
        this.angularMomentum = 0.5 - rand.nextDouble();
    }
    
    @Override
    public void moveEntity(double height, double width, Boolean stayOnScreen){
        super.moveEntity(800, 800, stayOnScreen);
        super.getEntity().setRotate(super.getEntity().getRotate() + angularMomentum);
    }
}
