package fr.onyx.pieces;

import fr.onyx.Board;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Vector2i position, Piece.Color color) {
        super(position, color);
    }

    @Override
    public List<Vector2i> getLegalMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();

        //movement
        if (board.isFree(new Vector2i(position).add(0, 1))) {
            moves.add(new Vector2i(0, 1));
            if (board.isFree(new Vector2i(position).add(0, 2))) {
                moves.add(new Vector2i(0, 2));
            }
        }

        //attack
        if (board.isEnemy(new Vector2i(position).add(1, 1), color)) {
            moves.add(new Vector2i(1, 1));
        }
        if (board.isEnemy(new Vector2i(position).add(1, 1), color)) {
            moves.add(new Vector2i(-1, 1));
        }

        // warning : if your pawns are on the enemies' pawns spawn enemies pawns push two cases forward , you can take "en passant" and take the pawn even if they are not in your range attack
        /*
        example :
        previous enemies position (5, 2) and he move to (5, 4)
        your pawn are in (6, 3)
        only for this turn you can go at (5, 2) and take enemie's pawn at (5, 4)
         */

        return moves;
    }
}
