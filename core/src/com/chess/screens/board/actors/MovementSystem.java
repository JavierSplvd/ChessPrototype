package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.chess.Chess;
import com.chess.screens.board.BoardScreen;
import com.chess.screens.board.StateMachine;
import com.chess.screens.board.actors.pieces.*;

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
        movTilesActors.clear();
        int[][] behaviourMap = pieceSelected.createBehaviourMap(createCollisionMap());
        int columns = 8;
        int rows = 8;
        int numberMovementPositions = 0;
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (behaviourMap[x][y] == 2) {
                    Tile tile = new MovementDot(movementTileTex, stateMachine);
                    tile.setBoardPosition(x, y);
                    movTilesActors.addActor(tile);
                    numberMovementPositions++;
                }
            }
        }
        if(numberMovementPositions == 0){
            stateMachine.returnToChooseState();
        }else{
            stateMachine.nextState();
        }
    }

    private void behaviourFor(Piece piece, StateMachine stateMachine) {
        int x = piece.getxBoardCoord();
        int y = piece.getyBoardCoord();
        System.out.println(piece.getClass().toString());
        int yCoord;
        if (piece.getPlayer() == Chess.PLAYER.WHITES) {
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

    private int[][] createCollisionMap() {
        int columns = 8;
        int rows = 8;
        int[][] collisionMap = new int[columns][rows];
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                boolean check = boardScreen.checkBoardPosition(x, y);
                if (check) {
                    collisionMap[x][y] = 1;
                } else {
                    collisionMap[x][y] = 0;
                }
            }
        }
        return collisionMap;
    }

    public Group getGroup() {
        return movTilesActors;
    }

    public void clear() {
        movTilesActors.clearChildren();
    }
}
