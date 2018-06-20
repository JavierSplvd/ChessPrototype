package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.chess.Chess;
import com.chess.screens.board.BoardScreen;
import com.chess.screens.board.StateMachine;
import com.chess.screens.board.actors.pieces.*;

public class MovementSystem {

    public enum TILE_STATUS {
        FREE, OCCUPIED, MOVE, ATTACK
    }

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

        TILE_STATUS[][] behaviourMap = createBehaviourMap(createCollisionMap(), pieceSelected);

        int columns = 8;
        int rows = 8;
        int numberMovementPositions = 0;
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (behaviourMap[x][y] == TILE_STATUS.MOVE) {
                    Tile tile = new MovementTile(movementTileTex, stateMachine);
                    tile.setBoardPosition(x, y);
                    movTilesActors.addActor(tile);
                    numberMovementPositions++;
                }
            }
        }
        if (numberMovementPositions == 0) {
            stateMachine.returnToChooseState();
        } else {
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
            Tile tile = new MovementTile(movementTileTex, stateMachine);
            tile.setBoardPosition(x, yCoord);
            movTilesActors.addActor(tile);
            stateMachine.nextState();
        } else {
            stateMachine.returnToChooseState();
        }
    }

    private TILE_STATUS[][] createCollisionMap() {
        int columns = 8;
        int rows = 8;
        TILE_STATUS[][] collisionMap = new TILE_STATUS[columns][rows];
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                boolean exist = boardScreen.checkBoardPosition(x, y);
                if (exist) {
                    collisionMap[x][y] = TILE_STATUS.OCCUPIED;
                } else {
                    collisionMap[x][y] = TILE_STATUS.FREE;
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

    private TILE_STATUS classify(TILE_STATUS[][] collisionMap, int i, int j) {
        if (i < 8 && i >= 0 && j < 8 && j >= 0) {
            if (collisionMap[i][j] == TILE_STATUS.FREE) {
                return TILE_STATUS.MOVE;
            } else if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                return TILE_STATUS.ATTACK;
            }
        }
        return TILE_STATUS.FREE;
    }

    private TILE_STATUS[][] createBehaviourMap(TILE_STATUS[][] collisionMap, Piece piece) {
        int x = piece.getxBoardCoord();
        int y = piece.getyBoardCoord();
        TILE_STATUS[][] behaviourMap = createCollisionMap();

        if (piece instanceof Pawn) {
            if (piece.getPlayer() == Chess.PLAYER.WHITES) {
                y = piece.getyBoardCoord() + 1;
            } else {
                y = piece.getyBoardCoord() - 1;
            }

            if (collisionMap[x][y] == MovementSystem.TILE_STATUS.FREE) {
                behaviourMap[x][y] = MovementSystem.TILE_STATUS.MOVE;
            }

            if (piece.getPlayer() == Chess.PLAYER.WHITES && !piece.isMoved()) {
                y = piece.getyBoardCoord() + 2;
            } else if (piece.getPlayer() == Chess.PLAYER.BLACKS && !piece.isMoved()) {
                y = piece.getyBoardCoord() - 2;
            }

            if (collisionMap[x][y] == MovementSystem.TILE_STATUS.FREE) {
                behaviourMap[x][y] = MovementSystem.TILE_STATUS.MOVE;
            }
            return behaviourMap;
        }
        if (piece instanceof King) {
            for (int angle = 0; angle < 360; angle = angle + 45) {
                int dx = (int) Math.round(Math.cos(Math.toRadians(angle)) * 1 - Math.sin(Math.toRadians(angle)) * 0);
                int dy = (int) Math.round(Math.sin(Math.toRadians(angle)) * 1 + Math.cos(Math.toRadians(angle)) * 0);
                if (x + dx < 8 && x + dx >= 0 && y + dy < 8 && y + dy >= 0) {
                    behaviourMap[x + dx][y + dy] = classify(collisionMap, x + dx, y + dy);
                }
            }
            return behaviourMap;
        }
        if (piece instanceof Queen) {
            //  Diagonal NE
            int i = x + 1;
            int j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i++;
                j++;
            }
            // Diagonal SE
            i = x + 1;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i++;
                j--;
            }
            // Diagonal NW
            i = x - 1;
            j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i--;
                j++;
            }
            // Diagonal SW
            i = x - 1;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i--;
                j--;
            }
            // Direction N
            i = x;
            j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                j++;
            }
            // Direction S
            i = x;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                j--;
            }
            // Direction E
            i = x + 1;
            j = y;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i++;
            }
            // Direction W
            i = x - 1;
            j = y;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i--;
            }

            return behaviourMap;
        }
        if (piece instanceof Knight) {
            int originalX = 1;
            int originalY = 2;
            int dx;
            int dy;
            for (int i = 0; i < 360; i = i + 90) {
                dx = (int) Math.round(Math.cos(Math.toRadians(i)) * originalX - Math.sin(Math.toRadians(i)) * originalY);
                dy = (int) Math.round(Math.sin(Math.toRadians(i)) * originalX + Math.cos(Math.toRadians(i)) * originalY);
                if (x + dx < 8 && x + dx >= 0 && y + dy < 8 && y + dy >= 0) {
                    behaviourMap[x + dx][y + dy] = classify(collisionMap, x + dx, y + dy);
                }
            }
            originalX = -1;
            originalY = 2;
            for (int i = 0; i <= 360; i = i + 90) {
                dx = (int) Math.round(Math.cos(Math.toRadians(i)) * originalX - Math.sin(Math.toRadians(i)) * originalY);
                dy = (int) Math.round(Math.sin(Math.toRadians(i)) * originalX + Math.cos(Math.toRadians(i)) * originalY);
                if (x + dx < 8 && x + dx >= 0 && y + dy < 8 && y + dy >= 0) {
                    behaviourMap[x + dx][y + dy] = classify(collisionMap, x + dx, y + dy);
                }
            }

            return behaviourMap;
        }
        if (piece instanceof Bishop) {
            //  Diagonal NE
            int i = x + 1;
            int j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i++;
                j++;
            }
            // Diagonal SE
            i = x + 1;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i++;
                j--;
            }
            // Diagonal NW
            i = x - 1;
            j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i--;
                j++;
            }
            // Diagonal SW
            i = x - 1;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i--;
                j--;
            }

            return behaviourMap;
        }
        if (piece instanceof Rook) {

            int i;
            int j;
            // Direction N
            i = x;
            j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                j++;
            }
            // Direction S
            i = x;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                j--;
            }
            // Direction E
            i = x + 1;
            j = y;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i++;
            }
            // Direction W
            i = x - 1;
            j = y;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j);
                if (collisionMap[i][j] == TILE_STATUS.OCCUPIED) {
                    break;
                }
                i--;
            }

            return behaviourMap;
        }

        return behaviourMap;
    }

}
