package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.chess.Chess;
import com.chess.screens.board.BoardScreen;
import com.chess.screens.board.StateMachine;
import com.chess.screens.board.actors.pieces.Piece;

public class MovementSystem {

    private final Texture movementTileTex;
    private Group movTilesActors;
    private BoardScreen boardScreen;


    public MovementSystem(Texture texture, BoardScreen boardScreen) {
        movTilesActors = new Group();
        this.movementTileTex = texture;
        this.boardScreen = boardScreen;
    }

    public void createNewMovTiles(Piece pieceSelected, StateMachine stateMachine) {
        int x = pieceSelected.getxBoardCoord();
        int y = pieceSelected.getyBoardCoord();
        movTilesActors.clear();
        int yCoord;
        if (pieceSelected.getPlayer() == Chess.PLAYER.WHITES) {
            yCoord = y + 1;
        } else {
            yCoord = y - 1;
        }
        // Check collisions
        if (!boardScreen.checkBoardPosition(x, yCoord)) {
            Tile tile = new MovementDot(movementTileTex, stateMachine);
            tile.setBoardPosition(x, yCoord);
            movTilesActors.addActor(tile);
            stateMachine.nextState();
        } else {
            stateMachine.returnToChooseState();
        }
    }

    public Group getGroup() {
        return movTilesActors;
    }

    public void clear() {
        movTilesActors.clearChildren();
    }
}
