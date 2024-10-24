package com.example.main;

import java.util.concurrent.atomic.AtomicInteger;

import com.example.entities.Ship;
import com.example.sys.AsteroidHandler;
import com.example.sys.Game;

import javafx.application.Application;
// import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Asteroids made with JavaFX by Dan Rusu
 */
public class App extends Application {

    private static Scene scene;
    
    private Scene createGame(){
        Pane pane = new Pane();
        scene = new Scene(pane);
        Ship ship = new Ship(400, 400);
        Text text = new Text(30, 30, "Points: 0");
        text.setFont(Font.font("Arial", 20));
        text.setFill(Color.WHITE);

        pane.getChildren().add(text);

        AtomicInteger points = new AtomicInteger();

        pane.setPrefSize(800, 800);
        pane.setStyle("-fx-background-color: #000000");
        
        ship.renderEntity(pane, ship.getEntity());
        AsteroidHandler.init(pane, ship);
        Game.run(scene, pane, ship, 800, 800, text, points);

        return scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Asteroids by Dan Rusu");
        stage.setScene(createGame());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}