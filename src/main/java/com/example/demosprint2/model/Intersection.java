package com.example.demosprint2.model;


public class Intersection {
    private Intersection top;
    private Intersection bottom;
    private Intersection left;
    private Intersection right;
    private Piece piece;
    private int x;
    private int y;

    public Intersection(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public Piece getPiece() {
        return piece;
    }
    public void addPiece(Piece piece) {
        this.piece = piece;
        this.piece.setIntersection(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void removePiece() {
        this.piece = null;
    }

    public void setTopIntersection(Intersection inter) {
        this.top = inter;
    }
    public void setBottomIntersection(Intersection inter) {
        this.bottom = inter;
    }
    public void setLeftIntersection(Intersection inter) {
        this.left = inter;
    }
    public void setRightIntersection(Intersection inter) {
        this.right = inter;
    }

    public Intersection getTopIntersection() {return this.top;}
    public Intersection getBottomIntersection() {return this.bottom;}
    public Intersection getLeftIntersection() {return this.left;}
    public Intersection getRightIntersection() {return this.right;}

}
