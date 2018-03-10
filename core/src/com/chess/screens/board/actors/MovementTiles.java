package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.chess.screens.board.actors.pieces.Piece;

public class MovementTiles {

    private final Texture movementTileTex;
    private Group movTilesActors;

    public MovementTiles(Texture texture) {
        movTilesActors = new Group();
        this.movementTileTex = texture;
    }

    public Group getGroup() {
        return movTilesActors;
    }

    public void createNewMovTiles(Piece pieceSelected) {
        int x = pieceSelected.getxBoardCoord();
        int y = pieceSelected.getyBoardCoord();
        Tile tile = new Tile(movementTileTex);
        tile.setBoardPosition(x, y + 1);
        movTilesActors.clear();
        movTilesActors.addActor(tile);
    }
}
