package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.List;

public abstract class Piece {
    Vector2i position;

    Piece(Vector2i position) {
        this.position = position;
    }

    public abstract List<Vector2i> getLegalMoves(Board board);
}
