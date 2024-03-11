package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.List;

public class Pawn extends Piece {
    Pawn(Vector2i position) {
        super(position);
    }

    @Override
    public List<Vector2i> getLegalMoves(Board board) {
        return null;
    }
}
