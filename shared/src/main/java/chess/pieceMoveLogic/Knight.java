package chess.pieceMoveLogic;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

import static chess.ChessPiece.getMovesHelper;

public class Knight {
    public static Collection<ChessMove> getKnightMoves(ChessBoard board, ChessPosition startPosition, ChessGame.TeamColor color) {
        Collection<ChessMove> moves = new ArrayList<>();

        getMovesHelper(board,startPosition,2,-1, color, moves);
        getMovesHelper(board,startPosition,2,1, color, moves);
        getMovesHelper(board,startPosition,1,2, color, moves);
        getMovesHelper(board,startPosition,1,-2, color, moves);
        getMovesHelper(board,startPosition,-1,2, color, moves);
        getMovesHelper(board,startPosition,-1,-2, color, moves);
        getMovesHelper(board,startPosition,-2,1, color, moves);
        getMovesHelper(board,startPosition,-2,-1, color, moves);

        return moves;
    }
}
