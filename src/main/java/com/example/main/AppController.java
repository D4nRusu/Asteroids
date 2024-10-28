package com.example.main;

import java.util.concurrent.atomic.AtomicInteger;

import com.example.entities.Ship;
import com.example.sys.AsteroidHandler;
import com.example.sys.Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AppController {

    private Scene scene;

    public Scene createGame(){
        Pane pane = new Pane();
        pane.setPrefSize(800, 800);
        pane.setStyle("-fx-background-color: #000000");

        scene = new Scene(pane);
        Ship ship = new Ship(400, 400);
        Text text = new Text(30, 30, "Points: 0");
        text.setFont(Font.font("Arial", 20));
        text.setFill(Color.WHITE);

        pane.getChildren().add(text);

        AtomicInteger points = new AtomicInteger();

        ship.renderEntity(pane, ship.getEntity());
        AsteroidHandler.init(pane, ship);
        Game.run(scene, pane, ship, 800, 800, text, points);

        return scene;
    }

    public Scene createMenu(){
        GridPane pane = new GridPane();
        pane.setPrefSize(800, 800);
        pane.setStyle("-fx-background-color: #000000");
        pane.setPadding(new Insets(20,20,20,20));
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(20);

        scene = new Scene(pane);

        Text title = new Text("ASTEROIDS");
        title.setFont(Font.font("Arial", 70));
        title.setFill(Color.WHITE);


        Text text = new Text("Press any key to start the game");
        text.setFont(Font.font("Arial", 30));
        text.setFill(Color.WHITE);

        pane.add(title, 1, 1);
        pane.add(text, 1, 2);

        return scene;
    }

}
