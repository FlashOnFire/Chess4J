package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.List;

public class Queen extends Piece {
    Queen(Vector2i position, Piece.Color color) {
        super(position, color);
    }

    @Override
    public List<Vector2i> getLegalMoves(Board board) {
        List<Vector2i> moves = diagonalMoves(board);
        moves.addAll(linearMoves(board));

        return moves;
    }
}
