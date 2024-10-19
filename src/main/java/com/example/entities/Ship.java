package com.example.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class Ship extends Entity{
    private Map<KeyCode, Boolean> keys = new HashMap<>();
    private List<Bullet> bullets = new ArrayList<>();

    public Ship(int x, int y){
        super(x, y, new Polygon(-5, -10, 15, 0, -5, 10, 2, 0));
    }

    public void handleControls(Scene scene, Pane pane, Ship ship, double height, double width){
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
                if(keys.getOrDefault(KeyCode.SPACE, false) && bullets.size() < 5){
                    keys.put(KeyCode.SPACE, Boolean.FALSE);
                    Bullet bullet = new Bullet((int)ship.getEntity().getTranslateX(), (int)ship.getEntity().getTranslateY());
                    bullet.getEntity().setRotate(ship.getEntity().getRotate());
                    
                    bullets.add(bullet);

                    bullet.accelerate(2);
                    

                    pane.getChildren().add(bullet.getEntity());
                }
                pane.getChildren().remove(ship.getEntity()); // Removing the entity before moving it and then re-adding it to avoid trails
                ship.moveEntity(height, width);   

                pane.getChildren().add(ship.getEntity());
                for (Bullet bullet: bullets){
                    bullet.moveEntity(height, width);
                }
            }
            
        }.start();
    }
}
