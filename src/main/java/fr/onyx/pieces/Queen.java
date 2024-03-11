package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    Queen(Vector2i position, Piece.Color color) {
        super(position, color);
    }

    @Override
    public List<Vector2i> getLegalMoves(Board board) {
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
