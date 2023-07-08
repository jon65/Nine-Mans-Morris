package com.example.demosprint2.command;

import com.example.demosprint2.GameState;
import com.example.demosprint2.Player;
import com.example.demosprint2.model.Board;
import com.example.demosprint2.model.Piece;

public class AddCommand implements Command {
    int x;
    int y;
    Board board;
    Player player;

    public AddCommand(Board board, Player player, int x, int y) {
        this.x=x;
        this.y=y;
        this.board = board;
        this.player = player;
    }

    @Override
    public boolean execute() {
        if (board.getPiece(x,y) == null && player.canPlacePiece()) {
            // add piece to board when intersection clicked is empty and player has pieces remaining to place
            board.placePiece(x, y, new Piece(this.player));
            player.increasePiecePlaced();
            player.increasePiece();
            System.out.println(this.player.getPlayerColor() + " has successfully placed a piece");
            if (!player.canPlacePiece()) { // player has no more pieces to place, transition to "move" phase
                System.out.println(this.player.getPlayerColor() + " has successfully placed all their pieces" +
                        "they can now move their pieces on the board");
                player.updateState(GameState.MOVE);
            }
            if (board.checkForNewMills()) { //must update to account for forming 2 mills in one turn
                System.out.println("Mill Formed! Remove a piece from " + this.player.getPlayerColor() + "'s opponent.");
                player.mills += 1;
                player.updateState(GameState.REMOVE);
            }
        }
        return true;
    }
}

