package com.example.demosprint2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class GameView extends BorderPane {
    private BorderPane topPane;
    private BoardView boardView;
    private Label gameInformation;
    private Button restartButton;


    public GameView(BoardController controller) {
        this.boardView = new BoardView(controller);
        this.setCenter(boardView);
        this.boardView.setStyle("-fx-border-color: black; -fx-border-width: 4px;");

        this.gameInformation = new Label();
        this.setBottom(this.gameInformation);
        this.gameInformation.setFont(new Font("Arial", 50));

        createResetButton();
    }

    public void update() {
        this.boardView.update();
        this.gameInformation.setText(boardView.getController().getCurrentPlayerColour() + "'s turn to " + boardView.getController().getCurrentPlayerState() + " a piece");
    }

    public void gameOver(Player player) {
        this.gameInformation.setText(player.getPlayerColor() + " has WON!");
    }

    private void createResetButton() {
        this.restartButton = new Button("Restart");
        this.restartButton.setFont(new Font("Arial", 20));
        this.restartButton.setOnAction(event -> {
            boardView.getController().restart();
            update();
        });

        HBox Box = new HBox();
        Box.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(Box, Priority.ALWAYS);
        Box.getChildren().add(this.restartButton);
        this.topPane = new BorderPane();
        this.topPane.setRight(Box);
        this.setRight(topPane);
    }
}
