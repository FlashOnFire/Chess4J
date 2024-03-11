package fr.onyx.pieces;

import org.joml.Vector2i;

import java.util.List;

public class Pawn extends Piece {
    Pawn(Vector2i position) {
        super(position);
    }

    @Override
    public List<Vector2i> getLegalMoves() {
        return null;
    }
}
