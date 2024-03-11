package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

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
}
