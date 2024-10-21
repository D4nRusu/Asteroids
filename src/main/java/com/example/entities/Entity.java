package com.example.entities;

import javafx.geometry.Point2D;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Entity {
    private Polygon entity;
    private Point2D movementVector;
    private Boolean exists;

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public Entity(int x, int y, Polygon entity){
        this.entity = entity;
        this.entity.setTranslateX(x);
        this.entity.setTranslateY(y);
        this.movementVector = new Point2D(0, 0);
        this.entity.setFill(Color.WHITE);
    }

    public Polygon getEntity(){
        return this.entity;
    }

    public void rotateRight(){
        this.entity.setRotate(this.entity.getRotate() + 3);
    }

    public void rotateLeft(){
        this.entity.setRotate(this.entity.getRotate() - 3);
    }

    public void renderEntity(Pane pane, Polygon entity){
        pane.getChildren().add(entity);
    }

    public void moveEntity(double height, double width, Boolean stayOnScreen){
        this.entity.setTranslateX(this.entity.getTranslateX() + this.movementVector.getX());
        this.entity.setTranslateY(this.entity.getTranslateY() + this.movementVector.getY());

        if(this.entity.getTranslateX() < 0) {this.entity.setTranslateX(width); this.setExists(stayOnScreen);}

        if(this.entity.getTranslateX() > width) {this.entity.setTranslateX(0); this.setExists(stayOnScreen);}

        if(this.entity.getTranslateY() < 0) {this.entity.setTranslateY(height); this.setExists(stayOnScreen);}

        if(this.entity.getTranslateY() > height) {this.entity.setTranslateY(0); this.setExists(stayOnScreen);}
        
    }

    public void accelerate(double accFactor){
        double deltaX = Math.cos(Math.toRadians(entity.getRotate()));
        double deltaY = Math.sin(Math.toRadians(entity.getRotate()));

        deltaX *= accFactor;
        deltaY *= accFactor;

        this.movementVector = this.movementVector.add(deltaX, deltaY);
    }

    public Boolean checkCollision(Entity entity){
        Shape intersect = Shape.intersect(this.getEntity(), entity.getEntity());
        if((intersect.getLayoutBounds().getHeight() <= 0) || (intersect.getLayoutBounds().getWidth() <= 0)){
            return false;
        }
        return true;
    }

}
