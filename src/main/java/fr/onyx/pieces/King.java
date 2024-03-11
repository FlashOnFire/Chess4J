package fr.onyx.pieces;

import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    King(Vector2i position) {
        super(position);
    }

    @Override
    public List<Vector2i> getLegalMoves() {
        List<Vector2i> moves = new ArrayList<>();

        moves.add(new Vector2i(1, 0));
        moves.add(new Vector2i(-1, 0));
        moves.add(new Vector2i(0, 1));
        moves.add(new Vector2i(0, -1));
        moves.add(new Vector2i(1, 1));
        moves.add(new Vector2i(-1, -1));
        moves.add(new Vector2i(1, -1));
        moves.add(new Vector2i(-1, 1));

        return moves;
    }
}
