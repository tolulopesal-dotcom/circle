package org.example;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ShapeApp extends Application {

    private static final double MIN_RADIUS = 10;
    private static final double MAX_RADIUS = 100;

    @Override
    public void start(Stage stage) {
        //  Circle in the middle 
        Circle circle = new Circle(50, Color.LIGHTSKYBLUE);
        circle.setStroke(Color.DARKBLUE);

        StackPane center = new StackPane(circle);

        //  Status + Buttons 
        Label status = new Label();
        Button btnGrow = new Button("Grow");
        Button btnShrink = new Button("Shrink");

        // Center buttons in the bottom area
        HBox controls = new HBox(15, btnGrow, btnShrink);
        controls.setStyle("-fx-padding: 10;");
        controls.setAlignment(javafx.geometry.Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(center);
        root.setBottom(new VBox(5, controls, status)); // buttons + status stacked

        Scene scene = new Scene(root, 500, 400);

        //  Timeline Animation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(circle.translateXProperty(), -100)),
                new KeyFrame(Duration.seconds(2), new KeyValue(circle.translateXProperty(), 100))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //  Grow button (lambda) 
        btnGrow.setOnAction(e -> {
            if (circle.getRadius() < MAX_RADIUS) {
                circle.setRadius(circle.getRadius() + 5);
                status.setText("Circle grew");
            } else {
                status.setText("Max size reached");
            }
        });

        //  Shrink button (inner class)
        btnShrink.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                if (circle.getRadius() > MIN_RADIUS) {
                    circle.setRadius(circle.getRadius() - 5);
                    status.setText("Circle shrank");
                } else {
                    status.setText("Min size reached");
                }
            }
        });

        // Keyboard control with EventFilter
        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.UP) {
                btnGrow.fire();
                e.consume();
            } else if (e.getCode() == KeyCode.DOWN) {
                btnShrink.fire();
                e.consume();
            }
        });

        //  Mouse: double-click enables and disables the animation
        scene.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                if (timeline.getStatus() == Animation.Status.RUNNING) {
                    timeline.pause();
                    status.setText("Animation paused");
                } else {
                    timeline.play();
                    status.setText("Animation playing");
                }
            }
        });

        stage.setTitle("JavaFX Circle App with Buttons");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
