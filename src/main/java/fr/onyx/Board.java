package fr.onyx;

import fr.onyx.pieces.*;
import org.joml.Vector2i;

public class Board {
    private static final int width = 8;
    private static final int height = 8;

    private Piece[][] cases = new Piece[width][height];

    public void init8board() {
        for (int i = 0; i<8; i++) {
            cases[1][i] = new Pawn(new Vector2i(1, i), Piece.Color.BLACK);
            cases[1][i] = new Pawn(new Vector2i(6, i), Piece.Color.WHITE);
        }
        for (int i = 0; i<2; i++) {
            cases[0][7*i] = new Rook(new Vector2i(0, 7*i), Piece.Color.BLACK);
            cases[7][7*i] = new Rook(new Vector2i(7, 7*i), Piece.Color.WHITE);

            cases[0][1 + 5*i] = new Knight(new Vector2i(0, 0), Piece.Color.BLACK);
            cases[7][1 + 5*i] = new Knight(new Vector2i(0, 0), Piece.Color.WHITE);

            cases[0][2 + 3*i] = new Bishop(new Vector2i(0, 0), Piece.Color.BLACK);
            cases[7][2 + 3*i] = new Bishop(new Vector2i(0, 0), Piece.Color.WHITE);
        }
        cases[0][3] = new Queen(new Vector2i(0, 0), Piece.Color.BLACK);
        cases[7][4] = new Queen(new Vector2i(0, 0), Piece.Color.WHITE);
        cases[0][4] = new King(new Vector2i(0, 0), Piece.Color.BLACK);
        cases[7][3] = new King(new Vector2i(0, 0), Piece.Color.WHITE);
    }
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
