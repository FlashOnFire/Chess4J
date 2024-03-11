package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    Vector2i position;
    Piece.Color color;

    Piece(Vector2i position, Piece.Color color) {
        this.position = position;
    }

    public abstract List<Vector2i> getLegalMoves(Board board);

    public Color getColor() {
        return color;
    }

    public enum Color {
        WHITE,
        BLACK
    }

    // i must use 4 for to avoid testing position behind other pieces
    public List<Vector2i> diagonalMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            if (board.isFree(new Vector2i(position).add(i, i))) {
                moves.add(new Vector2i(i, i));
            } else if (board.isEnemy(new Vector2i(position).add(i, i), color)) {
                moves.add(new Vector2i(i, i));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (board.isFree(new Vector2i(position).add(-i, i))) {
                moves.add(new Vector2i(-i, i));
            } else if (board.isEnemy(new Vector2i(position).add(-i, i), color)) {
                moves.add(new Vector2i(-i, i));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (board.isFree(new Vector2i(position).add(i, -i))) {
                moves.add(new Vector2i(i, -i));
            } else if (board.isEnemy(new Vector2i(position).add(i, -i), color)) {
                moves.add(new Vector2i(i, -i));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (board.isFree(new Vector2i(position).add(-i, -i))) {
                moves.add(new Vector2i(-i, -i));
            } else if (board.isEnemy(new Vector2i(position).add(-i, -i), color)) {
                moves.add(new Vector2i(-i, -i));
                break;
            } else {
                break;
            }
        }

        return moves;
    }

    // i must use 4 for to avoid testing position behind other pieces
    public List<Vector2i> linearMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            if (board.isFree(new Vector2i(position).add(i, 0))) {
                moves.add(new Vector2i(i, 0));
            } else if (board.isEnemy(new Vector2i(position).add(i, 0), color)) {
                moves.add(new Vector2i(i, 0));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (board.isFree(new Vector2i(position).add(0, i))) {
                moves.add(new Vector2i(0, i));
            } else if (board.isEnemy(new Vector2i(position).add(0, i), color)) {
                moves.add(new Vector2i(0, i));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (board.isFree(new Vector2i(position).add(-i, 0))) {
                moves.add(new Vector2i(-i, 0));
            } else if (board.isEnemy(new Vector2i(position).add(-i, 0), color)) {
                moves.add(new Vector2i(-i, 0));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (board.isFree(new Vector2i(position).add(0, -i))) {
                moves.add(new Vector2i(0, -i));
            } else if (board.isEnemy(new Vector2i(position).add(0, -i), color)) {
                moves.add(new Vector2i(0, -i));
                break;
            } else {
                break;
            }
        }

        return moves;
    }
}