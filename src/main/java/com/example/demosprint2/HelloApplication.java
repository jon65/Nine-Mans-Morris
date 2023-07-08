package com.example.demosprint2;

import com.example.demosprint2.model.Board;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/ninemensboard.jpg");
        Image nineMenMorrisBoardImage = new Image(inputStream);

        // Create an ImageView object with the Nine Men's Morris board image
        ImageView nineMenMorrisBoardImageView = new ImageView(nineMenMorrisBoardImage);
        nineMenMorrisBoardImageView.setPreserveRatio(true);

        nineMenMorrisBoardImageView.setFitWidth(600);
        nineMenMorrisBoardImageView.setFitHeight(600);

        // Set the ImageView object's opacity property to a value less than 1
        nineMenMorrisBoardImageView.setOpacity(1);


        Board board = new Board();
        BoardController controller = new BoardController(board);

        GameView gameView = new GameView(controller);
        controller.setGameView(gameView);

        gameView.update();
        Scene scene = new Scene(new Group(nineMenMorrisBoardImageView, gameView), 800, 800);

        stage.setScene(scene);
        stage.setTitle("Nine Men's Morris");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
