package com.example.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.example.entities.Asteroid;
import com.example.entities.Bullet;
import com.example.entities.Ship;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Game {
    private static Map<KeyCode, Boolean> keys = new HashMap<>();
    private static List<Bullet> bullets = new ArrayList<>();

    public static void run(Scene scene, Pane pane, Ship ship, double height, double width, Text text, AtomicInteger points){
        scene.setOnKeyPressed(event->{
            if(event.getCode() != KeyCode.SPACE) keys.put(event.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(event->{
            keys.put(event.getCode(), Boolean.FALSE);
            if(event.getCode() == KeyCode.SPACE) keys.put(event.getCode(), Boolean.TRUE);
        });

        new AnimationTimer(){

            @Override
            public void handle(long arg0) {
                if(keys.getOrDefault(KeyCode.LEFT, false)){
                    ship.rotateLeft();
                }
                if(keys.getOrDefault(KeyCode.RIGHT, false)){
                    ship.rotateRight();
                }
                if(keys.getOrDefault(KeyCode.UP, false)){
                    ship.accelerate(0.03);
                }
                if(keys.getOrDefault(KeyCode.SPACE, false)){
                    keys.put(KeyCode.SPACE, Boolean.FALSE);
                    Bullet bullet = new Bullet((int)ship.getEntity().getTranslateX(), (int)ship.getEntity().getTranslateY());
                    bullet.getEntity().setRotate(ship.getEntity().getRotate());
                    
                    bullets.add(bullet);

                    bullet.accelerate(2);
                    

                    pane.getChildren().add(bullet.getEntity());
                }
                pane.getChildren().remove(ship.getEntity()); // Removing the entity before moving it and then re-adding it to avoid trails
                ship.moveEntity(height, width, true);   
                pane.getChildren().add(ship.getEntity());

                pane.getChildren().remove(text);
                text.setText("Points: " + points);
                pane.getChildren().add(text);

                AsteroidHandler.generate(ship, pane);

                for(Asteroid asteroid: AsteroidHandler.getAsteroids()){
                    if(asteroid.checkCollision(ship) == true){
                        ship.getEntity().setFill(Color.RED);
                        stop();
                    }
                    asteroid.moveEntity(height, width, true);
                }

                for (Bullet bullet: bullets){
                    bullet.moveEntity(height, width, false);
                }

                bullets.forEach(bullet -> {
                    AsteroidHandler.getAsteroids().forEach(asteroid -> {
                        if(bullet.checkCollision(asteroid)){
                            bullet.setExists(false);
                            asteroid.setExists(false);
                            text.setText("Points: " + points.addAndGet(1));
                        }
                    });
                });
                
                bullets.stream()
                    .filter(bullet -> !bullet.getExists())
                    .forEach(bullet -> pane.getChildren().remove(bullet.getEntity()));
                bullets.removeAll(bullets.stream()
                                    .filter(bullet -> !bullet.getExists())
                                    .collect(Collectors.toList())
                );

                AsteroidHandler.getAsteroids().stream()
                .filter(asteroid -> !asteroid.getExists())
                .forEach(asteroid -> pane.getChildren().remove(asteroid.getEntity()));
                AsteroidHandler.getAsteroids().removeAll(AsteroidHandler.getAsteroids().stream()
                                .filter(asteroid -> !asteroid.getExists())
                                .collect(Collectors.toList())
                );

            }
            
        }.start();
    }
}
