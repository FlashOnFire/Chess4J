package fr.onyx;

import fr.onyx.pieces.Piece;

public class Board {
    private static final int width = 8;
    private static final int height = 8;

    private Piece[][] cases = new Piece[width][height];

    public Piece getPiece(int x, int y) {
        return cases[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        cases[x][y] = piece;
    }

    public void removePiece(int x, int y) {
        cases[x][y] = null;
    }

    public boolean isInside(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isOccupied(int x, int y) {
        return cases[x][y] != null;
    }

    public boolean isFree(int x, int y) {
        return isInside(x, y) && !isOccupied(x, y);
    }

    public boolean isEnemy(int x, int y, Piece.Color color) {
        return isInside(x, y) && isOccupied(x, y) && getPiece(x, y).getColor() != color;
    }

    public boolean isAlly(int x, int y, Piece.Color color) {
        return isInside(x, y) && isOccupied(x, y) && getPiece(x, y).getColor() == color;
    }
}
