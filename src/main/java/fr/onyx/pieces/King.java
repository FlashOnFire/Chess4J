package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    King(Vector2i position, Piece.Color color) {
        super(position, color);
    }

    @Override
    public List<Vector2i> getLegalMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();
        
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if(x == 0 && y == 0) {continue;}
                if (board.isFree(new Vector2i(position).add(x, y)) || board.isEnemy(new Vector2i(position).add(x, y), color)) {
                    moves.add(new Vector2i(x, y));
                }
            }
        }
        //for the rock, but I need to verify one of the rook don't have make any move since the start of the game, and king don't have move either
        if (board.isFree(new Vector2i(position).add(-2, 0))) {
            moves.add(new Vector2i(-2, 0));
        }

        if (board.isFree(new Vector2i(position).add(2, 0))) {
            moves.add(new Vector2i(2, 0));
        }

        return moves;
    }
}
