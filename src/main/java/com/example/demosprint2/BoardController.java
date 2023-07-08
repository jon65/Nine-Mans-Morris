package com.example.demosprint2;

import com.example.demosprint2.command.AddCommand;
import com.example.demosprint2.command.Command;
import com.example.demosprint2.command.MoveCommand;
import com.example.demosprint2.command.RemoveCommand;
import com.example.demosprint2.model.Board;
import com.example.demosprint2.model.Piece;
import java.util.ArrayList;
import java.util.List;


public class BoardController {
    private Board board;
    private GameView gameView;
    private Player playerA;
    private Player playerB;
    private Player currentPlayer;
    private Piece selectPiece;
    private List<GameObserver> observers = new ArrayList<GameObserver>();
    private Command cmd;

    public BoardController(Board board) {
        this.board = board;
        this.playerA = new Player("black", "Player 1");
        this.playerB = new Player("red", "Player 2");
        this.currentPlayer = playerA;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObserver() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }

    public String getLoc(int x, int y) {
        if (board.getPiece(x, y) == null) {
            return "transparent";
        }
         return board.getPiece(x,y).getPlayer().getPlayerColor();
    }

    public void onClick(int sRow, int sCol) {
        //check if intersection is valid intersection

        //if null -> remove / add
        // if not null -> select piece to move / fly
        GameState state = currentPlayer.getPlayerState();

        Piece piece = board.getPiece(sRow, sCol);
        //get piece

        if (state == GameState.REMOVE) {
            if (piece != null && piece.getPlayer() != currentPlayer) { // Check if there is an opponent's piece
                cmd = new RemoveCommand(board, currentPlayer, piece);
            }
        }

        if (state == GameState.ADD) {
            if (piece == null) { // Check if the position is empty
                cmd = new AddCommand(board, currentPlayer, sRow, sCol);
            }
        }

        if (state == GameState.MOVE) {
            if (piece == null && cmd != null) {
                ((MoveCommand) cmd).setLoc(sRow, sCol);
            } else if (piece != null && piece.getPlayer() == currentPlayer) {
                cmd = new MoveCommand(board, currentPlayer, piece);
            }
        }

        if (cmd != null ) {
            boolean success = cmd.execute();
            if (success) {
                cmd = null;
                gameView.update();
                nextTurn();
            }
        }
        if (currentPlayer.getPlayerState() != GameState.LOST) {
            gameView.update();
        }
    }

    public L

    public void nextTurn() {
        Player otherPlayer = (currentPlayer == playerA) ? playerB : playerA;

        // Check the game state
        switch (currentPlayer.getPlayerState()) {
            case ADD:
                // If the current player has no pieces to add, change the state to MOVE
                if (!currentPlayer.canPlacePiece()) {
                    currentPlayer.updateState(GameState.MOVE);
                }
                break;
            case MOVE:
                // If the current player cannot move (because there are no pieces or possible moves),
                // and the other player has placed all their pieces, update the current player's state to LOSE
                if (currentPlayer.pieces == 0 || !board.canMove(currentPlayer)) {
                    if (otherPlayer.piecesHold == 0) {
                        currentPlayer.updateState(GameState.LOST);
                    }
                }
                if (otherPlayer.hasLost()) {
                    otherPlayer.updateState(GameState.LOST);
                }
                break;
            default:
                break;
        }

        boolean isSetupPhase = playerA.piecesHold > 0 || playerB.piecesHold > 0;

        if (!isSetupPhase && (otherPlayer.pieces == 0 || !board.canMove(otherPlayer))) {
            otherPlayer.updateState(GameState.LOST);
        }

        if (otherPlayer.getPlayerState() == GameState.LOST) {
            gameView.gameOver(currentPlayer);
        }

        if (currentPlayer.getPlayerState() == GameState.LOST) {
            gameView.gameOver(otherPlayer);
        } else {
            // Only switch the current player if the game has not ended
            if (currentPlayer.getPlayerState() == GameState.REMOVE) {
                ;
            } else {
                currentPlayer = (currentPlayer == playerA) ? playerB : playerA;
            }

        }
    }

    public boolean isCurrentPlayersPiece(int x, int y) {
        Piece piece = board.getPiece(x, y);
        if (piece == null) {
            return false;
        }
        return piece.getPlayer() == currentPlayer;
    }

    public String getCurrentPlayerColour() {
        return currentPlayer.getPlayerColor();
    }

    public GameState getCurrentPlayerState() {
        return currentPlayer.getPlayerState();
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void restart() {
        // Reset the board
        this.board = new Board();

        // Reset the players
        this.playerA = new Player("black", "Player 1");
        this.playerB = new Player("red", "Player 2");
        this.currentPlayer = playerA;

        // Reset selected piece and command
        this.selectPiece = null;
        this.cmd = null;
    }
}
