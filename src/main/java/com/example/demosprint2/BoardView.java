package com.example.demosprint2;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;


public class BoardView extends GridPane implements GameObserver {

    private BoardController controller;

    public BoardView(BoardController controller) {
        createBoard(this);
        this.setOpacity(0.5);
        this.controller = controller;
        this.controller.addObserver(this);

    }

    private void createBoard(GridPane gridPane) {
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                StackPane cell = createCell(row, col);
                gridPane.add(cell, col, row);
            }
        }
    }

    public StackPane createCell(int row, int col) {
        StackPane cell = new StackPane();
        cell.setMinSize(80, 80); // Set the size of the cell
        cell.setStyle("-fx-background-color: white;"); // Set initial background color to black
        cell.setBackground(null);
        Circle circle = new Circle(30);


        // Set up a DropShadow effect to give the circle a shadow underneath
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.GRAY);
        circle.setEffect(dropShadow);

        circle.setStyle("-fx-fill: transparent; -fx-stroke: transparent;");
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, createCircleClickHandler(circle));
        cell.getChildren().add(circle);
        return cell;
    }


    private EventHandler<MouseEvent> createCircleClickHandler(Circle circle) {
        return event -> {
            int row = this.getRowIndex(circle.getParent());
            int col = this.getColumnIndex(circle.getParent());
            controller.onClick(row, col);

            if (controller.getCurrentPlayerState() == GameState.MOVE) {
                if (controller.isCurrentPlayersPiece(row, col)) {
                    circle.setStyle("-fx-fill: " + controller.getLoc(row, col) + "; -fx-stroke: green;");

                }
            }
            event.consume();
        };
    }


    public void update() {
        // Refresh the components on the page to reflect the updated state of the game board
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                StackPane cell = (StackPane) this.getChildren().get(row * 7 + col);
                Circle circle = (Circle) cell.getChildren().get(0);
                circle.setStyle("-fx-fill: " + controller.getLoc(row, col) + "; -fx-stroke: " + controller.getLoc(row, col) + ";");
            }
        }
    }

    public BoardController getController() {
        return this.controller;
    }
}


