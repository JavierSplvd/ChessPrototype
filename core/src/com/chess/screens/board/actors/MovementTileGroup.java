package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;
import com.chess.screens.board.actors.pieces.Piece;

public class MovementTileGroup {

    private final Texture movementTileTex;
    private Group movTilesActors;


    public MovementTileGroup(Texture texture) {
        movTilesActors = new Group();
        this.movementTileTex = texture;
    }

    public Group getGroup() {
        return movTilesActors;
    }

    public void createNewMovTiles(Piece pieceSelected, StateMachine stateMachine) {
        int x = pieceSelected.getxBoardCoord();
        int y = pieceSelected.getyBoardCoord();
        Tile tile = new MovementDot(movementTileTex, stateMachine);
        if (pieceSelected.getPlayer() == Chess.PLAYER.WHITES) {
            tile.setBoardPosition(x, y + 1);
        } else {
            tile.setBoardPosition(x, y - 1);
        }
        movTilesActors.clear();
        movTilesActors.addActor(tile);
    }

    public void clear() {
        movTilesActors.clearChildren();
    }
}
