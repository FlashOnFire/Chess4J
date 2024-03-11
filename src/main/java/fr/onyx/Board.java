package fr.onyx;

import fr.onyx.pieces.Piece;
import org.joml.Vector2i;

public class Board {
    private static final int width = 8;
    private static final int height = 8;

    private Piece[][] cases = new Piece[width][height];

    public Piece getPiece(Vector2i position) {
        return cases[position.x][position.y];
    }

    public void setPiece(Vector2i position, Piece piece) {
        cases[position.x][position.y] = piece;
    }

    public void removePiece(Vector2i position) {
        cases[position.x][position.y] = null;
    }

    public boolean isInside(Vector2i position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }

    public boolean isOccupied(Vector2i position) {
        return isInside(position) && cases[position.x][position.y] != null;
    }

    public boolean isFree(Vector2i position) {
        return isInside(position) && cases[position.x][position.y] == null;
    }

    public boolean isEnemy(Vector2i position, Piece.Color color) {
        return isInside(position) && isOccupied(position) && getPiece(position).getColor() != color;
    }

    public boolean isAlly(Vector2i position, Piece.Color color) {
        return isInside(position) && isOccupied(position) && getPiece(position).getColor() == color;
    }
}
