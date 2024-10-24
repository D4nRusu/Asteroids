package com.example.sys;

import com.example.entities.Asteroid;
import com.example.entities.Ship;

import javafx.scene.layout.Pane;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidHandler {
    private static List<Asteroid> asteroids = new ArrayList<>();

    private static List<Asteroid> populate(Ship ship){
        Random rand = new Random();

        for(int i = 0; i < 5; ++i){
            Asteroid asteroid = new Asteroid(rand.nextInt(400), rand.nextInt(400));
            while(ship.checkCollision(asteroid) == true){
                asteroid = new Asteroid(rand.nextInt(400), rand.nextInt(400));
            }
            asteroids.add(asteroid);
        }

        return asteroids;
    }

    public static List<Asteroid> getAsteroids(){
        return asteroids;
    }

    public static void init(Pane pane, Ship ship){
        populate(ship);
        for(int i = 0; i < asteroids.size(); ++i){
            pane.getChildren().add(asteroids.get(i).getEntity());
        }
    }

    public static void generate(Ship ship, Pane pane){
        Random rand = new Random();

        if(Math.random() < 0.005){
            Asteroid asteroid = new Asteroid(rand.nextInt(400), rand.nextInt(400));
            if(ship.checkCollision(asteroid) == false){
                asteroids.add(asteroid);
                pane.getChildren().add(asteroid.getEntity());
            }
        }
    }
}
