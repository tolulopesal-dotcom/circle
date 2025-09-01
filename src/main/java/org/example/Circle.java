package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Circle extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Fully-qualify to avoid name clash with this class
        javafx.scene.shape.Circle c = new javafx.scene.shape.Circle(150, 100, 50);
        c.setStroke(Color.BLACK);
        c.setFill(Color.LIGHTBLUE);

        Pane pane = new Pane(c);
        Scene scene = new Scene(pane, 300, 200);

        primaryStage.setTitle("Circle Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
