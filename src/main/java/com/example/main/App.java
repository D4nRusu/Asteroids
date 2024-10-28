package com.example.main;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Asteroids made with JavaFX by Dan Rusu
 */
public class App extends Application {

    private AppController controller;

    @Override
    public void start(Stage stage) throws Exception {
        controller = new AppController();

        stage.setTitle("Asteroids");
        stage.setScene(controller.createMenu(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}