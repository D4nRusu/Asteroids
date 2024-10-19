package com.example.random;

import com.example.entities.Asteroid;

import javafx.scene.layout.Pane;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidHandler {
    private List<Asteroid> asteroids = new ArrayList<>();

    private List<Asteroid> populate(){
        Random rand = new Random();

        for(int i = 0; i < 5; ++i){
            Asteroid asteroid = new Asteroid(rand.nextInt(400) + 100, rand.nextInt(400) + 100);
            asteroids.add(asteroid);
        }

        return this.asteroids;
    }

    public void handle(Pane pane){
        this.populate();
        for(int i = 0; i < asteroids.size(); ++i){
            pane.getChildren().add(asteroids.get(i).getEntity());
        }
    }
}
