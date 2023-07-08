package com.example.demosprint2.model;


import com.example.demosprint2.Player;


public class Piece {
    private Player player;
    protected Intersection isOn;

    public Piece(Player player) {
        this.player = player;
    }

    public void setIntersection(Intersection inter) {
        this.isOn = inter;
    }

    public Intersection getIntersection() {
        return isOn;
    }

    public Player getPlayer() {
        return this.player;
    }

}
