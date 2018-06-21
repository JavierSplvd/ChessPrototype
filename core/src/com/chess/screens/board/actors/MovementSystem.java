package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.chess.Chess;
import com.chess.screens.board.BoardScreen;
import com.chess.screens.board.StateMachine;
import com.chess.screens.board.actors.pieces.*;

public class MovementSystem {


    public enum TILE_STATUS {
        FREE, WHITE, BLACK, MOVE, ATTACK;
    }

    private final Texture movementTileTex;
    private Texture attackTileTex;
    private Group movTilesActors;
    private BoardScreen boardScreen;


    public MovementSystem(Texture movementTexture, Texture attackTexture, BoardScreen boardScreen) {
        movTilesActors = new Group();
        this.movementTileTex = movementTexture;
        this.attackTileTex = attackTexture;
        this.boardScreen = boardScreen;
    }

    public void createNewMovTiles(Piece pieceSelected, StateMachine stateMachine) {
        movTilesActors.clear();

        TILE_STATUS[][] behaviourMap = createBehaviourMap(createCollisionMap(), pieceSelected);

        int columns = 8;
        int rows = 8;
        int numberMoves = 0;
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (behaviourMap[x][y] == TILE_STATUS.MOVE) {
                    Tile tile = new MovementTile(movementTileTex, stateMachine);
                    tile.setBoardPosition(x, y);
                    movTilesActors.addActor(tile);
                    numberMoves++;
                }
                if (behaviourMap[x][y] == TILE_STATUS.ATTACK) {
                    Tile tile = new AttackTile(attackTileTex, stateMachine, boardScreen.getPieceIn(x, y));
                    tile.setBoardPosition(x, y);
                    movTilesActors.addActor(tile);
                    numberMoves++;
                }
            }
        }
        if (numberMoves == 0) {
            stateMachine.returnToChooseState();
        } else {
            stateMachine.nextState();
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
                    boolean isWhite = boardScreen.checkIfItIsWhite(x, y);
                    if (isWhite) {
                        collisionMap[x][y] = TILE_STATUS.WHITE;
                    } else {
                        collisionMap[x][y] = TILE_STATUS.BLACK;
                    }
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

    private TILE_STATUS classify(TILE_STATUS[][] collisionMap, int i, int j, Piece piece) {
        if (i < 8 && i >= 0 && j < 8 && j >= 0) {
            if (collisionMap[i][j] == TILE_STATUS.FREE) {
                return TILE_STATUS.MOVE;
            } else if (collisionMap[i][j] == TILE_STATUS.WHITE && piece.getPlayer() == Chess.PLAYER.BLACKS) {
                return TILE_STATUS.ATTACK;
            } else if (collisionMap[i][j] == TILE_STATUS.BLACK && piece.getPlayer() == Chess.PLAYER.WHITES) {
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
            // Movement pattern
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
            //Attack pattern
            //Left
            if (piece.getPlayer() == Chess.PLAYER.WHITES) {
                y = piece.getyBoardCoord() + 1;
                x = piece.getxBoardCoord() - 1;
            } else {
                y = piece.getyBoardCoord() - 1;
                x = piece.getxBoardCoord() - 1;
            }

            if (x < 8 && y < 8 && x >= 0 && y >= 0) {
                if (collisionMap[x][y] == TILE_STATUS.BLACK && piece.getPlayer() == Chess.PLAYER.WHITES) {
                    behaviourMap[x][y] = TILE_STATUS.ATTACK;
                } else if (collisionMap[x][y] == TILE_STATUS.WHITE && piece.getPlayer() == Chess.PLAYER.BLACKS) {
                    behaviourMap[x][y] = TILE_STATUS.ATTACK;
                }
            }
            //Right
            if (piece.getPlayer() == Chess.PLAYER.WHITES) {
                y = piece.getyBoardCoord() + 1;
                x = piece.getxBoardCoord() + 1;
            } else {
                y = piece.getyBoardCoord() - 1;
                x = piece.getxBoardCoord() + 1;
            }

            if (x < 8 && y < 8 && x >= 0 && y >= 0) {
                if (collisionMap[x][y] == TILE_STATUS.BLACK && piece.getPlayer() == Chess.PLAYER.WHITES) {
                    behaviourMap[x][y] = TILE_STATUS.ATTACK;
                } else if (collisionMap[x][y] == TILE_STATUS.WHITE && piece.getPlayer() == Chess.PLAYER.BLACKS) {
                    behaviourMap[x][y] = TILE_STATUS.ATTACK;
                }
            }
            return behaviourMap;
        }
        if (piece instanceof King) {
            for (int angle = 0; angle < 360; angle = angle + 45) {
                int dx = (int) Math.round(Math.cos(Math.toRadians(angle)) * 1 - Math.sin(Math.toRadians(angle)) * 0);
                int dy = (int) Math.round(Math.sin(Math.toRadians(angle)) * 1 + Math.cos(Math.toRadians(angle)) * 0);
                if (x + dx < 8 && x + dx >= 0 && y + dy < 8 && y + dy >= 0) {
                    behaviourMap[x + dx][y + dy] = classify(collisionMap, x + dx, y + dy, piece);
                }
            }
            return behaviourMap;
        }
        if (piece instanceof Queen) {
            //  Diagonal NE
            int i = x + 1;
            int j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i++;
                j++;
            }
            // Diagonal SE
            i = x + 1;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i++;
                j--;
            }
            // Diagonal NW
            i = x - 1;
            j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i--;
                j++;
            }
            // Diagonal SW
            i = x - 1;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i--;
                j--;
            }
            // Direction N
            i = x;
            j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                j++;
            }
            // Direction S
            i = x;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                j--;
            }
            // Direction E
            i = x + 1;
            j = y;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i++;
            }
            // Direction W
            i = x - 1;
            j = y;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
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
                    behaviourMap[x + dx][y + dy] = classify(collisionMap, x + dx, y + dy, piece);
                }
            }
            originalX = -1;
            originalY = 2;
            for (int i = 0; i <= 360; i = i + 90) {
                dx = (int) Math.round(Math.cos(Math.toRadians(i)) * originalX - Math.sin(Math.toRadians(i)) * originalY);
                dy = (int) Math.round(Math.sin(Math.toRadians(i)) * originalX + Math.cos(Math.toRadians(i)) * originalY);
                if (x + dx < 8 && x + dx >= 0 && y + dy < 8 && y + dy >= 0) {
                    behaviourMap[x + dx][y + dy] = classify(collisionMap, x + dx, y + dy, piece);
                }
            }

            return behaviourMap;
        }
        if (piece instanceof Bishop) {
            //  Diagonal NE
            int i = x + 1;
            int j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i++;
                j++;
            }
            // Diagonal SE
            i = x + 1;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i++;
                j--;
            }
            // Diagonal NW
            i = x - 1;
            j = y + 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i--;
                j++;
            }
            // Diagonal SW
            i = x - 1;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
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
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                j++;
            }
            // Direction S
            i = x;
            j = y - 1;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                j--;
            }
            // Direction E
            i = x + 1;
            j = y;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i++;
            }
            // Direction W
            i = x - 1;
            j = y;
            while (i < 8 && j < 8 && i >= 0 && j >= 0) {
                behaviourMap[i][j] = classify(collisionMap, i, j, piece);
                if (collisionMap[i][j] == TILE_STATUS.WHITE || collisionMap[i][j] == TILE_STATUS.BLACK) {
                    break;
                }
                i--;
            }

            return behaviourMap;
        }

        return behaviourMap;
    }

}
