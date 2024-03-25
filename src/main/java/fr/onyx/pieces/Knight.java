package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Vector2i position, Piece.Color color) {
        super(position, color);
    }

    @Override
    public List<Vector2i> getLegalMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if(x == 0 || y == 0) {continue;}
                if (board.isFree(new Vector2i(position).add(x, 2*y)) || board.isEnemy(new Vector2i(position).add(x, 2*y), color)) {
                    moves.add(new Vector2i(x, 2*y));
                }
                if (board.isFree(new Vector2i(position).add(2*x, y)) || board.isEnemy(new Vector2i(position).add(2*x, y), color)) {
                    moves.add(new Vector2i(2*x, y));
                }
            }
        }

        moves = moveInside(moves, board);
        return moves;
    }
}
