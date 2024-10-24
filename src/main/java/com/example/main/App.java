package com.example.main;

import com.example.entities.Ship;
import com.example.sys.AsteroidHandler;
import com.example.sys.Game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

/**
 * Asteroids made with JavaFX by Dan Rusu
 */
public class App extends Application {

    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        scene = new Scene(pane);
        Ship ship = new Ship(400, 400);

        pane.setPrefSize(800, 800);
        pane.setStyle("-fx-background-color: #000000");
        
        ship.renderEntity(pane, ship.getEntity());
        AsteroidHandler.init(pane, ship);
        Game.run(scene, pane, ship, 800, 800);

        stage.setTitle("Asteroids by Dan Rusu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}