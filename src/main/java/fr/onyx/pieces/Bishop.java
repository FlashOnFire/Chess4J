package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.List;

public class Bishop extends Piece {
    Bishop(Vector2i position, Piece.Color color) {
        super(position, color);
    }

    @Override
    public List<Vector2i> getLegalMoves(Board board) {
        return diagonalMoves(board);
    }
}
