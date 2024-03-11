package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.List;

public class Knight extends Piece {
    Knight(Vector2i position, Piece.Color color) {
        super(position, color);
    }

    @Override
    public List<Vector2i> getLegalMoves(Board board) {
        return null;
    }
}
