package com.example.demosprint2.command;


import com.example.demosprint2.GameState;
import com.example.demosprint2.Player;
import com.example.demosprint2.model.Board;
import com.example.demosprint2.model.Piece;

public class MoveCommand implements Command {

    int x = -1;
    int y = -1;
    Board board;
    Player player;
    Piece piece;

    public MoveCommand(Board board, Player player, Piece piece) {
        this.board = board;
        this.player = player;
        this.piece = piece;
    }

    public void setLoc(int x, int y) {
        this.x =x;
        this.y = y;
    }

    @Override
    public boolean execute() {
        if (!(this.x >= 0 && this.y >=0)) {
            return false;
        }
        int pieceX = piece.getIntersection().getX();
        int pieceY = piece.getIntersection().getY();
        //check if player has fewer than 3 pieces -> can move anywhere
        //else -> need to only move
            if (player.pieces > 3 && board.getIntersection(x,y) != null && (
                    piece.getIntersection().getBottomIntersection() == board.getIntersection(x,y) ||
                    piece.getIntersection().getTopIntersection() == board.getIntersection(x,y) ||
                            piece.getIntersection().getLeftIntersection() == board.getIntersection(x,y) ||
                            piece.getIntersection().getRightIntersection() == board.getIntersection(x,y)
            )) {
            board.removePiece(pieceX, pieceY);
            board.placePiece(x,y,piece);
        } else if (player.pieces == 3) {
            board.removePiece(pieceX, pieceY);
            board.placePiece(x,y,piece);
        } else {
                if ( piece.getIntersection().getBottomIntersection() == piece.getIntersection() ||
                        piece.getIntersection().getTopIntersection() == piece.getIntersection()) {
                }
                return false;
            }
        System.out.println(this.player.getPlayerColor() + " has successfully moved a piece");
        if (board.checkForNewMills()) { //must update to account for forming 2 mills in one turn
            System.out.println("Mill Formed! Remove a piece from " + this.player.getPlayerColor() + "'s opponent.");
            player.updateState(GameState.REMOVE);
        }
        return true;
    }


}
