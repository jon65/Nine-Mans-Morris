package com.example.demosprint2.command;

import com.example.demosprint2.GameState;
import com.example.demosprint2.Player;
import com.example.demosprint2.model.Board;
import com.example.demosprint2.model.Piece;

public class RemoveCommand implements Command {
    int x = -1;
    int y = -1;
    Board board;
    Player player;
    Piece piece;

    public RemoveCommand(Board board, Player player, Piece piece) {
        this.board = board;
        this.player = player;
        this.piece = piece;
        this.x = piece.getIntersection().getX();
        this.y = piece.getIntersection().getY();
    }

    @Override
    public boolean execute() {
        if (board.getPiece(x,y) == null) {
            return false;
        }
        System.out.println(this.player.getPlayerColor() + " has successfully removed an opponent's piece");
        board.getPiece(this.x,this.y).getPlayer().decreasePiece();
        board.removePiece(this.x, this.y);

        player.mills -=1;
        if (player.canPlacePiece() && player.mills < 1) {
            player.updateState(GameState.ADD);
        } else {
            player.updateState(GameState.MOVE);
        }
        return true;
    }


}
