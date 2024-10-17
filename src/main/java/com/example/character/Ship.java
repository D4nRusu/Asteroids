package com.example.character;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Polygon;

public class Ship extends Entity{
    private Map<KeyCode, Boolean> keys = new HashMap<>();

    public Ship(int x, int y){
        super(x, y, new Polygon(-5, -10, 15, 0, -5, 10, 2, 0));
    }

    public void handleControls(Scene scene, Ship ship, double height, double width){
        scene.setOnKeyPressed(event->{
            keys.put(event.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(event->{
            keys.put(event.getCode(), Boolean.FALSE);
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
                    ship.accelerate();
                }
                ship.moveEntity(height, width);
            }
            
        }.start();
    }
}
