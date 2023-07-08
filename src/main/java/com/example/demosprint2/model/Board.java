package com.example.demosprint2.model;

import com.example.demosprint2.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Intersection[][] board;
    private final List<Intersection> outerRing  = new ArrayList<>();
    private final List<Intersection> middleRing = new ArrayList<>();
    private final List<Intersection> innerRing = new ArrayList<>();
    private int millCount;

    public Board() {
        createBoardHelper();
    }

    public Piece getPiece(int x, int y) {
        if (board[x][y] == null) {
            return null;
        }
        return board[x][y].getPiece();
    }

    public void removePiece(int x, int y) {
        if (board[x][y].getPiece() != null) {
            board[x][y].removePiece();
        }
    }

    public void placePiece(int x, int y, Piece piece) {
        if (board[x][y].getPiece() == null) {
            board[x][y].addPiece(piece);
        }
    }

    public Intersection getIntersection(int x, int y) {
        return board[x][y];
    }

    private void createBoardHelper() {
        board = new Intersection[7][7];
        // Set up intersections on the board
        board[0][0] = new Intersection(0,0);
        board[0][3] = new Intersection(0,3);
        board[0][6] = new Intersection(0,6);
        board[1][1] = new Intersection(1,1);
        board[1][3] = new Intersection(1,3);
        board[1][5] = new Intersection(1,5);
        board[2][2] = new Intersection(2,2);
        board[2][3] = new Intersection(2,3);
        board[2][4] = new Intersection(2,4);
        board[3][0] = new Intersection(3,0);
        board[3][1] = new Intersection(3,1);
        board[3][2] = new Intersection(3,2);
        board[3][4] = new Intersection(3,4);
        board[3][5] = new Intersection(3,5);
        board[3][6] = new Intersection(3,6);
        board[4][2] = new Intersection(4,2);
        board[4][3] = new Intersection(4,3);
        board[4][4] = new Intersection(4,4);
        board[5][1] = new Intersection(5,1);
        board[5][3] = new Intersection(5,3);
        board[5][5] = new Intersection(5,5);
        board[6][0] = new Intersection(6,0);
        board[6][3] = new Intersection(6,3);
        board[6][6] = new Intersection(6,6);

        //level 0
        board[0][0].setTopIntersection(board[0][3]);
        board[0][0].setRightIntersection(board[3][0]);

        board[0][3].setTopIntersection(board[1][3]);
        board[0][3].setLeftIntersection(board[0][0]);
        board[0][3].setRightIntersection(board[0][6]);

        board[0][6].setLeftIntersection(board[0][3]);
        board[0][6].setTopIntersection(board[3][6]);

        //level 1
        board[1][1].setTopIntersection(board[3][1]);
        board[1][1].setLeftIntersection(board[1][3]);

        board[1][3].setBottomIntersection(board[0][3]);
        board[1][3].setTopIntersection(board[2][3]);
        board[1][3].setRightIntersection(board[1][5]);
        board[1][3].setLeftIntersection(board[1][1]);

        board[1][5].setLeftIntersection(board[1][3]);
        board[1][5].setTopIntersection(board[3][5]);

        //level 2
        board[2][2].setTopIntersection(board[3][2]);
        board[2][2].setRightIntersection(board[2][3]);


        board[2][3].setLeftIntersection(board[2][2]);
        board[2][3].setRightIntersection(board[2][4]);
        board[2][3].setBottomIntersection(board[1][3]);


        board[2][4].setLeftIntersection(board[2][3]);
        board[2][4].setTopIntersection(board[3][4]);

        //level 3
        board[3][0].setTopIntersection(board[6][0]);
        board[3][0].setBottomIntersection(board[0][0]);
        board[3][0].setRightIntersection(board[3][1]);

        board[3][1].setTopIntersection(board[5][1]);
        board[3][1].setBottomIntersection(board[1][1]);
        board[3][1].setRightIntersection(board[3][2]);
        board[3][1].setLeftIntersection(board[3][0]);

        board[3][2].setTopIntersection(board[4][2]);
        board[3][2].setBottomIntersection(board[2][2]);
        board[3][2].setLeftIntersection(board[3][1]);

        board[3][4].setTopIntersection(board[4][4]);
        board[3][4].setBottomIntersection(board[2][4]);
        board[3][4].setRightIntersection(board[3][5]);

        board[3][5].setBottomIntersection(board[1][5]);
        board[3][5].setTopIntersection(board[5][5]);
        board[3][5].setRightIntersection(board[3][6]);
        board[3][5].setLeftIntersection(board[3][4]);

        board[3][6].setBottomIntersection(board[0][6]);
        board[3][6].setTopIntersection(board[6][6]);
        board[3][6].setLeftIntersection(board[3][5]);

        //level 4
        board[4][2].setRightIntersection(board[4][3]);
        board[4][2].setBottomIntersection(board[3][2]);

        board[4][3].setRightIntersection(board[4][4]);
        board[4][3].setLeftIntersection(board[4][2]);
        board[4][3].setTopIntersection(board[5][3]);

        board[4][4].setLeftIntersection(board[4][3]);
        board[4][4].setBottomIntersection(board[3][4]);

        //level 5
        board[5][1].setBottomIntersection(board[3][1]);
        board[5][1].setRightIntersection(board[5][3]);

        board[5][3].setTopIntersection(board[6][3]);
        board[5][3].setBottomIntersection(board[4][3]);
        board[5][3].setRightIntersection(board[5][5]);
        board[5][3].setLeftIntersection(board[5][1]);

        board[5][5].setBottomIntersection(board[3][5]);
        board[5][5].setLeftIntersection(board[5][3]);

        //level 6
        board[6][0].setRightIntersection(board[6][3]);
        board[6][0].setBottomIntersection(board[3][0]);

        board[6][3].setLeftIntersection(board[6][0]);
        board[6][3].setBottomIntersection(board[5][3]);
        board[6][3].setRightIntersection(board[6][6]);

        board[6][6].setLeftIntersection(board[6][3]);
        board[6][6].setBottomIntersection(board[3][6]);

        populateRings();
    }

    private void populateRings() {
        this.outerRing.add(board[0][0]); // A1
        this.outerRing.add(board[0][3]); // A4
        this.outerRing.add(board[0][6]); // A7
        this.outerRing.add(board[3][6]); // D7
        this.outerRing.add(board[6][6]); // G7
        this.outerRing.add(board[6][3]); // G4
        this.outerRing.add(board[6][0]); // G1
        this.outerRing.add(board[3][0]); // D1

        this.middleRing.add(board[1][1]); // B2
        this.middleRing.add(board[1][3]); // B4
        this.middleRing.add(board[1][5]); // B6
        this.middleRing.add(board[3][5]); // D6
        this.middleRing.add(board[5][5]); // F6
        this.middleRing.add(board[5][3]); // F4
        this.middleRing.add(board[5][1]); // F2
        this.middleRing.add(board[3][1]); // D2

        this.innerRing.add(board[2][2]); // C3
        this.innerRing.add(board[2][3]); // C4
        this.innerRing.add(board[2][4]); // C5
        this.innerRing.add(board[3][4]); // D5
        this.innerRing.add(board[4][4]); // E5
        this.innerRing.add(board[4][3]); // E4
        this.innerRing.add(board[4][2]); // E3
        this.innerRing.add(board[3][2]); // D3
    }

    public int checkForMills() {
        int millCount = 0;

        // Check for mills in the outer ring
        millCount += checkMill(outerRing.get(0), outerRing.get(1), outerRing.get(2)) ? 1 : 0;
        millCount += checkMill(outerRing.get(2), outerRing.get(3), outerRing.get(4)) ? 1 : 0;
        millCount += checkMill(outerRing.get(4), outerRing.get(5), outerRing.get(6)) ? 1 : 0;
        millCount += checkMill(outerRing.get(6), outerRing.get(7), outerRing.get(0)) ? 1 : 0;

        // Check for mills in the middle ring
        millCount += checkMill(middleRing.get(0), middleRing.get(1), middleRing.get(2)) ? 1 : 0;
        millCount += checkMill(middleRing.get(2), middleRing.get(3), middleRing.get(4)) ? 1 : 0;
        millCount += checkMill(middleRing.get(4), middleRing.get(5), middleRing.get(6)) ? 1 : 0;
        millCount += checkMill(middleRing.get(6), middleRing.get(7), middleRing.get(0)) ? 1 : 0;

        // Check for mills in the inner ring
        millCount += checkMill(innerRing.get(0), innerRing.get(1), innerRing.get(2)) ? 1 : 0;
        millCount += checkMill(innerRing.get(2), innerRing.get(3), innerRing.get(4)) ? 1 : 0;
        millCount += checkMill(innerRing.get(4), innerRing.get(5), innerRing.get(6)) ? 1 : 0;
        millCount += checkMill(innerRing.get(6), innerRing.get(7), innerRing.get(0)) ? 1 : 0;

        // Check for mills in the connections between the rings
        millCount += checkMill(outerRing.get(1), middleRing.get(1), innerRing.get(1)) ? 1 : 0;
        millCount += checkMill(outerRing.get(3), middleRing.get(3), innerRing.get(3)) ? 1 : 0;
        millCount += checkMill(outerRing.get(5), middleRing.get(5), innerRing.get(5)) ? 1 : 0;
        millCount += checkMill(outerRing.get(7), middleRing.get(7), innerRing.get(7)) ? 1 : 0;

        this.millCount = millCount;
        return millCount;
    }

    private boolean checkMill(Intersection i1, Intersection i2, Intersection i3) {
        if (i1.getPiece() == null || i2.getPiece() == null || i3.getPiece() == null) {
            return false;
        }
        return i1.getPiece().getPlayer() == i2.getPiece().getPlayer() &&
                i1.getPiece().getPlayer() == i3.getPiece().getPlayer();
    }

    public boolean checkForNewMills() {
        int previousMillCount = this.millCount;
        int newMillCount = checkForMills();

        return newMillCount > previousMillCount;
    }

    //can move method implementation
    public boolean canMove(Player player) {
        if (player.pieces == 3) {
            for (Intersection[] intersections : board) {
                for (int y = 0; y < intersections.length; y++) {
                    Intersection inter = intersections[y];
                    if (inter != null && inter.getPiece() == null) {
                        return true;  // There is an empty intersection where the player can move a piece
                    }
                }
            }
            return false;  // There are no empty intersections left
        }

        // Iterate through all intersections on the board
        for (Intersection[] intersections : board) {
            for (int y = 0; y < intersections.length; y++) {
                Intersection inter = intersections[y];
                if (inter != null) {
                    Piece piece = inter.getPiece();
                    // If the intersection contains a piece owned by the player
                    if (piece != null && piece.getPlayer() == player) {
                        // Check all four possible directions for a valid move
                        if ((inter.getTopIntersection() != null && inter.getTopIntersection().getPiece() == null) ||
                                (inter.getBottomIntersection() != null && inter.getBottomIntersection().getPiece() == null) ||
                                (inter.getLeftIntersection() != null && inter.getLeftIntersection().getPiece() == null) ||
                                (inter.getRightIntersection() != null && inter.getRightIntersection().getPiece() == null)) {
                            // If any direction is a valid move, return true
                            return true;
                        }
                    }
                }
            }
        }

        // If no valid moves found, return false
        return false;
    }

}
