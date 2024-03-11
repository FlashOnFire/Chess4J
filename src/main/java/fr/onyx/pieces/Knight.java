package fr.onyx.pieces;

import org.joml.Vector2i;

import java.util.List;

public class Knight extends Piece {
    Knight(Vector2i position) {
        super(position);
    }

    @Override
    public List<Vector2i> getLegalMoves() {
        return null;
    }
}
