package com.example.demosprint2;

public class Player {

    private String name;
    private String color;
    public int pieces=0;
    public int piecesHold=4;
    private GameState playerState;

    public int mills;

    public Player(String color, String name) {
        this.playerState = GameState.ADD;
        this.color = color;
        this.name = name;
    }
    public GameState getPlayerState() {
        return playerState;
    }

    public String getPlayerColor() {
        return color;
    }

    public void increasePiecePlaced() {
        piecesHold -= 1;
    }

    public void increasePiece() {
        this.pieces +=1;
    }

    public void decreasePiece() {
        this.pieces -=1;
    }

    public boolean canPlacePiece() {
        return (piecesHold > 0);
    }

    public void updateState(GameState state) {
        this.playerState = state;
    }

    public boolean hasLost() {
        return pieces <= 2;
    }
}
